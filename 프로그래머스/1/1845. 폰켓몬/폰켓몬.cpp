#include <vector>
#include <unordered_map>

using namespace std;

int solution(vector<int> nums)
{
    unordered_map<int, int> hash;
    
    for(int num : nums) {
        hash[num]++;
    }
    return min(hash.size(), nums.size() / 2);
}