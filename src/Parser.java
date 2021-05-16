import java.util.*;

public class Parser {

    private final String[] args;
    private final List<Option> optionList;
    private final Map<String, Option> optionMap;

    public Parser(String[] args) {
        this.args = args;
        this.optionList = new ArrayList<>();
        this.optionMap = new HashMap<>();
    }

    public Parser addOption(Option option) {
        optionList.add(option);
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
                System.exit(-1);
            } else {
                // PhraseCollector has a "" blank string for its key
                argIndex = optionMap.get("").execute(args, argIndex);
            }
        }
    }

    public Option getOption(String commandlineSwitch) {
        return optionMap.get(commandlineSwitch);
    }

    public void printOptions() {
        optionList.forEach(option -> { System.out.println(option.getHelp()); });
    }

    public void printValues() {
        for (Option o : optionList) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        Parser parser = new Parser(args)
            .addOption(new MaxWordLen())
            .addOption(new MinWordLen())
            .addOption(new PhraseCollector());

        parser.parseArgs();

        parser.printOptions();
        parser.printValues();

        System.out.println(parser.getOption("-maxwl").getInt());
        System.out.println(parser.getOption("").getString());
    }
}
