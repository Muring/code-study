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

        BigInteger total = new BigInteger(br.readLine());
        BigInteger diff = new BigInteger(br.readLine());
        BigInteger two = new BigInteger("2");

        BigInteger a = (total.subtract(diff)).divide(two).add(diff); //(total-diff)/2+diff
        BigInteger b = (total.subtract(diff)).divide(two); //(total-diff)/2
        System.out.println(a);
        System.out.println(b);
    }
}
