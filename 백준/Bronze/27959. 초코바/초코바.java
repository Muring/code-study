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
        int count = Integer.parseInt(st.nextToken());
        int price = Integer.parseInt(st.nextToken());

        if (count * 100 >= price) System.out.println("Yes");
        else System.out.println("No");
    }
}