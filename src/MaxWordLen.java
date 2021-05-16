public class MaxWordLen implements Option {

    private static String NEWLINE = System.lineSeparator();

    private String commandlineSwitch;
    private int value;

    public MaxWordLen( ) {
        this.commandlineSwitch = "-maxwl";
        this.value = Integer.MAX_VALUE;
    }

    @Override
    public int getInt() { return value; }

    @Override
    public boolean getBool() { return true; }

    @Override
    public String getString() { return ""; }

    public String getHelp() {
        return String.format("\t%-5s\t%s", commandlineSwitch, "Maximum word length");
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
