import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum Switch {

    DICT_FILE("-d"),
    MIN_WORD_LENGTH("-minwl"),
    MAX_WORD_LENGTH("-maxwl"),
    EXCLUDE_FROM_DICT_FILE("-ef"),
    MAX_RESULTS("-mr"),
    TIMEOUT("-to"),
    MAX_WORDS("-mw"),
    EXCLUDE_DUPLICATES("-ed"),
    RESTRICT_PERMUTATIONS("-rp"),
    START_FROM("-sf"),
    INCLUDE_WORD("-iw"),
    EXCLUDE_WORD("-ew"),
    INCLUDE_WORD_WITH_SUFFIX("-iws"),
    HELP("-h"),
    NO_OPTION("");

    private final String label;

    private static final Map<String, Switch> ENUM_MAP;

    Switch(String label) {
        this.label = label;
    }

    public String getLabel() { return this.label; }

    static {
        Map<String, Switch> map = new HashMap<>();

        for (Switch s : Switch.values())
            map.put(s.getLabel(), s);

        ENUM_MAP = Collections.unmodifiableMap(map);
    }

    public static Switch get(String name) {
        if (ENUM_MAP.containsKey(name))
            return ENUM_MAP.get(name.toLowerCase());
        else
            return NO_OPTION;
    }

    @Override
    public String toString() { return this.label; }
}