package org.kotopka.CommandlineParser;

public class StartFrom extends AbstractOption {

    private String value;

    public StartFrom( ) {
        this.commandlineSwitch = Switch.START_FROM;
        this.description = "Start from";
        this.value = "";
        this.extendedHelpMessage =
                "Start From -- " +
                "Start anagram search from the word supplied as an option." + NEWLINE +
                "\tUsage: " + commandlineSwitch + " <word>";
    }

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public String getString() { return value; }

    @Override
    public int execute(Parser parser) {
        int argIndex = parser.getArgIndex();

        value = parser.getArgs().get(++argIndex);

        return ++argIndex;
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
