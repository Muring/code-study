import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        long result = (long) a * b * c;

        String str = String.valueOf(result);
        int[] count = new int[10];
        for (int idx = 0; idx < str.length(); idx++) {
            int num = str.charAt(idx) - '0';
            count[num]++;
        }

        for (int num : count)
            sb.append(num).append("\n");

        System.out.println(sb);
    }
}