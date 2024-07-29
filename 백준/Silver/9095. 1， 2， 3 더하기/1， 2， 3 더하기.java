import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // dp 구하기
        int[] dp = new int[12];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int idx = 4; idx <= 11; idx++) {
            dp[idx] = dp[idx - 1] + dp[idx - 2] + dp[idx - 3];
        }

        // 방법의 수 구하기
        int laps = Integer.parseInt(br.readLine());
        for (int lap = 0; lap < laps; lap++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(dp[num]).append("\n");
        }
        System.out.println(sb);
    }
}