import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    private static void Solution(int laps) throws IOException {
        int[] origin = new int[laps];
        int[] sorted = new int[laps];
        HashMap<Integer, Integer> rankingMap = new HashMap<>();

        // 입력
        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < laps; idx++) {
            origin[idx] = sorted[idx] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(sorted);

        // 좌표 압축
        int rank = 0;
        // 정렬된 결과에 랭킹을 매긴다.
        for (int num : sorted) {
            // 중복된 숫자에 랭킹을 메기면 안되므로 중복이 아닐 때만 순위를 매긴다.
            if (!rankingMap.containsKey(num)) {
                rankingMap.put(num, rank);
                rank++;
            }
        }

        for (int num : origin) {
            sb.append(rankingMap.get(num)).append(" ");
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
