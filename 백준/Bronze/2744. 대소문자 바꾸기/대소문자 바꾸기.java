import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String str = br.readLine();

        for (char ch : str.toCharArray()) {
            if (Character.isUpperCase(ch))
                sb.append(Character.toLowerCase(ch));
            else sb.append(Character.toUpperCase(ch));
        }

        System.out.println(sb);
    }
}