import java.util.Arrays;

class Solution {
    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] statusList = new int[n];  // 상태 배열을 사람 수 만큼 생성
        Arrays.fill(statusList, 1); // 상태 배열을 모두 1로 초기화

        // 체육복 도난
        for (int idx : lost) statusList[idx - 1]--;
        // 여벌 체육복
        for (int idx : reserve) statusList[idx - 1]++;
        // 빌려주기 시작
        for (int idx = 0; idx < n; idx++) {
            // 도난 당한 사람일 경우 좌우 확인
            if (statusList[idx] == 0) {
                // 좌
                if (idx - 1 >= 0 && statusList[idx - 1] > 1) {
                    statusList[idx - 1]--;
                    statusList[idx]++;
                    continue; // 동시에 오른쪽 여벌도 가져가기 않기 위해 다음으로 넘김
                }
                // 우
                if (idx + 1 < n && statusList[idx + 1] > 1) {
                    statusList[idx + 1]--;
                    statusList[idx]++;
                }
            }
        }

        for (int status : statusList) {
            if (status > 0) answer++;
        }
        
        return answer;
    }
}