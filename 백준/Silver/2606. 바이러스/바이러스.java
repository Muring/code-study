import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static int computerCnt; // 컴퓨터의 개수
    static int connectionCnt;   // 네트워크 연결 개수
    static int[][] map;    // 네트워크 맵
    static boolean[] isInfected; // 바이러스 여부

    // dfs로 컴퓨터 감염
    private static int deepInfection(int infectedComputer) {
        int count = 0;
        isInfected[infectedComputer] = true;

        for (int idx = 1; idx <= computerCnt; idx++) {
            if (map[infectedComputer][idx] == 1 && !isInfected[idx]) {
                count += deepInfection(idx) + 1;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 초기화
        computerCnt = Integer.parseInt(br.readLine());
        connectionCnt = Integer.parseInt(br.readLine());

        map = new int[computerCnt + 1][computerCnt + 1];
        isInfected = new boolean[computerCnt + 1];

        // 연결 시작
        for (int connectionIdx = 0; connectionIdx < connectionCnt; connectionIdx++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = map[b][a] = 1;  // 연결 상태 표시
        }

        // 감염 시작
        // 무조건 1번 컴퓨터부터 감염된다.
        System.out.println(deepInfection(1));
    }
}