package org.kotopka.CommandlineParser;

public class HelpMessage extends AbstractOption {

    private boolean value;

    public HelpMessage() {
        this.commandlineSwitch = Switch.HELP;
        this.description = "Get help";
        this.extendedHelpMessage =
                "Help -- " +
                "Prints a list of options with a simplified description of each. " +
                "This option does not take additional parameters." + NEWLINE +
                "\tUsage: " + commandlineSwitch;
    }

    @Override
    public String getExtendedHelp() { return extendedHelpMessage; }

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public int execute(Parser parser) {
        value = true;

        parser.printOptions();

        return parser.getArgIndex() + 1;
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
