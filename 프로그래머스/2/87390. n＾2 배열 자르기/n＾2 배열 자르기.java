import java.util.ArrayList;
import java.util.List;

public class Solution {

    /**
     * @param mapSize 배열의 길이
     * @param start   잘라낼 시작 인덱스
     * @param end     잘라낼 끝 인덱스
     * @return 잘라낸 배열
     */
    public static int[] solution(int mapSize, long start, long end) {
        // 초기화
        List<Integer> list = new ArrayList<>();

        for (long idx = start; idx <= end; idx++) {
            int row = (int) (idx / mapSize); //
            int col = (int) (idx % mapSize);
            int value = Math.max(row + 1, col + 1);
            list.add(value);
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}