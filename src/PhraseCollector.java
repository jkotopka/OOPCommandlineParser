import java.util.ArrayList;
import java.util.List;

public class PhraseCollector extends AbstractOption {

    private final List<String> value;

    public PhraseCollector( ) {
        this.commandlineSwitch = Switch.NO_OPTION;
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
    public int execute(String[] args, int argIndex) {
        while (argIndex < args.length)
            value.add(args[argIndex++]);

        return argIndex;
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
