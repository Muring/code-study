#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    queue<int> que;
    for(const auto priority : priorities) que.emplace(priority);
    sort(priorities.begin(), priorities.end(), greater<int>());
    
    while(true) {
        if(que.front() == priorities[0]) {
            answer++;
            que.pop();
            priorities.erase(priorities.begin());
            if(location == 0) return answer;
            else location--;
        }
        else {
            que.emplace(que.front());
            que.pop();
            location--;
            if(location < 0) location = que.size() - 1;
        }
    }

    return answer;
}