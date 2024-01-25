#include <string>
#include <vector>

using namespace std;

vector<vector<int>> solution(vector<int> num_list, int n) {
    vector<vector<int>> answer(num_list.size() / n, vector<int>(n));
    int col_idx = 0;
    for (int idx = 0; idx < num_list.size(); idx++) {
        if (col_idx == n) col_idx = 0;
        int answer_idx = idx / n;
        answer[answer_idx][col_idx++] = num_list[idx];
    }
    return answer;
}