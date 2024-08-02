import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static BufferedReader br;
    static StringBuilder sb;

    private static void print(int[][] dp) {
        for (int[] arr : dp)
            System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String str1 = br.readLine();
        String str2 = br.readLine();
        int strLen1 = str1.length();
        int strLen2 = str2.length();

        // DP
        int[][] dp = new int[strLen2 + 1][strLen1 + 1];
        for (int col = 1; col <= strLen1; col++) {
            for (int row = 1; row <= strLen2; row++) {
                if (str1.charAt(col - 1) == str2.charAt(row - 1)) {
                    dp[row][col] = dp[row - 1][col - 1] + 1;
                } else {
                    dp[row][col] = Math.max(dp[row - 1][col], dp[row][col - 1]);
                }
            }
//            print(dp);
        }
        System.out.println(dp[strLen2][strLen1]);
    }
}