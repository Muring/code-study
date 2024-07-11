import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int start = Integer.parseInt(br.readLine());
        int end = Integer.parseInt(br.readLine());
        int sleepTime = 0;
        while (start != end) {
            sleepTime++;
            if (++start == 24) start = 0;
        }
        System.out.println(sleepTime);
    }
}