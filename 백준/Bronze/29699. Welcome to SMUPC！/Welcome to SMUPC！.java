import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        final String LABEL = "WelcomeToSMUPC";
        int input = Integer.parseInt(br.readLine());
        input -= 1;
        System.out.println(LABEL.charAt(input % 14));
    }
}