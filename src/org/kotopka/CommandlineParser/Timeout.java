package org.kotopka.CommandlineParser;

public class Timeout extends AbstractOption {

    private int value;

    public Timeout( ) {
        this.commandlineSwitch = Switch.TIMEOUT;
        this.description = "Timeout";
        this.value = Integer.MAX_VALUE;
        this.extendedHelpMessage =
                "Timeout -- " +
                "Time in seconds to attempt anagram generation. " +
                "Note: this is a best attempt effort and the actual running time may  be longer or shorter " + NEWLINE +
                "\tUsage: " + commandlineSwitch + " <timeout in seconds>";
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
