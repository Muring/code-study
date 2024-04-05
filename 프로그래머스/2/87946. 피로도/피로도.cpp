#include <string>
#include <vector>
#include <algorithm>

using namespace std;

// 넘어온 배열을 순차적으로 탐색하면서 k가 최소 필요 피로도 이상인지 확인한다.
// 이상이면 소모 피로도만큼 피로도(k)를 줄이고 던전 탐험 횟수를 1 증가시킨다.
// 여러 종류의 입력값에 따른 최대 던전 탐험 횟수를 구해 반환한다.

const int REQUIRED_FATIGUE = 0;
const int FATIGUE_USE = 1;

int getExploreCount(vector<vector<int>> dungeons, int fatigue) {
    int count = 0;
    
    for(int dungeonIdx = 0; dungeonIdx < dungeons.size(); dungeonIdx++ ) {
        // 현재 피로도가 던전의 최소 필요 피로도에 충분하면
        if(dungeons[dungeonIdx][REQUIRED_FATIGUE] <= fatigue) {
            fatigue -= dungeons[dungeonIdx][FATIGUE_USE];
            count++;
        }
    }
    return count;
}

int solution(int k, vector<vector<int>> dungeons) {
    int answer = -1;
    int count = 0;
    sort(dungeons.begin(), dungeons.end());
        
    do {
        count = getExploreCount(dungeons, k);
        answer = answer > count ? answer : count;
    } while (next_permutation(dungeons.begin(), dungeons.end()));

    return answer;
}