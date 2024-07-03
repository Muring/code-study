import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    private static long getTax(long money) {
        return money * 22 / 100;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < 6; idx++) {
            int num = Integer.parseInt(st.nextToken());
            sum += num * 5;
        }
        System.out.println(sum);
    }
}