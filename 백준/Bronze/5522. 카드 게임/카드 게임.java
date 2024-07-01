import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        for (int idx = 0; idx < 5; idx++) {
            int num = Integer.parseInt(br.readLine());
            sum += num;
        }
        System.out.println(sum);
    }
}
