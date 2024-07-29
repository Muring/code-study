import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb;

    static int[][] map; // 양배추 밭
    static int rowSize, colSize;    // 양배추 밭 가로, 세로 길이
    static int count;   // 구역의 개수
    // 상 하 좌 우
    static final int[] D_ROW = {-1, 1, 0, 0};
    static final int[] D_COL = {0, 0, -1, 1};

    // 완전 탐색
    private static void bfs(int row, int col) {
        map[row][col] += count;
//        printMap();
        for (int deltaIdx = 0; deltaIdx < D_ROW.length; deltaIdx++) {
            int nextRow = row + D_ROW[deltaIdx];
            int nextCol = col + D_COL[deltaIdx];
            if (isValid(nextRow, nextCol)) bfs(nextRow, nextCol);
        }
    }

    // 유효성 검사
    private static boolean isValid(int row, int col) {
        return 0 <= row && 0 <= col && row < rowSize && col < colSize && map[row][col] == 1;
    }

    // 맵 확인
    private static void printMap() {
        for (int row = 0; row < rowSize; row++) {
            System.out.println(Arrays.toString(map[row]));
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        int laps = Integer.parseInt(br.readLine());
        for (int lap = 0; lap < laps; lap++) {
            // 양배추 밭 크기 입력 및 양배추 개수 입력
            st = new StringTokenizer(br.readLine());
            colSize = Integer.parseInt(st.nextToken());
            rowSize = Integer.parseInt(st.nextToken());
            int cabbageCnt = Integer.parseInt(st.nextToken());

            // 초기화
            map = new int[rowSize][colSize];
            count = 0;

            // 양배추 심기
            for (int cabbage = 0; cabbage < cabbageCnt; cabbage++) {
                st = new StringTokenizer(br.readLine());
                int colIdx = Integer.parseInt(st.nextToken());
                int rowIdx = Integer.parseInt(st.nextToken());
                map[rowIdx][colIdx]++;
            }

            // 구역 확인
            for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
                for (int colIdx = 0; colIdx < colSize; colIdx++) {
                    if (map[rowIdx][colIdx] == 1) {
                        count++;    // 구역 번호 매기기
                        bfs(rowIdx, colIdx);
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }
}