import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringTokenizer st;

    static int rowSize;
    static int colSize;
    static char[][] map;
    static final char[] STATUS = {'W', 'B'};

    private static int paintCnt(char[][] map, int row, int col, int status) {
        int count = 0;

        // 원본 맵에 영향을 미치지 않기 위해서 복사본 생성
        char[][] copyMap = new char[rowSize][colSize];
        for (int idx = 0; idx < rowSize; idx++)
            copyMap[idx] = map[idx].clone();

        for (int rowIdx = row; rowIdx < row + 8; rowIdx++) {
            for (int colIdx = col; colIdx < col + 8; colIdx++) {
                if (copyMap[rowIdx][colIdx] != STATUS[status]) {
                    copyMap[rowIdx][colIdx] = STATUS[status];
                    count++;
                }
                status = 1 - status;
            }
            status = 1 - status;
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 맵 사이즈 입력
        st = new StringTokenizer(br.readLine());
        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());

        // 맵 생성
        map = new char[rowSize][colSize];

        // 맵 정보 저장
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            String str = br.readLine();
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                map[rowIdx][colIdx] = str.charAt(colIdx);
            }
        }

        // 최소로 칠하는 개수 세기
        int minCount = Integer.MAX_VALUE;
        for (int rowIdx = 0; rowIdx <= rowSize - 8; rowIdx++) {
            for (int colIdx = 0; colIdx <= colSize - 8; colIdx++) {
                for (int status = 0; status <= 1; status++)
                    minCount = Math.min(minCount, paintCnt(map, rowIdx, colIdx, status));
            }
        }
        System.out.println(minCount);
    }
}
