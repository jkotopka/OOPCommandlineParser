public abstract class AbstractOption implements Option {

    Switch commandlineSwitch;
    String description;

    @Override
    public String getHelp() {
        return String.format("\t%-5s\t%s", commandlineSwitch, description);
    }

    @Override
    public Switch getSwitch() {
        return commandlineSwitch;
    }

}
