import java.util.Arrays;

class Solution {
    public static int solution(int k, int[] tangerines) {
        int answer = 0;
        int[] counts = new int[10000001];
        for (int tangerine : tangerines) {
            counts[tangerine]++;
        }

        Arrays.sort(counts);

        for (int idx = counts.length - 1; idx >= 0; idx--) {
            if (k <= 0) break;

            answer++;
            k -= counts[idx];
        }
        return answer;
    }
}