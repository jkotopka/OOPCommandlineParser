package org.kotopka.CommandlineParser;

import java.util.List;

public class ExtendedHelpMessage extends AbstractOption {

    private boolean value;

    public ExtendedHelpMessage() {
        this.commandlineSwitch = Switch.EXTENDED_HELP;
        this.description = "Get extended help for option";
        this.extendedHelpMessage =
                "Extended Help -- " +
                "Get extended help on commandline switch options." + NEWLINE +
                "\tUsage: " + commandlineSwitch + " <option>";
    }

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public int execute(Parser parser) {
        int argIndex = parser.getArgIndex();
        List<String> args = parser.getArgs();
        String arg = getArg(parser, argIndex, args);

        value = true;

        printExtendedHelpMessage(arg, parser);
        System.exit(0);

        return Integer.MAX_VALUE; // XXX: hackish workaround, never actually gets returned
    }

    private String getArg(Parser parser, int argIndex, List<String> args) {
        String arg;

        if (args.size() > 1 && argIndex < args.size() - 1)
            arg = parser.getArgs().get(++argIndex);
        else
            arg = commandlineSwitch.getLabel();

        return arg;
    }

    private void printExtendedHelpMessage(String arg, Parser parser) {
        try {
            System.out.println(arg + ": " + parser.getOption(Switch.get(arg)).getExtendedHelp());
        } catch (Exception e) {
            System.err.println("Unknown option: " + arg);
        }
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
