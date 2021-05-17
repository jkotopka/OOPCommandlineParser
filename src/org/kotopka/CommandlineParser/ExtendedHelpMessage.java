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

        if (args.size() == 1) {
            System.err.println("Usage: blah blah blah...");
            System.exit(-1);
        }

        String arg = parser.getArgs().get(++argIndex);

        value = true;

        if (Switch.get(arg) != null)
            System.out.println(arg + ": " + parser.getOption(Switch.get(arg)).getExtendedHelp());
        else
            System.err.println("Unknown option: " + arg);

        System.exit(0);

        return Integer.MAX_VALUE; // XXX: hackish workaround, never actually gets returned
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
