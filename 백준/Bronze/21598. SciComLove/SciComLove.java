import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        int laps = Integer.parseInt(br.readLine());

        for (int idx = 0; idx < laps; idx++) {
            System.out.println("SciComLove");

        }
    }
}