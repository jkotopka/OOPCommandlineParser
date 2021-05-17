package org.kotopka.CommandlineParser;

public interface Parser {

    Parser addOption(Option option);
    Parser addOptions(Option... optionArgs);
    boolean hasNextArg();
    String getNextArg();
    Option getOption(Switch commandlineSwitch);
    void printOptions();
    void printValues();
    void printState();
    void parseArgs();

}
