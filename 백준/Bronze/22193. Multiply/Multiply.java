import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        br.readLine();

        long a = Integer.parseInt(br.readLine());
        long b = Integer.parseInt(br.readLine());
        System.out.println(a * b);
    }
}