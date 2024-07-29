import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int len = Integer.parseInt(br.readLine());

        int[] dp = new int[1001];
        dp[1] = 1;
        dp[2] = 2;
        for (int idx = 3; idx <= len; idx++)
            dp[idx] = (dp[idx - 1] + dp[idx - 2]) % 10007;

        System.out.println(dp[len]);
    }
}