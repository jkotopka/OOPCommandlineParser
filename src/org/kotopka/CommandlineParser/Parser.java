package org.kotopka.CommandlineParser;

import java.util.*;

public class Parser {

    private int argIndex;
    private final String[] args;
    private final Map<Switch, Option> options;
    private final Switch defaultOption;

    public Parser(String[] args, Switch defaultOption) {
        this.args = args;
        this.options = new LinkedHashMap<>();
        this.defaultOption = defaultOption;
    }

    public Parser addOptions(Option... optionArgs) {
        for (Option o : optionArgs)
            options.put(o.getSwitch(), o);

        return this;
    }

    public Parser addOption(Option option) {
        options.put(option.getSwitch(), option);

        return this;
    }

    public void parseArgs() {
        while (argIndex < args.length) {
            String arg    = args[argIndex];
            Switch option = Switch.get(arg);

            if (options.containsKey(option))
                executeOptionAndUpdateIndex(option);
            else if (arg.startsWith("-")) //XXX: not super happy about this but it seems to work as intended
                printInvalidOptionMessageAndExit(arg);
            else
                executeOptionAndUpdateIndex(defaultOption);
        }
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

//        System.out.println(parser.getOption(org.kotopka.parser.Switch.COLLECT_PHRASE).getString());
//        System.out.println(parser.getOption(org.kotopka.parser.Switch.MAX_WORD_LENGTH).getInt());
//        System.out.println(parser.getOption(org.kotopka.parser.Switch.RESTRICT_PERMUTATIONS).getBool());
    }
}