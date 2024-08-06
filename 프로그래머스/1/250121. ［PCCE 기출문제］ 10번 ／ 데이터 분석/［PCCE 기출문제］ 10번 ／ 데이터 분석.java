import java.util.*;

public class Solution {
    static Map<String, Integer> dataMap;

    /**
     * data에서 ext 값이 val_ext보다 작은 데이터만 뽑은 후,
     * sort_by에 해당하는 값을 기준으로 오름차순으로 정렬하여 return 하도록 solution 함수를 완성
     *
     * @param data    code || date || maximum || remain 으로 구성된 데이터 배열
     * @param ext     데이터 뽑는 기준 문자열
     * @param val_ext 뽑아낼 기준값
     * @param sort_by 정렬할 기준 문자열
     * @return 정렬되어 정리된 data
     */
    public static int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        dataMap = new HashMap<>() {{
            put("code", 0);
            put("date", 1);
            put("maximum", 2);
            put("remain", 3);
        }};

        int dataIdx = dataMap.get(ext);
        List<int[]> dataList = new ArrayList<>();
        for (int[] arr : data) {
            if (arr[dataIdx] < val_ext) {
                dataList.add(arr);
            }
        }

        int sortIdx = dataMap.get(sort_by);
        dataList.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[sortIdx] - o2[sortIdx];
            }
        });
        return listToArray(dataList);
    }

    public static int[][] listToArray(List<int[]> list) {
        // 리스트의 크기로 2차원 배열 초기화
        int[][] array = new int[list.size()][];

        // 리스트의 각 요소를 배열에 복사
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }
}