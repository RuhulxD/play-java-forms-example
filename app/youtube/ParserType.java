package youtube;

public enum ParserType {
    EPISODE,
    SEASON,
    TITLE,
    ACTORS,
    NAME;
    private static final ParserType DEFAULT = ParserType.TITLE;

    /**
     * Is the supplied ResultType the default
     *
     * @param resultType
     * @return
     */
    public static boolean isDefault(ParserType resultType) {
        return resultType.equals(DEFAULT);
    }

    /**
     * Get the default ResultType
     *
     * @return
     */
    public static ParserType getDefault() {
        return DEFAULT;
    }
}
