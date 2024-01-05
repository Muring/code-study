#include <string>
#include <vector>

using namespace std;

int solution(vector<int> num_list) {
    int answer = 0;
    int odd_sum = 0, even_sum = 0;
    
    for(int idx = 0; idx < num_list.size(); idx++) {
        // If idx is odd
        if((idx + 1) % 2 != 0) {
            odd_sum += num_list[idx];
        }
        // If idx is even
        else if((idx + 1) % 2 == 0) {
            even_sum += num_list[idx];
        }
    }
    answer = odd_sum > even_sum ? odd_sum : even_sum;
    return answer;
}