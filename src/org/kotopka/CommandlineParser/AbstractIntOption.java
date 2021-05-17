package org.kotopka.CommandlineParser;

public class AbstractIntOption extends AbstractOption {

    int value;

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public int getInt() { return value; }

    @Override
    public int execute(Parser parser) {
        int argIndex = parser.getArgIndex();
        String arg = "";

        try {
            arg = parser.getArgs().get(++argIndex);
            value = Integer.parseInt(arg);
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid number format: " + arg);
            System.exit(-1);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            System.err.println(commandlineSwitch + ": Cannot parse argument! Please ensure the correct parameter was provided.");
            System.exit(-1);
        }

        return ++argIndex;
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
