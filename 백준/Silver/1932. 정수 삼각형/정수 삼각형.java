import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int size;    // 삼각형 크기
    static int[][] triangle;   // 삼각형 배열

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 삼각형 사이즈 입력
        size = Integer.parseInt(br.readLine());

        // 초기화
        triangle = new int[size][size];
        triangle[0][0] = Integer.parseInt(br.readLine());

        /**
         * 숫자를 입력받음과 동시에 DP방식으로 합을 구해나간다.
         *
         * @depth 삼각형의 깊이
         * @col 삼각형의 열
         */
        for (int depth = 1; depth < size; depth++) {

            st = new StringTokenizer(br.readLine());
            for (int col = 0; col <= depth; col++) {
                int num = Integer.parseInt(st.nextToken()); // 해당 칸의 숫자 입력
                int a = 0, b = 0;   // 해당 칸의 윗 칸의 좌우 숫자 저장 변수

                // 윗칸의 왼쪽 칸이 존재할 때
                if (0 <= col - 1) a = triangle[depth - 1][col - 1] + num;
                // 윗칸의 오른쪽 칸이 존재할 때
                if (col <= depth - 1) b = triangle[depth - 1][col] + num;

                // 왼쪽 위와 오른쪽 위의 dp 값과 현재 값을 합했을 때 더 큰 수를 저장한다.
                triangle[depth][col] = Math.max(a, b);
            }
        }
        // 최대값 구하기
        int max = 0;
        for (int idx = 0; idx < size; idx++)
            max = Math.max(triangle[size - 1][idx], max);
        System.out.println(max);
    }
}