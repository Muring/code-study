#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    vector<int> answer;
    queue<int> q;
    int size = speeds.size(); // size를 int형으로 따로 저장해 두는 것이 시간적으로 이득
    for (int i = 0; i < size; ++i)
        q.push(i);
    while(!q.empty()){
        int cnt = 0;
        for(int j = 0; j < size; ++j)
            progresses[j] += speeds[j]; // progresses에 speeds를 더해주는 과정
        while(!q.empty() && progresses[q.front()] >= 100){ // 100이 넘어갔을 때 처리
            ++cnt;
            q.pop();
        }
        if(cnt != 0)
            answer.push_back(cnt);  
    }
    return answer;
}