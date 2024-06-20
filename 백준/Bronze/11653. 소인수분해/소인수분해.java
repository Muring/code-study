import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int num = Integer.parseInt(br.readLine());

        for(int idx = 2; idx <= Math.sqrt(num); idx++) {
            while(num % idx == 0) {
                sb.append(idx).append("\n");
                num /= idx;
            }
        }

        if(num != 1) {
            sb.append(num);
        }
        System.out.println(sb);
    }
}
