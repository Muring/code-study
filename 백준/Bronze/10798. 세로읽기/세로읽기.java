import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static final int ROW_SIZE = 5;
    static final int COL_SIZE = 15;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        map = new char[ROW_SIZE][COL_SIZE];
        String line = " ";  // 한 줄 임시 저장 스트링

        // 맵 저장
        for(int rowIdx = 0; rowIdx < ROW_SIZE; rowIdx++) {
            line = br.readLine().trim();
            int stringLen = line.length();

            for(int colIdx = 0; colIdx < COL_SIZE; colIdx++) {
               if(colIdx < stringLen)
                   map[rowIdx][colIdx] = line.charAt(colIdx);
               else
                   map[rowIdx][colIdx] = ' ';
            }
        }

        // 순서대로 읽기
        for(int colIdx = 0; colIdx < COL_SIZE; colIdx++) {
            for(int rowIdx = 0; rowIdx < ROW_SIZE; rowIdx++) {
                if(map[rowIdx][colIdx] != ' ')
                    sb.append(map[rowIdx][colIdx]);
            }
        }
        System.out.println(sb);
    }
}
