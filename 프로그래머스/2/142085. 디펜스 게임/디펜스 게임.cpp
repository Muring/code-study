#include <string>
#include <vector>
#include <queue>

using namespace std;

/*
	디펜스 게임 programmers lv2

	n은 보유한 병사 수, k는 무적권 수, enemy에는 라운드 당 적의 수가 저장되어있다.
	이걸 조합으로 풀려고 하다니 얼마나 멍청한 짓이었던가.
	숫자가 100만 단위로 반복하는데 모든 경우의 수를 다 구하는 것은 미친짓이다.
	priority queue를 활용하여 문제를 해결할 수 있다.

	1. priority queue로 현재 보유한 병사 -= 현재 적 병사를 하고 현재 적 병사의 수를 큐에 넣는다.
	2. 그러다 현재 보유한 병사가 음수가 되면 큐의 값을 pop하고 무적권을 하나 줄인다.
	3. 현재 보유한 병사가 음수가 됐는데 무적권이 없다면 종료
	   라운드가 모두 끝나면 종료
*/
int solution(int n, int k, vector<int> enemy) {
		int answer = 0;
	priority_queue<int> que;
	
	for (int idx = 0; idx < enemy.size(); idx++) {
		// 현재 보유한 병사가 음수가 됐는데 무적권이 없다면 종료
		if (n < enemy[idx] and k == 0)
			break;

		que.emplace(enemy[idx]);
		n -= enemy[idx];
		answer++;

		while (!que.empty() and n < 0) {
			// 무적권이 없고 병사 수가 모자라게 된다면
			if ((k == 0 and n < 0)) {
				answer--;
				break;
			}

			// 무적권 사용
			n += que.top();
			que.pop();
			k--;
		}
	}
	return answer;
}