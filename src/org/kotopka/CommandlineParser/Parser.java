package org.kotopka.CommandlineParser;

import java.util.*;

public class Parser {

    private int argIndex;
    private final List<String> argList;
    private final Map<Switch, Option> options;
    private final Switch defaultOption;
    private final String validDelimiter;

    public Parser(String[] args, Switch defaultOption) {
        Objects.requireNonNull(args, "args argument cannot be null");
        Objects.requireNonNull(defaultOption, "optionSwitch argument cannot be null");

        this.argList = List.of(args);
        this.options = new LinkedHashMap<>();
        this.defaultOption = defaultOption;
        this.validDelimiter = Switch.getDefault();
    }

    int getArgIndex() { return argIndex; }

    List<String> getArgs() { return argList; }

    public Parser addOptions(Option... optionArgs) {
        Objects.requireNonNull(optionArgs, "optionArgs argument cannot be null");

        try {
            for (Option o : optionArgs)
                options.put(o.getSwitch(), o);
        } catch (NullPointerException e) {
            System.err.println("Parser initialization failed! Null option found. Please check that all Options are valid.");
            System.exit(-1);
        }

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
        return arg.startsWith(validDelimiter);
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

}
