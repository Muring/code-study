import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        String str = br.readLine();

        int idx = 0;
        while (idx < str.length()) {
            String temp = "";
            if (str.length() - idx >= 10) {
                temp = str.substring(idx, idx + 10);
                idx += 10;
            } else {
                temp = str.substring(idx, str.length());
                idx = str.length();
            }
            sb.append(temp).append("\n");
        }

        System.out.println(sb);
    }
}