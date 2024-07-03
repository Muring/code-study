import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int idx = 1;
        while (true) {
            String input = br.readLine();
            if (input.equals("0")) break;

            System.out.printf("Case %d: Sorting... done!\n", idx);
            idx++;
        }
    }
}