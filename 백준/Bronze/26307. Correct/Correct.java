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

        int hour = Integer.parseInt(st.nextToken()) - 9;
        int min = Integer.parseInt(st.nextToken());

        System.out.println(hour * 60 + min);
    }
}