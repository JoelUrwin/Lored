package in.joelurw.Lored.commands;

import in.joelurw.Lored.ItemManagement;
import in.joelurw.Lored.Lang;
import in.joelurw.Lored.Config;
import in.joelurw.Lored.Lored;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;


public class Lore implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {


        if(sender instanceof Player){

            Player player = (Player) sender;

            if(args.length > 0){
                if(args[0].equalsIgnoreCase("edit") || args[0].equalsIgnoreCase("line")){
                    try{
                        ItemStack item = player.getInventory().getItemInMainHand();
                        ItemMeta meta = item.getItemMeta();
                        PersistentDataContainer itemdata = meta.getPersistentDataContainer();

                        if (!itemdata.has(new NamespacedKey(Lored.getPlugin(), "lored"), PersistentDataType.STRING)){

                            player.sendMessage(Lang.UNAPPLIED.toString());
                            return false;
                        }
                        if (itemdata.has(new NamespacedKey(Lored.getPlugin(), "lore-lock"), PersistentDataType.STRING)){

                            player.sendMessage(Lang.ALREADY_LOCKED.toString());
                            return false;
                        }

                        if(args.length == 1 || args.length == 2){
                            player.sendMessage(Lang.EMPTY_INPUT.toString());
                        }else{
                            if(Integer.parseInt(args[1]) > Config.LINE_LIMIT.castInt()){
                                player.sendMessage(Lang.LINE_LIMIT.toString());
                                return false;
                            }

                            if(Integer.parseInt(args[1]) <= 0){
                                player.sendMessage(Lang.INCORRECT_LINE.toString());
                                return false;
                            }


                            List<String> lore = meta.getLore();
                            String obj = Collections.max(lore);
                            int maxloreindex = lore.indexOf(obj) + 2;


                            String text = "";
                            for (int i = 2; i < args.length; i++) {
                                text += args[i] + " ";
                            }
                            text.trim();

                            int textLength = text.length();

                            if(textLength > Config.LORE_CHAR_LIMIT.castInt()){
                                player.sendMessage(Lang.CHAR_LIMIT.toString());
                                return false;
                            }

                            String convertedtext = text.replaceAll("&", "§");
                            int real_line = Integer.parseInt(args[1]) - 1;


                                try{
                                lore.set(real_line, convertedtext);
                                meta.setLore(lore);
                                player.sendMessage(String.format(Lang.CHANGED_LINE.toString(),Integer.parseInt(args[1]), convertedtext));
                                item.setItemMeta(meta);
                                player.updateInventory();
                                return true;
                                }
                                catch(IndexOutOfBoundsException e) {
                                    lore.add(convertedtext);
                                    meta.setLore(lore);
                                    player.sendMessage(String.format(Lang.CHANGED_LINE.toString(), Integer.parseInt(args[1]), convertedtext));
                                    item.setItemMeta(meta);
                                    player.updateInventory();
                                   return true;
                                }}

                        } catch (NumberFormatException e){
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "Incorrect Number.");
                        return false;

                    }
                }
                if(args[0].equalsIgnoreCase("clear")){
                    ItemStack item = player.getInventory().getItemInMainHand();
                    ItemMeta meta = item.getItemMeta();
                    PersistentDataContainer itemdata = meta.getPersistentDataContainer();

                    if (!itemdata.has(new NamespacedKey(Lored.getPlugin(), "lored"), PersistentDataType.STRING)){

                        player.sendMessage(Lang.UNAPPLIED.toString());
                        return false;
                    }

                    if (itemdata.has(new NamespacedKey(Lored.getPlugin(), "lore-lock"), PersistentDataType.STRING)){

                        player.sendMessage(Lang.ALREADY_LOCKED.toString());
                        return false;
                    }

                    meta.setDisplayName(item.getType().name());
                    List<String> lore = new ArrayList<>();
                    lore.add(Lang.CLEARED.toString());
                    meta.setLore(lore);
                    item.setItemMeta(meta);
                    player.sendMessage(Lang.CLEARED.toString());
                    player.updateInventory();
                }
                if(args[0].equalsIgnoreCase("lock")){
                    ItemStack item = player.getInventory().getItemInMainHand();
                    ItemMeta meta = item.getItemMeta();
                    PersistentDataContainer itemdata = meta.getPersistentDataContainer();

                    if (!itemdata.has(new NamespacedKey(Lored.getPlugin(), "lored"), PersistentDataType.STRING)){

                        player.sendMessage(Lang.UNAPPLIED.toString());
                        return false;
                    }
                    if (itemdata.has(new NamespacedKey(Lored.getPlugin(), "lore-lock"), PersistentDataType.STRING)){

                        player.sendMessage(Lang.ALREADY_LOCKED.toString());
                        return false;
                    }

                    else{
                        itemdata.set(new NamespacedKey(Lored.getPlugin(), "lore-lock"), PersistentDataType.STRING, "true");
                        item.setItemMeta(meta);
                        player.sendMessage(Lang.LOCKED.toString());
                        return true;
                    }

                }
                if(args[0].equalsIgnoreCase("title")){
                    ItemStack item = player.getInventory().getItemInMainHand();
                    ItemMeta meta = item.getItemMeta();
                    PersistentDataContainer itemdata = meta.getPersistentDataContainer();

                    if (!itemdata.has(new NamespacedKey(Lored.getPlugin(), "lored"), PersistentDataType.STRING)){

                        player.sendMessage(Lang.UNAPPLIED.toString());
                        return false;
                    }
                    if (itemdata.has(new NamespacedKey(Lored.getPlugin(), "lore-lock"), PersistentDataType.STRING)){

                        player.sendMessage(Lang.ALREADY_LOCKED.toString());
                        return false;
                    }

                    if(args.length == 1){
                        player.sendMessage(Lang.NO_TITLE.toString());
                        return false;
                    }else{

                    String message = "";
                    for (int i = 1; i < args.length; i++) {
                        message  += args[i] + " ";
                    }
                    message = message.trim();
                    int textLength = message.length();
                    if(textLength > Config.TITLE_CHAR_LIMIT.castInt()){
                            player.sendMessage(Lang.CHAR_LIMIT.toString());
                            return false;
                        }
                    meta.setDisplayName(message.replaceAll("&", "§"));
                    player.sendMessage(String.format(Lang.CHANGED_TITLE.toString(), message.replaceAll("&", "§")));
                    item.setItemMeta(meta);
                    player.updateInventory();
                    return true;
                    }
                }

            }else{
                if(player.hasPermission("lore.access_editor")){
                    player.sendMessage(ChatColor.LIGHT_PURPLE + " *** Lore Scroll Plugin by Joel *** ");
                    player.sendMessage(ChatColor.LIGHT_PURPLE +  "Lored Commands");
                    player.sendMessage(ChatColor.GRAY + "/lore edit (line number) (text)");
                    player.sendMessage(ChatColor.GRAY + "Changes the lore of an item that is loreable at a certain line.");
                    player.sendMessage(ChatColor.GRAY + "/lore title (text)");
                    player.sendMessage(ChatColor.GRAY + "Changes the title of a loreable item.");
                    player.sendMessage(ChatColor.GRAY + "Lore Lines and Titles can use §r§2Color!");
                    return true;
                }else{
                    player.sendMessage(ChatColor.LIGHT_PURPLE + "Not enough permissions! (you require permission node lore.access_editor)");
                    return false;
                }

            }


        }

        if (!(sender instanceof Player)){

        if(args[0].equalsIgnoreCase("give")){
            Bukkit.getServer().getConsoleSender().sendMessage("Debug from Joel");
            if(!(Bukkit.getPlayer(args[1]) == null)) {
                if (args[2] == null){return false;}
                Player target = Bukkit.getPlayer(args[1]);

                    String Amt = args[2];
                    int AmtInt = Integer.parseInt(Amt);
                    ItemStack scroll = new ItemStack(ItemManagement.lore_scroll);
                    scroll.setAmount(AmtInt);

                target.getInventory().addItem(scroll);
                target.sendMessage(ChatColor.LIGHT_PURPLE + String.format("You have recieved %d Lore Scroll.", AmtInt));
                Bukkit.getServer().getConsoleSender().sendMessage(String.format("Gave %s %d Lore Scroll", target.getName(), AmtInt));

                return true;
            }

            else{Bukkit.getServer().getConsoleSender().sendMessage("Invalid Player Name.");}
        }}

        return true;
    }
}


