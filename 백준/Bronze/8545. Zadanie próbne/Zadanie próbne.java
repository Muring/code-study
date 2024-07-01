import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        for (int idx = str.length() - 1; idx >= 0; idx--) {
            System.out.print(str.charAt(idx));
        }
    }
}
