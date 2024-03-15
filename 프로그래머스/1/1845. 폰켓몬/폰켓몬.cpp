#include <vector>
using namespace std;

int solution(vector<int> nums)
{
    int answer = 0;
    vector<int> num_count(200001, 0);
    for(int num : nums) {
        if(num_count[num] == 0) answer++;
        
        if(answer == nums.size() / 2) break;
            
        num_count[num]++;
        
    }
    return answer;
}