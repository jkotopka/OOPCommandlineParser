public class MaxWordLen extends AbstractOption {

    private int value;

    public MaxWordLen( ) {
        this.commandlineSwitch = Switch.MAX_WORD_LENGTH;
        this.description = "Maximum word length";
        this.value = Integer.MAX_VALUE;
    }

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public int getInt() { return value; }

    @Override
    public int execute(String[] args, int argIndex) {
        try {
            value = Integer.parseInt(args[++argIndex]);
        } catch (NumberFormatException nfe) {
            System.err.println("Invalid number format");
        }

        return ++argIndex;
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
