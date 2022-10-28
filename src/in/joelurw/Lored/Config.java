package in.joelurw.Lored;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Config {

    LINE_LIMIT("line_limit", 15),
    LORE_CHAR_LIMIT("lore_char_limit", 64),
    TITLE_CHAR_LIMIT("title_char_limit", 32),
    BROADCAST("broadcast", "True");

    private String path;
    private Object def;
    private static YamlConfiguration CONFIG;

    Config (String path, Object start) {
        this.path = path;
        this.def = start;
    }

    public static void setFile(YamlConfiguration config){CONFIG = config;}

    @Override
    public String toString() {
        return ChatColor.translateAlternateColorCodes('&', CONFIG.getString(this.path, def.toString()));
    }

    /**
     * Get the default value of the path.
     * @return The default value of the path.
     */
    public Object getDefault() {
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
