package org.kotopka.CommandlineParser;

public class MaxWordLen extends AbstractOption {

    private int value;

    public MaxWordLen( ) {
        this.commandlineSwitch = Switch.MAX_WORD_LENGTH;
        this.description = "Maximum word length";
        this.value = Integer.MAX_VALUE;
        this.extendedHelpMessage =
                "Max Word Length -- " +
                "Maximum length of words to be inserted into the dictionary." + NEWLINE +
                "\tUsage: " + commandlineSwitch + " <length>";
    }

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public int getInt() { return value; }

    @Override
    public int execute(Parser parser) {
        int argIndex = parser.getArgIndex();
        String arg = parser.getArgs().get(++argIndex);

        try {
            value = Integer.parseInt(arg);
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid number format: " + arg);
            System.exit(-1);
        }

        return ++argIndex;
    }


    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
