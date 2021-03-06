package org.kotopka.CommandlineParser;

public class ExcludeFile extends AbstractOption {

    private String value;

    public ExcludeFile( ) {
        this.commandlineSwitch = Switch.EXCLUDE_FROM_DICT_FILE;
        this.description = "Exclude words file";
        this.value = "";
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
