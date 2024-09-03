import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static int numCnt;  // 숫자 갯수
    static int arrLen;  // 수열 길이
    static int[] givenArr;  // 주어진 숫자 배열
    static Set<String> combSet;
    static boolean[] isUsed;

    private static void comb(int[] arr, int len, int depth) {
        if (len == arrLen) {
            String str = Arrays.toString(arr);

            if (!combSet.contains(str)) {
                combSet.add(str);

                for (int idx = 0; idx < arrLen; idx++)
                    sb.append(arr[idx]).append(" ");
                sb.append("\n");
            }
            return;
        }

        for (int idx = depth; idx < numCnt; idx++) {
            arr[len] = givenArr[idx];
            comb(arr, len + 1, idx);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        numCnt = Integer.parseInt(st.nextToken());  // 전체 숫자 갯수
        arrLen = Integer.parseInt(st.nextToken());  // 수열 길이 입력

        givenArr = new int[numCnt]; // 주어지는 배열 초기화
        isUsed = new boolean[numCnt];

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < numCnt; idx++) {
            givenArr[idx] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(givenArr);  // 오름차순 정렬을 위해 정렬

        combSet = new HashSet<>();
        comb(new int[arrLen], 0, 0);

        System.out.println(sb);
    }
}