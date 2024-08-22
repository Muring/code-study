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

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        int count = 0;
        while (end != start) {
            if (end < start) {
                count = -1;
                break;
            }

            int lastDigit = end % 10;
            if (lastDigit == 1) {
                end /= 10;
            } else if (end % 2 == 0) {
                end /= 2;
            } else {
                count = -1;
                break;
            }
            count++;
        }

        System.out.println(count == -1 ? count : count + 1);
    }
}