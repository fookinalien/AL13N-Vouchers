package pl.al13n.vouchers.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class ChatUtil {

    public static String fixColor(String text){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public static List<String> fixColor(List<String> list){
        List<String> fixed = new ArrayList<>();
        for(String s : list){
            fixed.add(fixColor(s));
        }
        return fixed;
    }
}
