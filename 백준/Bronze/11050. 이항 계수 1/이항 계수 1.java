import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    private static int getFactorial(int num) {
        int result = 1;
        for (int idx = 2; idx <= num; idx++)
            result *= idx;
        return result;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        System.out.println(getFactorial(a) / (getFactorial(b) * getFactorial(a - b)));
    }
}