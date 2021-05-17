package org.kotopka.CommandlineParser;

public class ExcludeWord extends AbstractOption {

    private String value;

    public ExcludeWord( ) {
        this.commandlineSwitch = Switch.EXCLUDE_WORD;
        this.description = "Exclude word";
        this.value = "";
        this.extendedHelpMessage =
                "Exclude Word -- " +
                "Exclude the word supplied as an option from appearing in the anagram." + NEWLINE +
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
