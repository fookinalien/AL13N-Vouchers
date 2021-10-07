package pl.al13n.vouchers.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemUtil {

    public static void removeItemInHand(final Player player) {
        ItemStack itemStack = player.getItemInHand();
        if (itemStack.getAmount() == 1) {
            player.setItemInHand(null);
            return;
        }
        itemStack.setAmount(itemStack.getAmount() - 1);
        player.setItemInHand(itemStack);
    }
}
