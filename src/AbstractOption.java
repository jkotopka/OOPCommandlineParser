public abstract class AbstractOption implements Option {

    protected Switch commandlineSwitch;
    protected String description;

    @Override
    public String getHelp() {
        return String.format("\t%-5s\t%s", commandlineSwitch, description);
    }

    @Override
    public Switch getSwitch() {
        return commandlineSwitch;
    }

}
