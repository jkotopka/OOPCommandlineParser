public class MaxWordLen implements Option {

    private static String NEWLINE = System.lineSeparator();

    private String commandlineSwitch;
    private String description;
    private int value;

    public MaxWordLen( ) {
        this.commandlineSwitch = "-maxwl";
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
    public String getHelp() {
        return String.format("\t%-5s\t%s", commandlineSwitch, description);
    }

    @Override
    public String getSwitch() { return commandlineSwitch; }

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
