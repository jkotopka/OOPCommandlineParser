package org.kotopka.CommandlineParser;

public class DictFile extends AbstractOption {

    private String value;

    public DictFile( ) {
        this.commandlineSwitch = Switch.DICT_FILE;
        this.description = "Dictionary file";
        this.value = "";
        this.extendedHelpMessage =
                "Dictionary file -- " +
                "Name of file which contains words to be inserted into the dictionary." + NEWLINE +
                "\tUsage: " + commandlineSwitch + " <filename>";
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
