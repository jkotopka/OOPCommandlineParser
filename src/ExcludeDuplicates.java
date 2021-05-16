public class ExcludeDuplicates implements Option {

    private static String NEWLINE = System.lineSeparator();

    private String commandlineSwitch;
    private String description;
    private boolean value;

    public ExcludeDuplicates( ) {
        this.commandlineSwitch = "-ed";
        this.description = "Exclude Duplicates";
    }

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public boolean getBool() { return value; }

    @Override
    public String getHelp() {
        return String.format("\t%-5s\t%s", commandlineSwitch, description);
    }

    @Override
    public String getSwitch() { return commandlineSwitch; }

    @Override
    public int execute(String[] args, int argIndex) {
        value = true;
        return ++argIndex;
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
