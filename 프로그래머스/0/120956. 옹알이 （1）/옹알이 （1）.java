public class Solution {
    static final String[] BASE_MABBLING = {"aya", "ye", "woo", "ma"};

    public static int solution(String[] babbling) {
        int answer = 0; // 정답 변수

        // babbling을 돌면서 기본적으로 발음할 수 있는 baseMabbling의 요소를 포함하는지 확인한다.
        for (String str : babbling) {

            // 요소 검증
            int baseIdx = 0;

            // String의 길이가 남아 있거나, BASE_MABBLING을 모두 훑어볼 때까지 반복
            while (!str.isEmpty() && baseIdx < BASE_MABBLING.length) {

                // String에 BASE_MABBLING의 요소가 있고 동시에 해당 String의 시작 지점이 0이라면 발음할 수 있다.
                if (str.contains(BASE_MABBLING[baseIdx]) && str.indexOf(BASE_MABBLING[baseIdx]) == 0) {
                    str = str.replace(BASE_MABBLING[baseIdx], "");  // 공백으로 해당 요소 치환
                    baseIdx = 0;    // 다시 BASE 처음부터 탐색
                } else baseIdx++;   // 요소가 발견되지 않으면 다음 요소 탐색
            }

            // 검사가 끝난 후 문자열의 길이가 0이면 정답이므로 count한다.
            if (str.isEmpty()) answer++;
        }
        return answer;  // 정답 반환
    }
}