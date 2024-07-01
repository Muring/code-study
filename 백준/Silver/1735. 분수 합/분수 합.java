import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    private static int gcd(int a, int b) {

        BigInteger b1 = BigInteger.valueOf(a);
        BigInteger b2 = BigInteger.valueOf(b);
        BigInteger gcd = b1.gcd(b2);

        return gcd.intValue();
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int[] numerator = new int[2];
        int[] denominator = new int[2];

        // input
        for (int idx = 0; idx < 2; idx++) {
            st = new StringTokenizer(br.readLine());
            numerator[idx] = Integer.parseInt(st.nextToken());
            denominator[idx] = Integer.parseInt(st.nextToken());
        }

        // answer
        int numAnswer = 0;
        int deAnswer = 0;

        // make it as one fraction
        deAnswer = denominator[0] * denominator[1];
        numAnswer = numerator[0] * denominator[1] + numerator[1] * denominator[0];

        // get gcd from the fraction
        int gcd = gcd(deAnswer, numAnswer);

        // divide them with gcd
        numAnswer /= gcd;
        deAnswer /= gcd;

        // save answer
        sb.append(numAnswer).append(" ").append(deAnswer);

        System.out.println(sb);
    }
}