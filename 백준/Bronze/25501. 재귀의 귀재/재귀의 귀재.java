import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static int recursionCount;

    public static int recursion(String s, int l, int r) {
        recursionCount++;
        if (l >= r) return 1;
        else if (s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l + 1, r - 1);
    }

    public static int isPalindrome(String s) {
        return recursion(s, 0, s.length() - 1);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int laps = Integer.parseInt(br.readLine());
        for (int idx = 0; idx < laps; idx++) {
            recursionCount = 0;
            String str = br.readLine();
            sb.append(isPalindrome(str)).append(" ").append(recursionCount).append("\n");
        }
        System.out.println(sb);
    }
}