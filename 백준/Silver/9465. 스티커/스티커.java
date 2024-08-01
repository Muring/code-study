import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int[][] arr;

    private static void getMaxScore(int len) throws IOException {
        // 배열 저장
        for (int idx = 0; idx < 2; idx++) {
            st = new StringTokenizer(br.readLine());
            for (int jdx = 0; jdx < len; jdx++)
                arr[idx][jdx] = Integer.parseInt(st.nextToken());
        }

        // dp 생성
        for (int idx = 1; idx < len; idx++) {
            if (idx == 1) {
                arr[0][idx] += arr[1][idx - 1];
                arr[1][idx] += arr[0][idx - 1];
            } else {
                arr[0][idx] += Math.max(arr[1][idx - 1], arr[1][idx - 2]);
                arr[1][idx] += Math.max(arr[0][idx - 1], arr[0][idx - 2]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int laps = Integer.parseInt(br.readLine());
        for (int lap = 0; lap < laps; lap++) {
            int len = Integer.parseInt(br.readLine());
            arr = new int[2][len];

            getMaxScore(len);

            sb.append(Math.max(arr[0][len - 1], arr[1][len - 1])).append("\n");
        }
        System.out.println(sb);
    }
}