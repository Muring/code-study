/**
    맵의 모든 열을 확인하여 해당 열로 시추선을 내렸을 때 얻어지는 석유 값을
    모두 구하여 최대 값을 도출한다.
    완전 탐색으로 해당 석유의 크기를 구한다.

    효율성에서 터지는 문제 발생
    효율성을 위해서 bfs 탐색을 한번만 해도 되게 바꾼다.
    먼저 land를 직접적으로 사용하여 bfs를 돌린다. 같은 석유를 그루핑한다.
    이후 열을 돌면서 발견한 그룹의 석유 공간을 더해 답을 구한다.
*/

#include <string>
#include <vector>

using namespace std;

// up down left rights
const int D_ROW[4] = {-1, 1, 0, 0};
const int D_COL[4] = {0, 0, -1, 1};

// map size
int row_size = 0;
int col_size = 0;

// counts
int count = 0;
int max_count = 0;

// group related
int group_idx = 2;
vector<int> group_count;
vector<vector<int>> group_map;

bool isAvailable(int row, int col) {
    if (0 <= row && row < row_size && 0 <= col && col < col_size && group_map[row][col] == 1) return true;
    return false;
}

void groupOil(int row, int col) {
    // visited
    count += 1;
    group_map[row][col] = group_idx;
    for (int delta_idx = 0; delta_idx < 4; delta_idx++) {
        int next_row = row + D_ROW[delta_idx];
        int next_col = col + D_COL[delta_idx];

        // check if next area is available
        if (isAvailable(next_row, next_col)) {
            groupOil(next_row, next_col);
        }
    }
}

int solution(vector<vector<int>> land) {
    // measure map size
    row_size = land.size();
    col_size = land[0].size();
    group_map = land;

    // grouping
    for (int col_idx = 0; col_idx < col_size; col_idx++) {
        // reset
        count = 0;

        for (int row_idx = 0; row_idx < row_size; row_idx++) {
            // when you find oil count existing oil
            if (group_map[row_idx][col_idx] == 1) {
                groupOil(row_idx, col_idx);
                group_idx++;
                group_count.push_back(count);
                count = 0;
            }
        }
    }

    // counting
    for (int col = 0; col < col_size; col++) {
        count = 0;
        vector<bool> used(group_count.size() + 2, false);
        for (int row = 0; row < row_size; row++) {
            if (group_map[row][col] > 1 && used[group_map[row][col]] == false) {
                count += group_count[group_map[row][col] - 2];
                used[group_map[row][col]] = true;
            }
        }
        max_count = max_count > count ? max_count : count;
    }
    return max_count;
}