import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    private static boolean isPrime(int num) {
        if (num == 1)
            return false;

        for (long idx = 2; idx <= Math.sqrt(num); idx++) {
            if (num % idx == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        for (int idx = start; idx <= end; idx++) {
            if (isPrime(idx))
                sb.append(idx).append("\n");
        }

        System.out.println(sb);
    }
}