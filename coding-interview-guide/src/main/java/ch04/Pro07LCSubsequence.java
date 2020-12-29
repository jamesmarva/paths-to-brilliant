package ch04;

/**
 * 最长公共子序列
 * https://leetcode-cn.com/problems/longest-common-subsequence/
 * 1143. 最长公共子序列
 * dp[i][j]
 * s1[i]  和 s2[j] 的最长的公共子序列
 * @author 王涵威
 * @date 2020/9/22 12:35
 */
public class Pro07LCSubsequence {

    public int solution0(String s1, String s2) {
        if (null == s1 || null == s2 || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        int row = s1.length();
        int col = s2.length();
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        int[][] dp = new int[row][col];
        if (c1[0] == c2[0]) {
            dp[0][0] = 1;
        }
        for (int i = 1; i < row; i++) {
            dp[i][0] = dp[i - 1][0];
            if (c1[i] == c2[0]) {
                dp[i][0] = 1;
            }
        }

        for (int i = 1; i < col; i++) {
            dp[0][i] = dp[0][i - 1];
            if (c1[0] == c2[i]) {
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < row; i++){
            for (int j = 1; j < col; j++) {
                int tmp = Math.max(dp[i -1][j], dp[i][j - 1]);
                if (c1[i] == c2[j]) {
                    tmp = Math.max(tmp, dp[i -1][j - 1] + 1);
                }
                dp[i][j] = tmp;
            }
        }
        return dp[row - 1][col - 1];
    }

    public int solution1(String s1, String s2) {
        if (null == s1 || null == s2 || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        int row = s1.length(), col = s2.length();
        char[] c1 = s1.toCharArray(), c2 = s2.toCharArray();
        int[][] dp = new int[row + 1][col + 1];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                int tmp = Math.max(dp[i][j + 1], dp[i + 1][j]);
                if (c1[i] == c2[j]) {
                    tmp = Math.max(tmp, dp[i][j] + 1);
                }
                dp[i + 1][j + 1] = tmp;
            }
        }
        return dp[row][col];
    }

    public int[][] getDp(String s1, String s2) {
        return null;
    }



}
