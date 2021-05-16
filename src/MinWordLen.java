public class MinWordLen implements Option {

    private static String NEWLINE = System.lineSeparator();

    private String commandlineSwitch;
    private String description;
    private int value;

    public MinWordLen( ) {
        this.commandlineSwitch = "-minwl";
        this.description = "Minimum word length";
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
    public String getSwitch() {
        return commandlineSwitch;
    }

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
