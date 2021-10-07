package pl.al13n.vouchers.object;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.List;

public class Voucher {

    private final Material itemType;
    private final String itemName;
    private final List<String> itemLore;
    private final List<String> itemEnchants;
    private final int itemAmount;
    private long drop;
    private final long delay;
    private final String command;
    private final String world;

    public Voucher(Material itemType, String itemName, List<String> itemLore, List<String> itemEnchants, int itemAmount, long delay, String command, String world) {
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemLore = itemLore;
        this.itemEnchants = itemEnchants;
        this.itemAmount = itemAmount;
        this.delay = delay;
        this.drop = System.currentTimeMillis() + delay;
        this.command = command;
        this.world = world;
    }

    public String getWorld(){
        return this.world;
    }

    public List<Enchantment> getItemEnchantments(){
        List<Enchantment> enchants = new ArrayList<>();
        for(String s : this.itemEnchants){
            String[] split = s.split(":");
            enchants.add(Enchantment.getByName(split[0].toUpperCase()));
        }
        return enchants;
    }

    public String getCommand(){
        return this.command;
    }

    public boolean canDrop(){
        return this.drop < System.currentTimeMillis();
    }

    public void setDrop(long time){
        this.drop = time;
    }

    public long getDelay(){
        return this.delay;
    }

    public Material getItemType() {
        return itemType;
    }

    public String getItemName() {
        return itemName;
    }

    public List<String> getItemLore() {
        return itemLore;
    }

    public List<String> getItemEnchants() {
        return itemEnchants;
    }

    public int getItemAmount() {
        return itemAmount;
    }
}
