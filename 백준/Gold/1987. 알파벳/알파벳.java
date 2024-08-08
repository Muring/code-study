import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int rowSize;
    static int colSize;
    static int[][] map;
    static boolean[] isUsed;
    static int maxLen;

    static final int[] D_ROW = {-1, 1, 0, 0};
    static final int[] D_COL = {0, 0, -1, 1};

    private static void runMap(int startRow, int startCol, int count) {
        if (isUsed[map[startRow][startCol]]) {
            maxLen = Math.max(maxLen, count);
            return;
        }

        isUsed[map[startRow][startCol]] = true;
        for (int idx = 0; idx < D_ROW.length; idx++) {
            int nextRow = startRow + D_ROW[idx];
            int nextCol = startCol + D_COL[idx];

            if (isvalid(nextRow, nextCol)) {
                runMap(nextRow, nextCol, count + 1);
            }
        }
        isUsed[map[startRow][startCol]] = false;
    }

    private static boolean isvalid(int row, int col) {
        return 0 <= row && row < rowSize && 0 <= col && col < colSize;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        isUsed = new boolean[26];

        // 지도 사이즈 입력
        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        if (rowSize == 1 && colSize == 1) maxLen = 1;
        else {
            // 맵 입력
            map = new int[rowSize][colSize];
            for (int row = 0; row < rowSize; row++) {
                String input = br.readLine();
                for (int col = 0; col < colSize; col++) {
                    map[row][col] = input.charAt(col) - 'A';
                }
            }
            maxLen = 0;
            runMap(0, 0, 0);
        }
        System.out.println(maxLen);
    }
}