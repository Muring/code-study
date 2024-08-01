import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    private static long pow(int a, int b, int mod) {
        if (b == 0) {
            return 1;
        }

        long result = pow(a, b / 2, mod);
        if (b % 2 == 0)
            return result * result % mod;
        else return (result * result % mod) * a % mod;
    }


    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        System.out.println(pow(a, b, c));
    }
}