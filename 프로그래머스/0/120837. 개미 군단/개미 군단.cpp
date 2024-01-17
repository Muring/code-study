#include <string>
#include <vector>

using namespace std;

int solution(int hp) {
    int answer = 0;
    vector<int> damage = {5, 3, 1};
    for(int idx = 0; idx < damage.size(); idx++) {
        if(hp  == 0) break;
        answer += hp / damage[idx];
        hp %= damage[idx];
    }
    return answer;
}