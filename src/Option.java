public interface Option {
    String getSwitch();
    String getHelp();
    int execute(String[] args, int argIndex);
    int getInt();
    boolean getBool();
    String getString();
}
