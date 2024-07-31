import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int arrLen;  // 수열 길이
    static int[] arr;   // 수열
    static int[] dp;    // dp 배열
    static int max;     // 최대 길이

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 수열 길이 입력 및 배열 생성
        // 이때, 중복된 값은 필요 없으므로 순서만 유지하며 중복된 값은 제거한다.
        arrLen = Integer.parseInt(br.readLine());
        arr = new int[arrLen];

        // 수열 값 입력
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < arrLen; idx++) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        // 정렬하지 않은 상태의 주어진 수열의 가장 긴 부분 수열을 구해야 한다.
        dp = new int[arrLen]; // dp 배열 생성
        dp[0] = 1;  // 가장 첫번째 dp 초기화
        for (int idx = 1; idx < arrLen; idx++) {
            dp[idx] = 1;
            for (int jdx = 0; jdx < idx; jdx++) {
                if (arr[idx] > arr[jdx] && dp[idx] <= dp[jdx])
                    dp[idx] = dp[jdx] + 1;
            }
        }

        max = 0;
        for (int num : dp)
            max = Math.max(num, max);
        System.out.println(max);
    }
}