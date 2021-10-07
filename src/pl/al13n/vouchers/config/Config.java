package pl.al13n.vouchers.config;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import pl.al13n.vouchers.manager.VoucherManager;
import pl.al13n.vouchers.object.Voucher;
import pl.al13n.vouchers.util.YamlUtil;

import java.util.List;

public class Config {

    private final YamlUtil yamlUtil;
    private final VoucherManager voucherManager;
    public int border;
    public List<String> voucherInfo, voucherUseInfo;

    public Config(YamlUtil yamlUtil, VoucherManager voucherManager) {
        this.yamlUtil = yamlUtil;
        this.voucherManager = voucherManager;
    }

    public void load(){
        FileConfiguration c = this.yamlUtil.getYaml("config");
        this.border = c.getInt("border");
        this.voucherInfo = c.getStringList("voucherInfo");
        this.voucherUseInfo = c.getStringList("voucherUseInfo");
        for(String s : c.getConfigurationSection("vouchers").getKeys(false)){
            Material itemType = Material.valueOf(c.getString("vouchers." + s + ".item.type").toUpperCase());
            String itemName = c.getString("vouchers." + s + ".item.name");
            List<String> itemLore = c.getStringList("vouchers." + s + ".item.lore");
            int amount = c.getInt("vouchers." + s + ".item.amount");
            List<String> enchants = c.getStringList("vouchers." + s + ".item.enchants");
            int delay = c.getInt("vouchers." + s + ".delay");
            String command = c.getString("vouchers." + s + ".command");
            String world = c.getString("vouchers." + s + ".world");
            this.voucherManager.addVoucher(new Voucher(itemType, itemName, itemLore, enchants, amount, delay * 1000L, command, world));
        }
    }
}
