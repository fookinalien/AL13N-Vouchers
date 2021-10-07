package pl.al13n.vouchers.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.al13n.vouchers.config.Config;
import pl.al13n.vouchers.manager.VoucherManager;
import pl.al13n.vouchers.object.Voucher;
import pl.al13n.vouchers.util.ChatUtil;
import pl.al13n.vouchers.util.ItemUtil;
import pl.al13n.vouchers.util.ListUtil;

import java.util.List;

public class PlayerInteractListener implements Listener {

    private final VoucherManager voucherManager;
    private final Config config;

    public PlayerInteractListener(VoucherManager voucherManager, Config config) {
        this.voucherManager = voucherManager;
        this.config = config;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        ItemStack itemStack = event.getItem();
        if(itemStack == null || !itemStack.hasItemMeta()){
            return;
        }
        Voucher voucher = this.voucherManager.getVoucher(itemStack);
        if(voucher == null){
            return;
        }
        Player player = event.getPlayer();
        ItemUtil.removeItemInHand(player);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), voucher.getCommand().replace("{player}", player.getName()));
        List<String> info = this.config.voucherUseInfo;
        info = ListUtil.replace(info, "{player}", player.getName());
        info = ListUtil.replace(info, "{name}", voucher.getItemName());
        List<String> finalInfo = info;
        Bukkit.getOnlinePlayers().forEach(all -> {
            for(String s : finalInfo){
                all.sendMessage(ChatUtil.fixColor(s));
            }
        });
    }
}
