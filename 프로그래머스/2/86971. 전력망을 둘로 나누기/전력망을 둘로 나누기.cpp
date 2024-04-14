#include <string>
#include <vector>

using namespace std;

// 트리 연결상태를 저장하는 양방향 그래프
vector<int> map[200];

int DFS(int togo, int now, int count) {
    for (int idx = 0; idx < map[now].size(); idx++) {
        // 이전에 방문했던 노드(to go)를 제외하고 탐색
        if (map[now][idx] != togo) {
            // 다음 노드로 이동하면서 count를 증가
            count = DFS(now, map[now][idx], count + 1);
        }
    }
    return count;
}

// 트리니까 단방향이든 양방향이든 그래프를 통해 검사한다.
// n = 송전탑 개수
int solution(int n, vector<vector<int>> wires) {
    int answer = 99999;
    
    // 트리 연결 상태를 양방향 그래프로 저장
    for (auto wire : wires) {
        map[wire[0]].push_back(wire[1]);
        map[wire[1]].push_back(wire[0]);
    }
    
    // 저장된 양방향 그래프를 토대로 탐색 시작
    for (auto wire : wires) {
        int sum = DFS(wire[0], wire[1], 1);
        // 전체 송전탑 개수에서 DFS로 탐색된 송전탑 수의 두 배를 빼면 두 그룹 간의 차이를 계산할 수 있다.
        int comp = n - (2 * sum);
        // 계산된 차이의 절대값이 이전에 계산된 최소값보다 작다면 갱신
        answer = min(answer, abs(comp));
        
    }
    
    return answer;
}