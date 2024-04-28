#include <string>
#include <vector>

using namespace std;

int answer = 50;
bool visited[50];

bool check(string a, string b) {
    int dif_count = 0;

    for (int idx = 0; idx < a.size(); idx++) {
        if (a[idx] != b[idx])
            dif_count++;
    }

    // 다른 문자가 하나일 때
    if (dif_count == 1) {
        return true;
    }
    // 하나가 아닐 때
    return false;
}   

void dfs(string begin, string target, vector<string>words, int step) {
    if (answer <= step) return;

    if (begin == target) {
        answer = min(answer, step);
        return;
    }

    for (int idx = 0; idx < words.size(); idx++) {
        // 한개의 문자만 다르고 방문 하지 않은 단어이면 탐색 시작
        if (check(begin, words[idx]) && !visited[idx]) {
            visited[idx] = true;
            // 그 단어부터 탐색을 다시 시작한다. 단계가 하나 추가되었으므로 step+1을 인자로 넘긴다.
            dfs(words[idx], target, words, step + 1);
            // dfs 재귀 호출하여 종료되어 여기로 돌아오면, 백트래킹 (방문 표시 해제) 하여 다음분기점부터 다시 탐색을 시작한다.
            visited[idx] = false;
        }
    }

    return;
}

int solution(string begin, string target, vector<string> words) {
    dfs(begin,target,words,0);

  // 탐색후 target문자열을 만나지 못했을 때
  if(answer == 50)
    return 0;

  return answer;
}