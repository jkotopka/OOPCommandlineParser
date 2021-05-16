public class MinWordLen extends AbstractOption{

    private int value;

    public MinWordLen( ) {
        this.commandlineSwitch = Switch.MIN_WORD_LENGTH;
        this.description = "Minimum word length";
    }

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public int getInt() { return value; }

    @Override
    public int execute(String[] args, int argIndex) {
        String arg = args[++argIndex];

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
