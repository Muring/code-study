import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int[][] arr = new int[2][3];
        for (int idx = 0; idx < 2; idx++) {
            st = new StringTokenizer(br.readLine());
            arr[idx][0] = Integer.parseInt(st.nextToken());
            arr[idx][1] = Integer.parseInt(st.nextToken());
            arr[idx][2] = Integer.parseInt(st.nextToken());
        }
        sb.append(arr[1][0] - arr[0][2]).append(" ")
                .append(arr[1][1] / arr[0][1]).append(" ")
                .append(arr[1][2] - arr[0][0]);
        System.out.println(sb);
    }
}