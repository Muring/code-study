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

        int laps = Integer.parseInt(br.readLine());
        for (int lap = 0; lap < laps; lap++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == 1 || b == 1) {
                sb.append(Math.max(a, b)).append("\n");
                continue;
            }

            int temp = 0;
            int idx = 0;
            while (temp <= a * b) {
                idx++;
                temp = a * idx;
                if (temp % b == 0) {
                    sb.append(temp).append("\n");
                    break;
                }
            }
        }
        System.out.println(sb);
    }
}