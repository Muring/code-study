import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = {56, 24, 14, 6};
        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < 4; idx++) {
            int num = Integer.parseInt(st.nextToken());
            sum += num * arr[idx];
        }
        System.out.println(sum);
    }
}