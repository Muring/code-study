import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numCnt;  // 수의 종류 수
    static int seqLen;  // 수열 길이
    static int[] numArr;    // 수 저장 배열
    static Set<int[]> seqList;  //
    static boolean[] isVisited; // 해당 수가 사용됐는지 확인하는 배열

    private static void getSeq(int[] arr, int count) {
        if (count == seqLen) {
            for (int num : arr)
                sb.append(num).append(" ");
            sb.append("\n");
            return;
        }

        // before를 사용하여 다른 칸의 같은 값의 수가 사용되는 것을 막는다.
        int before = 0;
        for (int idx = 0; idx < numCnt; idx++) {
            if (isVisited[idx]) continue;

            if (before != numArr[idx]) {
                arr[count] = numArr[idx];
                before = numArr[idx];
                isVisited[idx] = true;
                getSeq(arr, count + 1);
                isVisited[idx] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 초기화
        st = new StringTokenizer(br.readLine());
        numCnt = Integer.parseInt(st.nextToken());
        seqLen = Integer.parseInt(st.nextToken());
        numArr = new int[numCnt];
        seqList = new LinkedHashSet<>();
        isVisited = new boolean[numCnt];

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < numCnt; idx++)
            numArr[idx] = Integer.parseInt(st.nextToken());

        // 사전 순으로 출력하기 위해 정렬
        Arrays.sort(numArr);

        // 수열 구하기
        getSeq(new int[seqLen], 0);

        //결과 출력
        System.out.println(sb);
    }
}