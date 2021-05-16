public class RestrictPermutations extends AbstractOption{

    private boolean value;

    public RestrictPermutations( ) {
        this.commandlineSwitch = Switch.RESTRICT_PERMUTATIONS;
        this.description = "Restrict Permutations";
    }

    @Override
    public String getState() {
        return description + ": " + value;
    }

    @Override
    public boolean getBool() { return value; }

    @Override
    public int execute(String[] args, int argIndex) {
        value = true;

        return ++argIndex;
    }

    @Override
    public String toString() {
        return commandlineSwitch + " " + value;
    }

}
