import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br;
    static StringBuilder sb;
    static StringTokenizer st;

    static int[][] maxDPMap;  // 최대합 맵
    static int[][] minDPMap;  // 최소합 맵
    static int rowSize; // 행의 길이
    static final int COL_SIZE = 3;  // 열의 길이는 3으로 고정
    static final int[] D_COL = {-1, 0, 1};

    // 이전 행의 최대값 구하는 메서드
    private static int getPrevMaxValue(int startRow, int startCol) {
        int maxNum = 0;
        int prevRow = startRow - 1;
        for (int delta : D_COL) {
            if (isAvailable(startCol + delta)) {
                maxNum = Math.max(maxDPMap[prevRow][startCol + delta], maxNum);
            }
        }
        return maxNum;
    }

    // 이전 행의 최소값 구하는 메서드
    private static int getPrevMinValue(int startRow, int startCol) {
        int minNum = Integer.MAX_VALUE;
        int prevRow = startRow - 1;
        for (int delta : D_COL) {
            if (isAvailable(startCol + delta)) {
                minNum = Math.min(minDPMap[prevRow][startCol + delta], minNum);
            }
        }
        return minNum;
    }

    private static boolean isAvailable(int nextCol) {
        return nextCol >= 0 && nextCol < COL_SIZE;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();

        // 행 사이즈 입력 및 맵 초기화
        rowSize = Integer.parseInt(br.readLine());
        maxDPMap = new int[rowSize][COL_SIZE];
        minDPMap = new int[rowSize][COL_SIZE];

        // 맵 정보 입력
        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            st = new StringTokenizer(br.readLine());
            for (int colIdx = 0; colIdx < COL_SIZE; colIdx++) {
                int num = Integer.parseInt(st.nextToken());
                maxDPMap[rowIdx][colIdx] = minDPMap[rowIdx][colIdx] = num;
            }
        }

        // 시작 지점이 총 열의 개수만큼 있기 떄문에 반복해준다.
        // 이때, 제한 시간이 1초이기 때문에 DP 방식으로 문제를 해결한다.
        // 또한, 한칸 위의 행의 값과 현재 행의 값을 더해서 최소, 최대를 구해야하기 때문에 시작 행은 1이다.
        for (int rowIdx = 1; rowIdx < rowSize; rowIdx++) {
            for (int colIdx = 0; colIdx < COL_SIZE; colIdx++) {
                maxDPMap[rowIdx][colIdx] += getPrevMaxValue(rowIdx, colIdx);
                minDPMap[rowIdx][colIdx] += getPrevMinValue(rowIdx, colIdx);
            }
        }

        // 결과 저장 및 출력
        int maxSum = Arrays.stream(maxDPMap[rowSize - 1]).max().isPresent() ? Arrays.stream(maxDPMap[rowSize - 1]).max().getAsInt() : 0;
        int minSum = Arrays.stream(minDPMap[rowSize - 1]).min().isPresent() ? Arrays.stream(minDPMap[rowSize - 1]).min().getAsInt() : 0;
        sb.append(maxSum).append(" ").append(minSum);
        System.out.println(sb);
    }
}