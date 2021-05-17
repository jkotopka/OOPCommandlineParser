package org.kotopka.CommandlineParser;

public class Timeout extends AbstractOption {

    private int value;

    public Timeout( ) {
        this.commandlineSwitch = Switch.MAX_WORDS;
        this.description = "Max words";
        this.value = Integer.MAX_VALUE;
        this.extendedHelpMessage =
                "Max Words -- " +
                "Maximum number of words in anagram. " + NEWLINE +
                "\tUsage: " + commandlineSwitch + " <max>";
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
