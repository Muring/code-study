import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        BigInteger answer = new BigInteger("1");
        for (int idx = 1; idx <= num; idx++) {
            BigInteger temp = new BigInteger(Integer.toString(idx));
            answer = answer.multiply(temp);
        }
        System.out.println(answer);
    }
}