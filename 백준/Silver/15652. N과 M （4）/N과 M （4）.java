import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int numCnt;
    static int arrLen;

    private static void comb(int[] arr, int start, int len) {
        if (len == arrLen) {
            for (int num : arr) {
                sb.append(num).append(" ");
            }
            sb.replace(sb.length() - 1, sb.length(), "\n");
            return;
        }

        for (int idx = start; idx < numCnt; idx++) {
            arr[len] = idx + 1;
            comb(arr, idx, len + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 입력
        st = new StringTokenizer(br.readLine());
        numCnt = Integer.parseInt(st.nextToken());
        arrLen = Integer.parseInt(st.nextToken());

        comb(new int[arrLen], 0, 0);

        System.out.println(sb);
    }
}