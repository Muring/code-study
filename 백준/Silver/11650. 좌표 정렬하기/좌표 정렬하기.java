import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    private static void Solution(int laps) throws IOException {
        // 배열 생성
        int[][] arr = new int[laps][2];

        // 배열에 입력
        for (int idx = 0; idx < laps; idx++) {
            st = new StringTokenizer(br.readLine());
            arr[idx][0] = Integer.parseInt(st.nextToken());
            arr[idx][1] = Integer.parseInt(st.nextToken());
        }

        // 오름차순 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return o1[0] - o2[0];
                else return o1[1] - o2[1];
            }
        });

        // 결과 출력
        for (int[] pair : arr) {
            sb.append(pair[0]).append(" ").append(pair[1]).append("\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        int laps = Integer.parseInt(br.readLine());

        Solution(laps);
    }
}
