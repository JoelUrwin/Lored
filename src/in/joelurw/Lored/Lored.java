package in.joelurw.Lored;

import in.joelurw.Lored.commands.Lore;
import in.joelurw.Lored.events.LoreScrollApplyEvent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Lored extends JavaPlugin {

    public static Lored getPlugin() {
        return plugin;
    }

    private static Lored plugin;

    @Override
    public void onEnable(){
        plugin = this;
        ItemManagement.init();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Lored by JoelUrwin] : Enabled!");
        getServer().getPluginManager().registerEvents(new LoreScrollApplyEvent(), this);
        getCommand("lore").setExecutor(new Lore());

    }

    @Override
    public void onDisable(){}


}
