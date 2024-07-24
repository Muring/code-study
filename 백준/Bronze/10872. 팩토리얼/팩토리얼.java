import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    private static int getFactorial(int num) {
        int result = 1;
        for (int idx = 2; idx <= num; idx++)
            result *= idx;

        return result;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        System.out.println(getFactorial(num));
    }
}