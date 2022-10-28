package in.joelurw.Lored;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class ItemManagement {

    public static ItemStack lore_scroll;

    public static void init(){

        CreateLoreScroll();

    }

    private static void CreateLoreScroll() {
        ItemStack item = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer data = meta.getPersistentDataContainer();
        data.set(new NamespacedKey(Lored.getPlugin(), "lore-scroll"), PersistentDataType.STRING, "true");
        meta.setDisplayName(Lang.LORE_SCROLL_TITLE.toString());
        List<String> lore = new ArrayList<>();
        lore.add(Lang.LORE_SCROLL_DESC.toString());
        meta.setLore(lore);
        item.setItemMeta(meta);
        lore_scroll = item;

    }

}
