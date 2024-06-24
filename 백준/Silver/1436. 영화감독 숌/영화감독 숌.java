import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int inputNum = Integer.parseInt(br.readLine());
        int count = 0;
        int num = 665;
        String sNum = "";

        while (count != inputNum) {
            num++;
            sNum = Integer.toString(num);
            if (sNum.contains("666")) {
                count++;
            }
        }
        System.out.println(num);
    }
}
