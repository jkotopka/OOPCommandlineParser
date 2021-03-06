package org.kotopka.CommandlineParser;

import java.util.ArrayList;
import java.util.List;

public class PhraseCollector extends AbstractOption {

    private final List<String> value;

    public PhraseCollector( ) {
        this.commandlineSwitch = Switch.COLLECT_PHRASE;
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
    public int execute(Parser parser) {
        int argIndex = parser.getArgIndex();

        while (argIndex < parser.getArgs().size())
            value.add(parser.getArgs().get(argIndex++));

        return argIndex;
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
