package org.kotopka.CommandlineParser;

public class ExcludeDuplicates extends AbstractOption {

    private boolean value;

    public ExcludeDuplicates( ) {
        this.commandlineSwitch = Switch.EXCLUDE_DUPLICATES;
        this.description = "Exclude duplicates";
        this.extendedHelpMessage =
                "Exclude Duplicates -- " +
                "Attempts to exclude duplicate words in the anagram. " +
                "This option does not take additional parameters." + NEWLINE +
                "\tUsage: " + commandlineSwitch;
    }

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public boolean getBool() { return value; }

    @Override
    public int execute(Parser parser) {
        value = true;

        return parser.getArgIndex() + 1;
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
