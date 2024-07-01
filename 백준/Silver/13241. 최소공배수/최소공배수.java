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

        st = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());

        if (a == 1) {
            sb.append(Math.max(a, b)).append("\n");
        } else {
            long temp = 0;
            long idx = 0;
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