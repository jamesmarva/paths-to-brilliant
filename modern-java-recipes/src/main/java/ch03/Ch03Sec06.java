package ch03;

/**
 * 字符串与流之间的转换
 *
 *
 * String 实现了 CharSequence 的接口，它引入了两种能够生成IntStream 的方法 (char, codePoints)
 * 它们都是接口中的默认的方法，因此存在实现的可能。
 * @author 王涵威
 * @date 2020/6/29 21:26
 */
public class Ch03Sec06 {

    public static void main(String[] args) {

    }

    public static boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                sb.append(c);
            }
        }
        String forward = sb.toString().toLowerCase();
        String backward = sb.reverse().toString().toLowerCase();
        return forward.equals(backward);
    }

    public static boolean isPalindromeDemo1(String s) {
        String forward = s.toLowerCase().codePoints()
                .filter(Character::isLetterOrDigit)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append)
                .toString();
        String backward = new StringBuilder(forward).reverse().toString();
        return forward.equals(backward);
    }


}
