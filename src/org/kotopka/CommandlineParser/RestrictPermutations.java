package org.kotopka.CommandlineParser;

public class RestrictPermutations extends AbstractOption{

    private boolean value;

    public RestrictPermutations( ) {
        this.commandlineSwitch = Switch.RESTRICT_PERMUTATIONS;
        this.description = "Restrict permutations";
        this.extendedHelpMessage =
                "Restrict permutations -- " +
                "This option attempts to restrict the number of permutations " +
                "the algorithm uses to build anagrams. This option does not take additional parameters. " + NEWLINE +
                "\tUsage: " + commandlineSwitch;
    }

    @Override
    public String getExtendedHelp() { return extendedHelpMessage; }

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public boolean getBool() { return value; }

    @Override
    public int execute(Parser parser) {
        value = true;

        return parser.getArgIndex() + 1;
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
