import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static int treeCnt; // 나무 개수
    static int requiredLen; // 가져가야하는 나무 길이
    static int[] treeArr;  // 나무 배열

    /**
     * 입력 값의 범위가 매우 넓기 때문에 이분할로 계산을 빠르게 한다.
     */
    private static int chopTree(int start, int end) {
        while (start < end) {
            int mid = (end + start) / 2;
            long sum = 0;
            for (int tree : treeArr) sum += Math.max(tree - mid, 0);

            if (sum == requiredLen) return mid;
            /*
             * 합이 더 작을 때
             * 합이 더 작다는 것은 자른 높이가 너무 높다는 것이므로 낮춰줘야 한다.
             */
            if (sum < requiredLen) {
                end = mid;
            }

            /*
             * 합이 더 클 때
             * 합이 더 크다는 것은 높이가 너무 낮다는 것이므로 높여줘야 한다.
             */
            else {
                start = mid + 1;
            }
        }
        return start - 1;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 첫 줄 입력
        st = new StringTokenizer(br.readLine());
        treeCnt = Integer.parseInt(st.nextToken());
        requiredLen = Integer.parseInt(st.nextToken());

        // 나무 정보 입력
        treeArr = new int[treeCnt];
        int height = 0;
        st = new StringTokenizer(br.readLine());
        for (int treeIdx = 0; treeIdx < treeCnt; treeIdx++) {
            treeArr[treeIdx] = Integer.parseInt(st.nextToken());
            height = Math.max(treeArr[treeIdx], height);
        }

        // 나무 자르기
        System.out.println(chopTree(0, height));
    }
}