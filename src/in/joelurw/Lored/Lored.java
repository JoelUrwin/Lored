package in.joelurw.Lored;

import in.joelurw.Lored.commands.Lore;
import in.joelurw.Lored.events.LoreScrollApplyEvent;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Lored extends JavaPlugin {

    public static YamlConfiguration LANG;
    public static File LANG_FILE;
    public static YamlConfiguration CONFIG;
    public static File CONFIG_FILE;

    public static Lored getPlugin() {
        return plugin;
    }

    private static Lored plugin;

    @Override
    public void onEnable(){
        plugin = this;
        loadLang();
        loadConfig();
        ItemManagement.init();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Lored by Asynchronized] : Enabled!");
        getCommand("lore").setExecutor(new Lore());
        getServer().getPluginManager().registerEvents(new LoreScrollApplyEvent(), this);
    }

    @Override
    public void onDisable(){}


    /**
     * Load the lang.yml file.
     *
     * @return The lang.yml config.
     */

    public FileConfiguration loadLang() {
        File lang = new File(getDataFolder(), "lang.yml");
        if (!lang.exists()) {
            try {
                getDataFolder().mkdir();
                lang.createNewFile();
                InputStream defConfigStream = this.getResource("lang.yml");
                if (defConfigStream != null) {
                    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(lang);
                    defConfig.save(lang);
                    Lang.setFile(defConfig);
                    return defConfig;
                }
            } catch(IOException e) {
                e.printStackTrace(); // So they notice
                this.setEnabled(false); // Without it loaded, we can't send them messages
            }
        }
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
        for(Lang item:Lang.values()) {
            if (conf.getString(item.getPath()) == null) {
                conf.set(item.getPath(), item.getDefault());
            }
        }
        Lang.setFile(conf);
        Lored.LANG = conf;
        Lored.LANG_FILE = lang;
        try {
            conf.save(getLangFile());
        } catch(IOException e) {
            e.printStackTrace();
        }
        return conf;
    }

    public FileConfiguration loadConfig() {
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            try {
                getDataFolder().mkdir();
                config.createNewFile();
                InputStream defConfigStream = this.getResource("config.yml");
                if (defConfigStream != null) {
                    YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(config);
                    defConfig.save(config);
                    Config.setFile(defConfig);
                    return defConfig;
                }
            } catch(IOException e) {
                e.printStackTrace(); // So they notice
                this.setEnabled(false); // Without it loaded, we can't send them messages
            }
        }
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(config);
        for(Config item:Config.values()) {
            if (conf.getString(item.getPath()) == null) {
                conf.set(item.getPath(), item.getDefault());
            }
        }
        Config.setFile(conf);
        Lored.CONFIG = conf;
        Lored.CONFIG_FILE = config;
        try {
            conf.save(getConfigFile());
        } catch(IOException e) {
            e.printStackTrace();
        }
        return conf;
    }

    public File getLangFile() {
        return LANG_FILE;
    }
    public File getConfigFile(){return CONFIG_FILE;}

    public YamlConfiguration getLang() {
        return LANG;
    }
    public YamlConfiguration getConfig(){return CONFIG;}

}
