package org.kotopka.CommandlineParser;

import java.util.List;

public interface Parser {

    Parser addOption(Option option);
    Parser addOptions(Option... optionArgs);
    int getArgIndex();
    List<String> getArgs();
    Option getOption(Switch commandlineSwitch);
    void printOptions();
    void printValues();
    void printState();
    void parseArgs();
}
