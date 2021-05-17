package org.kotopka.CommandlineParser;

public class HelpMessage extends AbstractOption {

    private boolean value;

    public HelpMessage() {
        this.commandlineSwitch = Switch.HELP;
        this.description = "This help message";
    }

    @Override
    public String getState() {
        return "Help invoked: " + value;
    }

    @Override
    public int execute(Parser parser) {
        value = true;

        parser.printOptions();

        return parser.argIndex + 1;
    }

}
