import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static int mapSize;     // 맵 길이
    static int[][] map;     // 맵 배열
    static int whiteCnt;    // 하얀색 색종이
    static int blueCnt;     // 파란색 색종이

    /**
     * 색종이를 나누는 메서드
     *
     * @param startRow 시작지점의 행 인덱스
     * @param startCol 시작지점의 열 위치
     * @param len      사각형 한 변 길이
     */
    private static void divide(int startRow, int startCol, int len) {
        // 가장 처음 칸 값을 기준으로 다른지 확인
        int num = map[startRow][startCol];

        // 만약 길이가 1이면 이미 정사각형이므로 탈출
        if (len == 1) {
            if (num == 0) whiteCnt++;
            else blueCnt++;
            return;
        }

        // 탐색 시작
        for (int row = startRow; row < startRow + len; row++) {
            for (int col = startCol; col < startCol + len; col++) {
                // 해당 범위에 다른 색상이 존재한다면
                if (num != map[row][col]) {
                    // 1사분면
                    divide(startRow, startCol + len / 2, len / 2);
                    // 2사분면
                    divide(startRow, startCol, len / 2);
                    // 3사분면
                    divide(startRow + len / 2, startCol, len / 2);
                    // 4사분면
                    divide(startRow + len / 2, startCol + len / 2, len / 2);
                    return;
                }
            }
        }
        // 해당 칸 전체가 같은 색이라면 해당 색상 개수 증가
        if (num == 0) whiteCnt++;
        else blueCnt++;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 초기화
        whiteCnt = 0;
        blueCnt = 0;

        // 맵 정보 입력
        mapSize = Integer.parseInt(br.readLine());
        map = new int[mapSize][mapSize];
        for (int row = 0; row < mapSize; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < mapSize; col++) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        // 색종이 나누기
        divide(0, 0, mapSize);

        System.out.println(whiteCnt);
        System.out.println(blueCnt);
    }
}