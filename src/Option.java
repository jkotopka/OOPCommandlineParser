public interface Option {
    Switch getSwitch();
    String getHelp();
    String getState();
    int execute(String[] args, int argIndex);
    default int getInt() { return  0; }
    default boolean getBool() { return false; }
    default String getString() { return ""; }
}
