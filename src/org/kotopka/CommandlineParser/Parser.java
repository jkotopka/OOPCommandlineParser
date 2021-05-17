package org.kotopka.CommandlineParser;

import java.util.*;

// TODO: add extended help option with detailed help messages for each Option class
public class Parser {

    private int argIndex;
    private final List<String> argList;
    private final Map<Switch, Option> options;
    private final Switch defaultOption;
    private final Set<String> validDelimiters;

    public Parser(String[] args, Switch defaultOption) {
        Objects.requireNonNull(args, "args argument cannot be null");
        Objects.requireNonNull(defaultOption, "optionSwitch argument cannot be null");

        this.argList = List.of(args);
        this.options = new LinkedHashMap<>();
        this.defaultOption = defaultOption;
        this.validDelimiters = new HashSet<>();

        // TODO: maybe make this one field and remove method to set it below
        validDelimiters.add(Switch.getDefault());
    }

    int getArgIndex() { return argIndex; }

    List<String> getArgs() { return argList; }

    public Parser addValidDelimiter(String switchDelimiter) {
        Objects.requireNonNull(switchDelimiter, "switchDelimiter argument cannot be null");

        validDelimiters.add(switchDelimiter);

        return this;
    }

    public Parser addOptions(Option... optionArgs) {
        Objects.requireNonNull(optionArgs, "optionArgs argument cannot be null");

        // TODO: maybe add try/catch NPE block
        for (Option o : optionArgs)
            options.put(o.getSwitch(), o);

        return this;
    }

    public Parser addOption(Option option) {
        addOptions(option);

        return this;
    }

    public void parseArgs() {
        while (argIndex < argList.size()) {
            String arg    = argList.get(argIndex);
            Switch option = Switch.get(arg);

            if (options.containsKey(option))
                executeOptionAndUpdateIndex(option);
            else if (isInvalidOption(arg))
                printInvalidOptionMessageAndExit(arg);
            else
                executeOptionAndUpdateIndex(defaultOption);
        }
    }

    private void executeOptionAndUpdateIndex(Switch option) {
        argIndex = options.get(option).execute(this);
    }

    private boolean isInvalidOption(String arg) {
        // XXX: checks if the arg looks like a valid option but isn't
        for (String s : validDelimiters)
            if (arg.startsWith(s)) return true;

        return false;
    }

    private void printInvalidOptionMessageAndExit(String arg) {
        System.err.println("Invalid option: " + arg);
        printOptions();
        System.exit(-1);
    }

    public Option getOption(Switch commandlineSwitch) {
        Objects.requireNonNull(commandlineSwitch, "commandlineSwitch argument cannot be null");

        return options.get(commandlineSwitch);
    }

    public void printOptions() {
        options.forEach((key, value) -> System.out.println(value.getHelp()));
    }

    public void printValues() {
        options.forEach((key, value) -> System.out.println(value));
    }

    public void printState() {
        options.forEach((key, value) -> System.out.println(value.getState()));
    }

    public static void main(String[] args) {
        Parser parser = new Parser(args, Switch.COLLECT_PHRASE);
        parser.addOptions(
                new DictFile(),
                new MinWordLen(),
                new MaxWordLen(),
                new ExcludeFile(),
                new RestrictPermutations(),
                new ExcludeDuplicates(),
                new HelpMessage(),
                new PhraseCollector());

        parser.parseArgs();

//        parser.printOptions();
        parser.printValues();
        parser.printState();

        System.out.println(parser.getOption(Switch.COLLECT_PHRASE).getString());
        System.out.println(parser.getOption(Switch.MAX_WORD_LENGTH).getInt());
        System.out.println(parser.getOption(Switch.RESTRICT_PERMUTATIONS).getBool());
        System.out.println(parser.getOption(Switch.EXCLUDE_DUPLICATES).getBool());
    }
}
