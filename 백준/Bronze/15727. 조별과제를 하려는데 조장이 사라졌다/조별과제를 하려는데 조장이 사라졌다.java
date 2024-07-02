import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int distance = Integer.parseInt(br.readLine());
        int answer = distance % 5 == 0 ? distance / 5 : distance / 5 + 1;
        System.out.println(answer);
    }
}