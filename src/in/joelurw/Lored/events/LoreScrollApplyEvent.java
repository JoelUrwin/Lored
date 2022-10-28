package in.joelurw.Lored.events;

import in.joelurw.Lored.Lang;
import in.joelurw.Lored.Lored;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import in.joelurw.Lored.Config;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.List;

public class LoreScrollApplyEvent implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        final Player p = (Player) e.getWhoClicked();
        if (e.getAction() == InventoryAction.SWAP_WITH_CURSOR) {
            // Checks if item ontop is actually the lore scroll.
            if (e.getCursor() == null) return;
            if (e.getCurrentItem() == null) return;
            if (!e.getCursor().hasItemMeta()) return;
            if (e.getCursor().getItemMeta().getPersistentDataContainer().isEmpty()) return;
            // Items that you're appling the scroll to.
            ItemStack applicant = e.getCurrentItem();
            ItemMeta applicantmeta = e.getCurrentItem().getItemMeta();
            PersistentDataContainer applicantData = applicantmeta.getPersistentDataContainer();
            // The Lore Scroll Itself the item meta and item stack itself.
            ItemMeta ci = e.getCursor().getItemMeta();
            ItemStack cii = e.getCursor();
            if (ci.getPersistentDataContainer().isEmpty()) return;
            if (applicant.getAmount() > 1){p.sendMessage(Lang.OVERSTACKED.toString());return;}
            PersistentDataContainer data = ci.getPersistentDataContainer();

            if (applicantData.has(new NamespacedKey(Lored.getPlugin(), "lored"), PersistentDataType.STRING)){

                p.sendMessage(Lang.INAPPLICABLE.toString());
                return;
            }

            if(data.has(new NamespacedKey(Lored.getPlugin(), "lore-scroll"), PersistentDataType.STRING)){


                List<String> lore = new ArrayList<>();
                lore.add(Lang.LORE_SCROLL_DESC.toString());
                applicantmeta.setLore(lore);
                applicantData.set(new NamespacedKey(Lored.getPlugin(), "lored"), PersistentDataType.STRING, "true");
                applicant.setItemMeta(applicantmeta);

                if(Config.CONFIRMATION_SOUND.castBool()){p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10f, 10f);}

                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(Lang.SUCCESS.toString()));
                if(Config.BROADCAST.castBool()){

                for (Player player : Bukkit.getServer().getOnlinePlayers()){

                    TextComponent message = new TextComponent(String.format(Lang.BROADCAST.toString(), p.getName()));

                    message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, Lang.STORE_URL.toString()));
                    message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Lang.STORE_URL_HOVER.toString()).color(net.md_5.bungee.api.ChatColor.BOLD).italic(false).create()));
                    player.spigot().sendMessage(message);
                }
                cii.setAmount(cii.getAmount() - 1);
            }}



        }
    }
}

