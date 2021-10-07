package pl.al13n.vouchers.manager;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.al13n.vouchers.object.Voucher;
import pl.al13n.vouchers.util.ChatUtil;

import java.util.HashSet;
import java.util.Set;

public class VoucherManager {

    private final Set<Voucher> vouchers = new HashSet<>();

    public Set<Voucher> getVouchers() {
        return this.vouchers;
    }

    public void addVoucher(Voucher voucher){
        this.vouchers.add(voucher);
    }

    public Voucher getVoucher(ItemStack itemStack){
        for(Voucher voucher : this.vouchers){
            ItemMeta itemMeta = itemStack.getItemMeta();
            if(voucher.getItemType().equals(itemStack.getType()) && ChatUtil.fixColor(voucher.getItemName()).equals(itemMeta.getDisplayName()) && ChatUtil.fixColor(voucher.getItemLore()).equals(itemMeta.getLore())){
                return voucher;
            }
        }
        return null;
    }
}
