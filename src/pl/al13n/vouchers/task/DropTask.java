package pl.al13n.vouchers.task;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import pl.al13n.vouchers.VouchersPlugin;
import pl.al13n.vouchers.builder.ItemBuilder;
import pl.al13n.vouchers.config.Config;
import pl.al13n.vouchers.manager.VoucherManager;
import pl.al13n.vouchers.object.Voucher;
import pl.al13n.vouchers.util.ChatUtil;
import pl.al13n.vouchers.util.ListUtil;
import pl.al13n.vouchers.util.RandomUtil;

import java.util.List;

public class DropTask implements Runnable {

    private final VouchersPlugin plugin;
    private final Config config;
    private final VoucherManager voucherManager;

    public DropTask(VouchersPlugin plugin, Config config, VoucherManager voucherManager) {
        this.plugin = plugin;
        this.config = config;
        this.voucherManager = voucherManager;
    }

    @Override
    public void run() {
        for(Voucher voucher : this.voucherManager.getVouchers()){
            if(voucher.canDrop()){
                int x = RandomUtil.getRandInt(-this.config.border, this.config.border);
                int z = RandomUtil.getRandInt(-this.config.border, this.config.border);
                World world = Bukkit.getWorld(voucher.getWorld());
                int y = world.getHighestBlockYAt(x, z);
                ItemBuilder item = new ItemBuilder(voucher.getItemType());
                item.setAmount(voucher.getItemAmount());
                item.setName(voucher.getItemName());
                item.setLore(voucher.getItemLore());
                for(String enchant : voucher.getItemEnchants()){
                    String[] split = enchant.split(":");
                    Enchantment enchantment = Enchantment.getByName(split[0].toUpperCase());
                    item.addEnchant(enchantment, Integer.parseInt(split[1]));
                }
                world.dropItem(new Location(world, x, y + 1, z, 0, 0), item.build());
                voucher.setDrop(System.currentTimeMillis() + voucher.getDelay());
                List<String> info = this.config.voucherInfo;
                info = ListUtil.replace(info, "{x}", String.valueOf(x));
                info = ListUtil.replace(info, "{y}", String.valueOf(y));
                info = ListUtil.replace(info, "{z}", String.valueOf(z));
                info = ListUtil.replace(info, "{name}", voucher.getItemName());
                info = ListUtil.replace(info, "{world}", voucher.getWorld());
                List<String> finalInfo = info;
                Bukkit.getOnlinePlayers().forEach(all -> {
                    for(String s : finalInfo){
                        all.sendMessage(ChatUtil.fixColor(s));
                    }
                });
            }
        }
    }

    public void start(){
        Bukkit.getScheduler().runTaskTimer(this.plugin, this, 20L, 20L);
    }
}
