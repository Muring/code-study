import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int laps = Integer.parseInt(br.readLine());

        int width = 2;
        int dots = 0;
        for(int idx = 0; idx < laps; idx++) {
            width += width - 1;
            dots = (int) Math.pow(width , 2);
        }
        System.out.println(dots);
    }
}
