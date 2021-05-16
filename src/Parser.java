import java.util.*;

public class Parser {

    private final String[] args;
    private final Map<String, Option> optionMap;

    public Parser(String[] args) {
        this.args = args;
        this.optionMap = new LinkedHashMap<>();
    }

    public Parser addOption(Option option) { ;
        optionMap.put(option.getSwitch(), option);

        return this;
    }

    public void parseArgs() {
        int argIndex = 0;

        while (argIndex < args.length) {
            String arg = args[argIndex];

            if (optionMap.containsKey(arg)) {
                argIndex = optionMap.get(arg).execute(args, argIndex);
            } else if (arg.startsWith("-")) {
                System.err.println("Invalid option: " + arg);
                printOptions();
                System.exit(-1);
            } else {
                // XXX: PhraseCollector has "" (blank String) for its key
                argIndex = optionMap.get("").execute(args, argIndex);
            }
        }
    }

    public Option getOption(String commandlineSwitch) {
        return optionMap.get(commandlineSwitch);
    }

    public void printOptions() {
        optionMap.forEach((key, value) -> value.getHelp());
    }

    public void printValues() {
        optionMap.forEach((key, value) -> System.out.println(value));
    }

    public void printState() {
        optionMap.forEach((key, value) -> System.out.println(value.getState()));
    }

    public static void main(String[] args) {
        Parser parser = new Parser(args)
            .addOption(new MaxWordLen())
            .addOption(new MinWordLen())
            .addOption(new RestrictPermutations())
            .addOption(new ExcludeDuplicates())
            .addOption(new PhraseCollector());

        parser.parseArgs();

        parser.printOptions();
        parser.printValues();
        parser.printState();

        System.out.println(parser.getOption("").getString());
    }
}
