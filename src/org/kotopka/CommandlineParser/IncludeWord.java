package org.kotopka.CommandlineParser;

public class IncludeWord extends AbstractOption {

    private String value;

    public IncludeWord( ) {
        this.commandlineSwitch = Switch.INCLUDE_WORD;
        this.description = "Include word";
        this.value = "";
        this.extendedHelpMessage =
                "Include Word -- " +
                "Include the word supplied as an option in the anagram." + NEWLINE +
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
