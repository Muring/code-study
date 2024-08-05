class Solution {
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        answer[0] = x;
        for(int idx = 1; idx < n; idx++) {
            answer[idx] = answer[idx - 1] + x;
        }
        
        return answer;
    }
}