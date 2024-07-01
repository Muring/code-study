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

        st = new StringTokenizer(br.readLine());
        int row1 = Integer.parseInt(st.nextToken());
        int col1 = Integer.parseInt(st.nextToken());
        int row2 = Integer.parseInt(st.nextToken());
        int col2 = Integer.parseInt(st.nextToken());
        System.out.println(row1 * col1 + row2 * col2);
    }
}
