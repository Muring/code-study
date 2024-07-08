import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int count = 0;
        while (true) {
            String input = br.readLine();
            if (input == null) break;
            count++;
        }
        System.out.println(count);
    }
}