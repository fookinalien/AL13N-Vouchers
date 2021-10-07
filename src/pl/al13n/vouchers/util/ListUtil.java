package pl.al13n.vouchers.util;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtil {

    public static List<String> replace(final List<String> list, final String variable, final String to) {
        return list.stream().map(s -> s.replace(variable, to)).collect(Collectors.toList());
    }
}
