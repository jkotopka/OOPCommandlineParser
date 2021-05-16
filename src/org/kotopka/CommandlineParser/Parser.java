package org.kotopka.CommandlineParser;

import java.util.*;

public class Parser {

    private int argIndex;
    private final String[] args;
    private final Map<Switch, Option> options;
    private final Switch defaultOption;
    private final Set<String> validDelimiters;

    public Parser(String[] args, Switch defaultOption) {
        Objects.requireNonNull(args, "args argument cannot be null");
        Objects.requireNonNull(defaultOption, "defaultOptions argument cannot be null");

        this.args = args;
        this.options = new LinkedHashMap<>();
        this.defaultOption = defaultOption;
        this.validDelimiters = new HashSet<>();

        validDelimiters.add("-"); // set - as default commandline switch delimiter, maybe have a way to un-set this
    }

    public Parser addValidDelimiter(String switchDelimiter) {
        Objects.requireNonNull(switchDelimiter, "switchDelimiter argument cannot be null");

        validDelimiters.add(switchDelimiter);

        return this;
    }

    public Parser addOptions(Option... optionArgs) {
        Objects.requireNonNull(optionArgs, "optionArgs argument cannot be null");

        for (Option o : optionArgs)
            options.put(o.getSwitch(), o);

        return this;
    }

    public Parser addOption(Option option) {
        addOptions(option);

        return this;
    }

    public void parseArgs() {
        while (argIndex < args.length) {
            String arg    = args[argIndex];
            Switch option = Switch.get(arg);

            if (options.containsKey(option))
                executeOptionAndUpdateIndex(option);
            else if (isInvalidOption(arg))
                printInvalidOptionMessageAndExit(arg);
            else
                executeOptionAndUpdateIndex(defaultOption);
        }
    }

    private boolean isInvalidOption(String arg) {
        for (String s : validDelimiters)
            if (arg.startsWith(s)) return true;

        return false;
    }

    private void executeOptionAndUpdateIndex(Switch option) {
        argIndex = options.get(option).execute(args, argIndex);
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
        Parser parser = new Parser(args, Switch.COLLECT_PHRASE)
                .addValidDelimiter("-")
                .addOptions(
                        new MaxWordLen(),
                        new MinWordLen(),
                        new RestrictPermutations(),
                        new ExcludeDuplicates(),
                        new PhraseCollector());

        parser.parseArgs();

//        parser.printOptions();
//        parser.printValues();
        parser.printState();

        System.out.println(parser.getOption(Switch.COLLECT_PHRASE).getString());
        System.out.println(parser.getOption(Switch.MAX_WORD_LENGTH).getInt());
        System.out.println(parser.getOption(Switch.RESTRICT_PERMUTATIONS).getBool());
        System.out.println(parser.getOption(Switch.EXCLUDE_DUPLICATES).getBool());
    }
}
