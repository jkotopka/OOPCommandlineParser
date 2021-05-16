import java.util.*;

public class Parser {

    private int argIndex;
    private final String[] args;
    private final Map<Switch, Option> options;

    public Parser(String[] args) {
        this.args = args;
        this.options = new LinkedHashMap<>();
    }

    public Parser addOption(Option option) {
        options.put(option.getSwitch(), option);

        return this;
    }

    public void parseArgs() {
        while (argIndex < args.length) {
            String arg    = args[argIndex];
            Switch option = Switch.get(arg);

            if (options.containsKey(option))
                executeOptionAndUpdateIndex(option);
            else if (arg.startsWith("-"))
                invalidOptionSelected(arg);
            else
                executeOptionAndUpdateIndex(Switch.COLLECT_PHRASE);
        }
    }

    private void executeOptionAndUpdateIndex(Switch option) {
        argIndex = options.get(option).execute(args, argIndex);
    }

    private void invalidOptionSelected(String arg) {
        System.err.println("Invalid option: " + arg);
        printOptions();
        System.exit(-1);
    }

    public Option getOption(Switch commandlineSwitch) {
        return options.get(commandlineSwitch);
    }

    public void printOptions() {
        options.forEach((key, value) -> System.out.println(value.getHelp()));
    }

    public void printValues() {
        options.forEach((key, value) -> System.out.println(value));
    }

    public void printState() {
        options.forEach((key, value) -> System.out.println(value.getState()));
    }

    public static void main(String[] args) {
        Parser parser = new Parser(args)
            .addOption(new MaxWordLen())
            .addOption(new MinWordLen())
            .addOption(new RestrictPermutations())
            .addOption(new ExcludeDuplicates())
            .addOption(new PhraseCollector());

        parser.parseArgs();

//        parser.printOptions();
//        parser.printValues();
        parser.printState();

//        System.out.println(parser.getOption(Switch.COLLECT_PHRASE).getString());
//        System.out.println(parser.getOption(Switch.MAX_WORD_LENGTH).getInt());
//        System.out.println(parser.getOption(Switch.RESTRICT_PERMUTATIONS).getBool());
    }
}
