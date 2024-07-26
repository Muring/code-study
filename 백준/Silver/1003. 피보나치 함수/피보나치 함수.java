import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static final int MAX_LEN = 41;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // f(0)과 f(1)의 계수에 대한 결과 저장 배열을 생성한다.
        // 범위가 0~40까지이기 때문에 41크기의 배열을 생성한다.
        int[][] coefficient = new int[MAX_LEN][2];    // 0의 개수, 1의 개수

        // 계수 피보나치 저장
        coefficient[0][0] = 1;
        coefficient[0][1] = 0;
        coefficient[1][0] = 0;
        coefficient[1][1] = 1;
        for (int idx = 2; idx < MAX_LEN; idx++) {
            coefficient[idx][0] = coefficient[idx - 1][0] + coefficient[idx - 2][0];
            coefficient[idx][1] = coefficient[idx - 1][1] + coefficient[idx - 2][1];
        }

        // 테스트 케이스 실행
        int testCnt = Integer.parseInt(br.readLine());
        for (int testIdx = 0; testIdx < testCnt; testIdx++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(coefficient[num][0]).append(" ").append(coefficient[num][1]).append("\n");
        }
        System.out.println(sb);
    }
}