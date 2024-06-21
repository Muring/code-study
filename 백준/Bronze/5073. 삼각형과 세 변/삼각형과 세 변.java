import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    private static String countEqualCnt(int a, int b, int c) {

        if (a == b && b == c) return "Equilateral";
        else if (a == b || b == c || c == a) return "Isosceles";
        else return "Scalene";
    }

    private static boolean isTriangle(int a, int b, int c) {
        // 삼각형이 성립하는 조건을 확인
        return a + b > c && a + c > b && b + c > a;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 0 && b == 0 && c == 0) break;
            else if (!isTriangle(a, b, c))
                System.out.println("Invalid");
            else
                System.out.println(countEqualCnt(a, b, c));
        }
    }
}
