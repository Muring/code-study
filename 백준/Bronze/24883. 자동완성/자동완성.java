import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();
        if (input.equalsIgnoreCase("n")) {
            System.out.println("Naver D2");
        } else System.out.println("Naver Whale");
    }
}