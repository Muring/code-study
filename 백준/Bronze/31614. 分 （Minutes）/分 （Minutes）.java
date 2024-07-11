import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int hour = Integer.parseInt(br.readLine());
        int min = Integer.parseInt(br.readLine());
        System.out.println(hour * 60 + min);
    }
}