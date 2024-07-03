import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    private static long vowelCount(String str) {
        String vowels = "aiueo";

        return str.chars()
                .filter(c -> vowels.indexOf(c) != -1)
                .count();
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        String str = br.readLine();

        System.out.println(vowelCount(str));
    }
}