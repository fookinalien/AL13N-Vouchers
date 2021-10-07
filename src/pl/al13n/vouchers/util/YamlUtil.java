package pl.al13n.vouchers.util;

import org.bukkit.configuration.file.YamlConfiguration;
import pl.al13n.vouchers.VouchersPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


public class YamlUtil {

    private final VouchersPlugin plugin;

    public YamlUtil(VouchersPlugin plugin){
        this.plugin = plugin;
    }

    public YamlConfiguration getYaml(final String name) {
        return YamlConfiguration.loadConfiguration(Objects.requireNonNull(getFile(name)));
    }

    public File getFile(final String name) {
        final File file = new File(this.plugin.getDataFolder(), name + ".yml");
        if (!file.exists()) {
            final InputStream is = this.plugin.getResource(name + ".yml");
            if (is != null) {
                this.plugin.saveResource(name + ".yml", true);
            }
            else {
                try {
                    file.createNewFile();
                    return null;
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return file;
    }
}