#include <string>
#include <vector>

using namespace std;

using namespace std;

vector<int> solution(int brown, int yellow) {
    vector<int> answer;

    brown = brown / 2 + 2;
    int row = 3, col;
    for (int idx = row; idx < brown; idx++) {
        col = brown - idx;
        if ((idx - 2) * (col - 2) == yellow) {
            answer.push_back(col);
            answer.push_back(idx);
            break;
        }
    }
    return answer;
}