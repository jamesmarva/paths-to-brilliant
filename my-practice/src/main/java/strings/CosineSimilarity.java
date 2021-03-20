package strings;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * 余弦相似度
 *
 *
 * @author 王涵威
 * @date 21.3.20 15:13
 */
public class CosineSimilarity {


    /**
     * 获取两个字符串的相似度
     * @param src 字符串a
     * @param dst 字符串b
     * @return 相似度
     */
    public BigDecimal getSimilarity(String src, String dst) {
        char[] srcChars = src.toCharArray();
        char[] dstChars = dst.toCharArray();
        Map<Character, Integer> srcMap = new HashMap<>();
        Map<Character, Integer> dstMap = new HashMap<>();

        for (char i : srcChars) {
            srcMap.computeIfAbsent(i, o -> 0);
            srcMap.computeIfPresent(i, (k, v) -> v + 1);
        }
        for (char i : dstChars) {
            dstMap.computeIfAbsent(i, k -> 0);
            dstMap.computeIfPresent(i, (k, v) -> v + 1);
        }

//        分子 （分子在上部分，分母在下部分）
        BigDecimal numerator = new BigDecimal("0");

        for (char i : srcMap.keySet()) {
            if (dstMap.containsKey(i)) {
                numerator = numerator.add(new BigDecimal(String.valueOf(srcMap.get(i) * dstMap.get(i))));
            }
        }

        double srcDou = 0.0d;
        double dstDou = 0.0d;
        for (Character i : srcMap.keySet()) {
            srcDou += Math.pow(srcMap.get(i), 2);
        }

        for (Character i : dstMap.keySet()) {
            dstDou += Math.pow(dstMap.get(i), 2);
        }
//       分母
        BigDecimal denominator = new BigDecimal(String.valueOf(Math.sqrt(srcDou) * Math.sqrt(dstDou)));
        return numerator.divide(denominator, 5, RoundingMode.HALF_UP);
    }
}
