import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * BOJ 3009 네 번째 점
 * 각 열에서 숫자를 셌을 때, 개수가 홀 수인 수를 반환하면 된다.
 */
public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        long num = Long.parseLong(br.readLine());

        System.out.println(num * 4);
    }
}
