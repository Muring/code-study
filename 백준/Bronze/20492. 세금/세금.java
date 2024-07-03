import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    private static long getTax(long money) {
        return money * 22 / 100;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        long money = Long.parseLong(br.readLine());
        sb.append(money - getTax(money)).append(" ");
        sb.append(money - getTax(money / 5));
        System.out.println(sb);
    }
}