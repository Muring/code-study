import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // N, M 입력
        st = new StringTokenizer(br.readLine());
        int studentCnt = Integer.parseInt(st.nextToken());
        int limit = Integer.parseInt(st.nextToken());

        // 학생 점수 입력
        Integer[] score = new Integer[studentCnt];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < studentCnt; idx++) {
            score[idx] = Integer.parseInt(st.nextToken());
        }

        // 내림차순 정렬
        Arrays.sort(score, Collections.reverseOrder());

        // 결과
        System.out.println(score[limit - 1]);

    }
}
