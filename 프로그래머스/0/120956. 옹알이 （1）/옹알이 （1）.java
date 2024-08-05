class Solution {
      static final String[] BASE_MABBLING = {"aya", "ye", "woo", "ma"};
    
    public int solution(String[] babbling) {
        // babbling을 돌면서 기본적으로 발음할 수 있는 baseMabbling의 요소를 포함하는지 확인한다.
        int answer = 0;
        for (String str : babbling) {
            // 요소 검증
            int baseIdx = 0;
            while (!str.isEmpty() && baseIdx < BASE_MABBLING.length) {
                if (str.contains(BASE_MABBLING[baseIdx]) && str.indexOf(BASE_MABBLING[baseIdx]) == 0) {
                    System.out.println("제거전: " + str);
                    str = str.replace(BASE_MABBLING[baseIdx], "");
                    System.out.println("제거후: " + str);
                    baseIdx = 0;
                } else baseIdx++;
            }
            if (str.isEmpty()) answer++;
        }
        return answer;
    }
    
}