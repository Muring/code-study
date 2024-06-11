class Solution {
    /**
     * @param number 숫자 문자열
     * @param k      제거할 숫자 개수
     * @return 가장 큰 숫자 문자열 반환
     */
    public static String solution(String number, int k) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        int len = number.length() - k;    // 정답 길이
        char[] arr = number.toCharArray();  // char 배열에 각각 입력

        int start = 0; // 문자 비교를 시작하는 인덱스

        for (int idx = 0; idx < len; idx++) {
            char max = '0';
            for (int startIdx = start; startIdx <= idx + k; startIdx++) {
                // 가장 큰 수를 골라서 다음 인덱스 시작 지점 지정
                if (arr[startIdx] > max) {
                    max = arr[startIdx];
                    start = startIdx + 1;
                }
            }
            // 가장 큰 수를 저장
            sb.append(Character.toString(max));
        }
        answer = sb.toString();
        return answer;
    }
}