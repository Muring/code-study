class Solution {
    public static int solution(String name) {
        int answer = 0;
        int move = name.length() - 1; // 기본 최소 좌우 이동 횟수
        int len = name.length();         // 각 커서의 알파벳의 변경 최솟값

        for (int idx = 0; idx < len; idx++) {
            // 알파벳의 값을 확인하고 위로 바꿀지 아래로 바꿀지 결정 후 계산
            answer += Math.min(name.charAt(idx) - 'A', 'Z' - name.charAt(idx) + 1);

            // 연속된 A 길이 찾기
            int next = idx + 1;
            while (next < len && name.charAt(next) == 'A') {
                next++;
            }

            // 좌우 이동 최솟값 구하기
            move = Math.min(move, (idx * 2) + len - next);
            move = Math.min(move, (len - next) * 2 + idx);
        }
        answer += move;

        return answer;
    }
}