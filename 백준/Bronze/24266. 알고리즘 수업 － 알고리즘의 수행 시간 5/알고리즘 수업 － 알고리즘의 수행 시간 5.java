import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        long num = Long.parseLong(br.readLine());

        System.out.println(num * num * num);
        System.out.println(3);
    }
}