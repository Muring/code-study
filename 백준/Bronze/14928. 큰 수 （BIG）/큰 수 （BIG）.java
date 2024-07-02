import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 14928
 * 단순 BigInteger를 사용하면 시간초과가 난다.
 * String을 하나씩 쪼개어 나머지를 구하는 방식으로 하면 연산 횟수가 비약적으로 줄어든다.
 */
public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        long temp = 0;
        for (int idx = 0; idx < input.length(); idx++) {
            temp = (temp * 10 + (input.charAt(idx) - '0')) % 20000303;
        }

        System.out.println(temp);
    }
}