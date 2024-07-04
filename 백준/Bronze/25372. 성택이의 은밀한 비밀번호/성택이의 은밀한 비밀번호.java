import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int laps = Integer.parseInt(br.readLine());
        for (int idx = 0; idx < laps; idx++) {
            String input = br.readLine();
            if (input.length() > 5 && input.length() < 10)
                sb.append("yes");
            else sb.append("no");
            sb.append("\n");
        }
        System.out.println(sb);
    }
}