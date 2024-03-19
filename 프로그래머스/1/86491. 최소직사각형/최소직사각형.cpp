#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> sizes) {
    int temp = 0;
    
    // 배열을 [작은 수, 큰 수] 형식으로 모두 정렬한다.
    for (int idx = 0; idx < sizes.size(); idx++) {
        if (sizes[idx][0] > sizes[idx][1]) {
            temp = sizes[idx][0];
            sizes[idx][0] = sizes[idx][1];
            sizes[idx][1] = temp;
        }
    }

    int first_largest = 0;
    int second_largest = 0;
    
    // 정렬한 배열에서 각 왼쪽, 오른쪽에서의 최대 값을 구해 곱한다.
    for (vector<int> pair : sizes) {
        first_largest = max(first_largest, pair[0]);
        second_largest = max(second_largest, pair[1]);
    }

    return first_largest * second_largest;
}