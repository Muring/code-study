#include <string>
#include <vector>

using namespace std;

int solution(vector<int> num_list) {
    int answer = 0;
    int mult_sum = 1;
    int sum = 0;
    for(int num : num_list){
        mult_sum *= num;
        sum += num;
    }
    answer = mult_sum > sum * sum ? 0 : 1;
    return answer;
}