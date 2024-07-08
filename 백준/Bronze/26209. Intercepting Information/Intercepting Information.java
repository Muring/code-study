import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        String answer = "S";
        while (st.hasMoreTokens()) {
            int data = Integer.parseInt(st.nextToken());
            if (data == 9) {
                answer = "F";
                break;
            }
        }
        System.out.println(answer);
    }
}