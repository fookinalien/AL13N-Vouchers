package pl.al13n.vouchers.util;

import org.apache.commons.lang3.Validate;

import java.util.Random;

public class RandomUtil {

    private static final Random rand = new Random();

    public static int getRandInt(final int min, final int max) throws IllegalArgumentException {
        Validate.isTrue(max > min, "Max can't be smaller than min!");
        return RandomUtil.rand.nextInt(max - min + 1) + min;
    }
}

