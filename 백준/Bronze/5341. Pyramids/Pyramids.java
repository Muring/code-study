import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        while (true) {
            int num = Integer.parseInt(br.readLine());
            if (num == 0) break;
            int sum = 0;
            for (int idx = 1; idx <= num; idx++) {
                sum += idx;
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
