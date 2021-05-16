import java.util.*;

public class Parser {

    private int argIndex;
    private final String[] args;
    private final Map<Switch, Option> optionMap;

    public Parser(String[] args) {
        this.args = args;
        this.optionMap = new LinkedHashMap<>();
    }

    public Parser addOption(Option option) {
        optionMap.put(option.getSwitch(), option);

        return this;
    }

    public void parseArgs() {
        while (argIndex < args.length) {
            String arg    = args[argIndex];
            Switch option = Switch.get(arg);

            if (optionMap.containsKey(option))
                executeOptionAndUpdateIndex(option);
            else if (arg.startsWith("-"))
                invalidOptionSelected(arg);
            else
                executeOptionAndUpdateIndex(Switch.COLLECT_PHRASE);
        }
    }

    private void executeOptionAndUpdateIndex(Switch option) {
        argIndex = optionMap.get(option).execute(args, argIndex);
    }

    private void invalidOptionSelected(String arg) {
        System.err.println("Invalid option: " + arg);
        printOptions();
        System.exit(-1);
    }

    public Option getOption(Switch commandlineSwitch) {
        return optionMap.get(commandlineSwitch);
    }

    public void printOptions() {
        optionMap.forEach((key, value) -> System.out.println(value.getHelp()));
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

        System.out.println(parser.getOption(Switch.COLLECT_PHRASE).getString());
        System.out.println(parser.getOption(Switch.MAX_WORD_LENGTH).getInt());
        System.out.println(parser.getOption(Switch.RESTRICT_PERMUTATIONS).getBool());
    }
}
