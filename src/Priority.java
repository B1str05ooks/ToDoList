public enum Priority
{
        LOW         ("Low",      1),
        MEDIUM      ("Medium",   2),
        HIGH        ("High",     3),
        CRITICAL    ("Critical", 4);

    private final String displayName;
    private final int level;

    Priority(String displayName, int level)
    {
        this.displayName = displayName;
        this.level = level;
    }

    public String getDisplayName(){return displayName;}

    public int getLevel() {return level;}

    public static Priority fronInt(int choice)
    {
        return switch (choice)
        {
            case 1 -> LOW;
            case 2 -> MEDIUM;
            case 3 -> HIGH;
            case 4 -> CRITICAL;
            default -> MEDIUM;
        };
    }
}
