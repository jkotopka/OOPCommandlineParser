package org.kotopka.CommandlineParser;

public class AbstractStringOption extends AbstractOption {

    String value;

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public String getString() { return value; }

    @Override
    public int execute(Parser parser) {
        int argIndex = parser.getArgIndex();

        try {
            value = parser.getArgs().get(++argIndex);
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
