package pl.al13n.vouchers;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.al13n.vouchers.config.Config;
import pl.al13n.vouchers.listener.PlayerInteractListener;
import pl.al13n.vouchers.manager.VoucherManager;
import pl.al13n.vouchers.task.DropTask;
import pl.al13n.vouchers.util.YamlUtil;

public class VouchersPlugin extends JavaPlugin {

    public void onEnable(){
        YamlUtil yamlUtil = new YamlUtil(this);
        VoucherManager voucherManager = new VoucherManager();
        Config config = new Config(yamlUtil, voucherManager);
        config.load();
        new DropTask(this, config, voucherManager).start();
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(voucherManager, config), this);
    }
}
