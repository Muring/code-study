import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        BigInteger money = new BigInteger(st.nextToken());
        BigInteger life = new BigInteger(st.nextToken());

        sb.append(money.divide(life)).append(" ").append(money.remainder(life));

        System.out.println(sb);
    }
}