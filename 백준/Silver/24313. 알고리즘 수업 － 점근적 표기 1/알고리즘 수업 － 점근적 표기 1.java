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
        int f1 = Integer.parseInt(st.nextToken());
        int f2 = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        int Fn = n * f1 + f2;
        int Gn = c * n;

        if (Fn <= Gn && c >= f1)
            System.out.println(1);
        else System.out.println(0);
    }
}