#include <string>
#include <vector>

using namespace std;

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;

    // 기본적으로 최대 행의 수를 구한다.
    int maxRow = brown / 2 + 2;
    // 기본 최소 행 수는 3이다.
    int row = 3, col;
    
    for (int idx = row; idx < maxRow; idx++) {
        // 주어진 행으로 열 수를 구한다.
        col = maxRow - idx;
        // 가로 세로 갈색 칸 수를 뺀 남은 칸수가 노란색 면적이면 맞는 가로 세로 크기를 구한 것이다.
        if ((idx - 2) * (col - 2) == yellow) {
            answer.push_back(col);
            answer.push_back(idx);
            break;
        }
    }
    return answer;
}