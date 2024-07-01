import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        BigInteger a = new BigInteger(br.readLine());
        BigInteger b = new BigInteger(br.readLine());

        sb.append(a.add(b)).append("\n")
                .append(a.subtract(b)).append("\n")
                .append(a.multiply(b));

        System.out.println(sb);
    }
}