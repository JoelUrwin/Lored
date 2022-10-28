package in.joelurw.Lored;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Lang {
    DEFAULT_LINE("defaultline",""),
    SUCCESS("success","Successfully applied the lore scroll."),
    BROADCAST("broadcast","%s just applied a lore scroll!"),
    STORE_URL("store-url",""),
    STORE_URL_HOVER("store-url-hover-message","Click here to go to our store!"),
    INAPPLICABLE("inapplicable ","This item already has a lore scroll applied!"),
    OVERSTACKED("overstacked-item","Only one item can be lored!"),
    UNAPPLIED("unapplied","This item has not had a lore scroll applied!"),
    ALREADY_LOCKED("already-locked","The lore scroll on this item has been locked!"),
    LINE_LIMIT("line-limit","You have reached the line limit! Max, "),
    EMPTY_INPUT("empty-input","Enter a lore line number and text."),
    INCORRECT_LINE("incorrect-line","Incorrect lore line."),
    CHAR_LIMIT("char-limit","Maximum Character Limit is "),
    NO_TITLE("no-title","Enter a title."),
    ADDED_LINE("added-line","Added line %s"),
    CHANGED_LINE("changed-line","Changed lore line %s to %s"),
    CHANGED_TITLE("changed-title","Changed title to %s"),
    CLEARED("cleared","Successfully cleared lore lines."),
    LOCKED("locked","Successfully locked the lore to your item!"),
    TITLE_CHANGE("title-change","Successfully changed title to %s"),
    LORE_SCROLL_TITLE("lore-scroll-title","§5Lore Scroll"),
    LORE_SCROLL_DESC("lore-scroll-description", "§fApply custom lore to your item!");

    private String path;
    private String def;
    private static YamlConfiguration LANG;

    /**
     * Lang enum constructor.
     * @param path The string path.
     * @param start The default string.
     */
    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }

    /**
     * Set the {@code YamlConfiguration} to use.
     * @param config The config to set.
     */
    public static void setFile(YamlConfiguration config) {
        LANG = config;
    }

    @Override
    public String toString() {
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def));
    }

    /**
     * Get the default value of the path.
     * @return The default value of the path.
     */
    public String getDefault() {
        return this.def;
    }

    /**
     * Get the path to the string.
     * @return The path to the string.
     */
    public String getPath() {
        return this.path;
    }
}