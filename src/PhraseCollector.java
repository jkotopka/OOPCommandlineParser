import java.util.ArrayList;
import java.util.List;

public class PhraseCollector implements Option {

    private static String NEWLINE = System.lineSeparator();

    private String commandlineSwitch;
    private String description;
    private List<String> value;

    public PhraseCollector( ) {
        this.commandlineSwitch = "";
        this.description = "Collect phrase words";
        this.value = new ArrayList<>();
    }

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public String getString() { return String.join(" ", value); }

    @Override
    public String getHelp() {
        return String.format("\t%-5s\t%s", commandlineSwitch, description);
    }

    @Override
    public String getSwitch() { return commandlineSwitch; }

    @Override
    public int execute(String[] args, int argIndex) {
        while (argIndex < args.length) {
            value.add(args[argIndex++]);
        }
        return argIndex;
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
