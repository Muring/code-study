import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 초기 입력
        int peopleCnt = Integer.parseInt(br.readLine());
        int[] timeArr = new int[peopleCnt];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < peopleCnt; idx++)
            timeArr[idx] = Integer.parseInt(st.nextToken());

        // 오름차순으로 정렬 됐을 떄가 가장 빠르다.
        Arrays.sort(timeArr);

        // 정답 구하기
        int answer = 0;
        int[] temp = new int[peopleCnt];
        for (int idx = 0; idx < peopleCnt; idx++) {
            if (idx == 0) temp[idx] = timeArr[idx];
            else temp[idx] = temp[idx - 1] + timeArr[idx];

            answer += temp[idx];
        }
        System.out.println(answer);
    }
}