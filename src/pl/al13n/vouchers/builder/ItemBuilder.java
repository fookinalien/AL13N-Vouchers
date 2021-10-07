package pl.al13n.vouchers.builder;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.al13n.vouchers.util.ChatUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemBuilder {

    private int amount;
    private short data;
    private Map<Enchantment, Integer> enchants;
    private List<String> lore;
    private final Material mat;
    private String name;

    public ItemBuilder(final Material mat) {
        this(mat, 1);
    }

    private ItemBuilder(final Material mat, final int amount) {
        this(mat, amount, (short)0);
    }

    private ItemBuilder(final Material mat, final int amount, final short data) {
        this.data = 0;
        this.lore = new ArrayList<>();
        this.enchants = new HashMap<>();
        this.name = null;
        this.lore = new ArrayList<>();
        this.enchants = new HashMap<>();
        this.mat = mat;
        this.amount = amount;
        this.data = data;
    }

    public void addEnchant(final Enchantment enchant, final int i) {
        this.enchants.put(enchant, i);
    }

    public ItemBuilder addEnchantment(final Enchantment enchant, final int level) {
        this.enchants.remove(enchant);
        this.enchants.put(enchant, level);
        return this;
    }

    public ItemStack build() {
        final ItemStack is = new ItemStack(this.mat, this.amount, this.data);
        final ItemMeta im = is.getItemMeta();
        if (this.name != null) {
            im.setDisplayName(ChatUtil.fixColor(this.name));
        }
        if (this.lore != null && !this.lore.isEmpty()) {
            im.setLore(ChatUtil.fixColor(this.lore));
        }
        for (final Map.Entry<Enchantment, Integer> e : this.enchants.entrySet()) {
            im.addEnchant(e.getKey(), e.getValue(), true);
        }
        is.setItemMeta(im);
        return is;
    }

    public ItemBuilder setAmount(final int amount) {
        this.amount = amount;
        return this;
    }

    public void setLore(final List<String> lore) {
        this.lore = lore;
    }

    public ItemBuilder setName(final String name) {
        this.name = name;
        return this;
    }
}
