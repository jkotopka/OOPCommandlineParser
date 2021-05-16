public class MinWordLen implements Option {

    private static String NEWLINE = System.lineSeparator();

    private String commandlineSwitch;
    private int value;

    public MinWordLen( ) {
        this.commandlineSwitch = "-minwl";
        this.value = 0;
    }

    @Override
    public int getInt() { return value; }

    @Override
    public boolean getBool() { return true; }

    @Override
    public String getString() { return ""; }

    public String getHelp() {
        return String.format("\t%-5s\t%s", commandlineSwitch, "Minimum word length");
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
