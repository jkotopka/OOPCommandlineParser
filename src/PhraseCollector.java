import java.util.ArrayList;
import java.util.List;

public class PhraseCollector implements Option {

    private static String NEWLINE = System.lineSeparator();

    private String commandlineSwitch;
    private List<String> value;

    public PhraseCollector( ) {
        this.commandlineSwitch = "";
        this.value = new ArrayList<>();
    }

    @Override
    public int getInt() { return 0; }

    @Override
    public boolean getBool() { return true; }

    @Override
    public String getString() { return String.join(" ", value); }

    public String getHelp() {
        return String.format("\t%-5s\t%s", commandlineSwitch, "Collect phrase words");
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
