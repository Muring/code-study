import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;

    private static int countEqualCnt(int a, int b, int c) {
        int count = 0;

        if (a == b) count++;
        if (b == c) count++;
        if (c == a) count++;

        return count;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        String answer = "";

        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        int sum = a + b + c;
        int equalCnt = countEqualCnt(a, b, c);

        if (sum == 180) {
            if (equalCnt == 3) {
                answer = "Equilateral";
            } else if (equalCnt >= 1) {
                answer = "Isosceles";
            } else {
                answer = "Scalene";
            }
        } else answer = "Error";

        System.out.println(answer);
    }
}
