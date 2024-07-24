import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    private static int fibonacci(int num, int a, int b) {
        if (num == 0) return a;
        return fibonacci(num - 1, b, a + b);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        if (num == 0) System.out.println(num);
        else if (num == 1) System.out.println(num);
        else System.out.println(fibonacci(num, 0, 1));
    }
}