import java.util.HashSet;
import java.util.Set;

public class Solution {

    static Set<Integer> set;
    static boolean[] isSelected;

    /**
     * 가장 중요한 부분은 주어진 수열이 원형 수열이란 것과 연속 부분 수열의 합을 구하는 것이라는 것이다.
     * <p>
     * 즉, 주어진 수열에서 순서가 바뀌지 않도록 하면서 길이가 1~전체 길이인 모든 부분집합의 합을 구하면 된다.
     * <p>
     * 배열을 계속 연속시켜 순환시키기 위해선 두 가지 방법이 있다.
     * <p>
     * 1. 주어진 수열을 두 배의 길이로 늘려 반복시키는 방법
     * <p>
     * 2. 주어진 수열의 길이만큼 나눈 나머지로 반복시키는 방법
     * <p>
     * 필자는 2번 방법을 사용하겠다.
     * <p>
     * 구해진 부분집합은 중복되면 안되니 Set을 사용해 중복을 제거해주도록 하자.
     *
     * @param elements 원형 수열
     * @return 원형 수열의 연속 부분 수열 합으로 만들 수 있는 수의 개수
     */
    public static int solution(int[] elements) {
        // 초기화
        int elementSize = elements.length;
        set = new HashSet<>();

        // 부분 집합의 길이에 따라 합을 구한다.
        for (int setLen = 1; setLen <= elementSize; setLen++) {
            // 수열 탐색
            for (int startIdx = 0; startIdx < elementSize; startIdx++) {
                int sum = 0;
                // 부분집합의 합 구하기
                // 시작지점에서 시작지점 + 부분집합 길이를 통해 해당 부분집합 길이만큼 합을 구한다.
                for (int currentIdx = startIdx; currentIdx < startIdx + setLen; currentIdx++) {
                    sum += elements[currentIdx % elementSize];  // 나머지 연산을 통해 원형 수열화
                }
                // 부분집합의 합이 구해졌으면 set에 저장한다.
                set.add(sum);
            }
        }

//        System.out.println(Arrays.toString(set.toArray()));
        return set.size();
    }
}