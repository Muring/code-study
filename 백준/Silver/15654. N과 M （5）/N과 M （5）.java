import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static int numCnt;  // 숫자 종류 개수
    static int permLen; // 수열의 길이
    static int[] nums;  // 입력된 숫자 저장 배열
    static boolean[] isVisited; // 숫자가 쓰였는지 확인 배열

    /**
     * 수열 생성 메서드
     *
     * @param arr   수열 배열
     * @param start 숫자의 시작 인덱스
     * @param count 생성된 수열의 길이
     */
    private static void perm(int[] arr, int start, int count) {
        // 기저 조건
        if (count == permLen) {
            for (int num : arr) {
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        // 수열 저장
        for (int idx = 0; idx < numCnt; idx++) {
            if (!isVisited[idx]) {
                arr[count] = nums[idx];
                isVisited[idx] = true;
                perm(arr, idx + 1, count + 1);
                isVisited[idx] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 숫자의 갯수 및 수열의 길이를 입력
        st = new StringTokenizer(br.readLine());
        numCnt = Integer.parseInt(st.nextToken());
        permLen = Integer.parseInt(st.nextToken());

        // 초기화
        nums = new int[numCnt];
        isVisited = new boolean[numCnt];
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < numCnt; idx++) {
            nums[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nums);  // 사전 순 정렬을 위해 오름차순 정렬

        int[] arr = new int[permLen];
        perm(arr, 0, 0);

        System.out.println(sb);
    }
}