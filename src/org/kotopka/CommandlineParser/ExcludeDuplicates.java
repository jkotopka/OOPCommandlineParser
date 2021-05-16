package org.kotopka.CommandlineParser;

public class ExcludeDuplicates extends AbstractOption {

    private boolean value;

    public ExcludeDuplicates( ) {
        this.commandlineSwitch = Switch.EXCLUDE_DUPLICATES;
        this.description = "Exclude Duplicates";
    }

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public boolean getBool() { return value; }

    @Override
    public int execute(String[] args, int argIndex) {
        value = true;

        return ++argIndex;
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
