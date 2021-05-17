package org.kotopka.CommandlineParser;

public class IncludeWordWithSuffix extends AbstractOption {

    private String value;

    public IncludeWordWithSuffix( ) {
        this.commandlineSwitch = Switch.INCLUDE_WORD_WITH_SUFFIX;
        this.description = "Include word with suffix";
        this.value = "";
        this.extendedHelpMessage =
                "Include Word with Suffix -- " +
                "Include the word having the suffix supplied as an option in the anagram." + NEWLINE +
                "\tUsage: " + commandlineSwitch + " <suffix>";
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
