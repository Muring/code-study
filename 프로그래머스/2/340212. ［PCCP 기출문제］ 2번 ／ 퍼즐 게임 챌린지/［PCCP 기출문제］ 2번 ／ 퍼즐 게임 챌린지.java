class Solution {
    
    public long getTotalTime(int level, int diff, int curTime, int prevTime) {
        return diff <= level ? curTime : (curTime + prevTime) * (diff - level) + curTime;
    }
    
    public int solution(int[] diffs, int[] times, long limit) {
        int start = 1;
        int end = 100000;
        int answer = 0;
        
        while(start <= end) {
            int level = (start + end) / 2;
            long totalTime = 0;
            
            for(int idx = 0; idx < diffs.length; idx++) {
                int currentDiff = diffs[idx];
                int currentTime = times[idx];
                int prevTime = idx != 0 ? times[idx - 1] : 0;
                
                totalTime += getTotalTime(level, currentDiff, currentTime, prevTime);
            }
            
            if(totalTime <= limit) {
                answer = level;
                end = level - 1;
            } else {
                start = level + 1;
            }
        }
        return answer;
    }
}