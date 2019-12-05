package io.github.qyvlik.siuone.common.utils;

/**
 * https://segmentfault.com/a/1190000010516708
 */
public class Base62 {
    public static final String BASE_62_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final int BASE = BASE_62_CHAR.length();

    /**
     * 将 base62 转换为 整数
     *
     * @param str base62
     * @return 整数
     */
    public static long toBase10(String str) {
        //从右边开始
        return toBase10(new StringBuilder(str).reverse().toString().toCharArray());
    }

    /**
     * 将整数转换为 base62
     *
     * @param i 整数
     * @return base62
     */
    public static String fromBase10(long i) {
        if (i == 0) {
            return "a";
        }
        StringBuilder sb = new StringBuilder("");
        while (i > 0) {
            i = fromBase10(i, sb);
        }
        return sb.reverse().toString();
    }

    private static long toBase10(char[] chars) {
        long n = 0;
        int pow = 0;
        for (char item : chars) {
            n += toBase10(BASE_62_CHAR.indexOf(item), pow);
            pow++;
        }
        return n;
    }

    private static long toBase10(int n, int pow) {
        return n * (long) Math.pow(BASE, pow);
    }

    private static long fromBase10(long i, final StringBuilder sb) {
        int rem = (int) (i % BASE);
        sb.append(BASE_62_CHAR.charAt(rem));
        return i / BASE;
    }
}
