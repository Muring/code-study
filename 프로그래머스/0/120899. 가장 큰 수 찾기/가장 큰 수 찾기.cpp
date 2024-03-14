#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> array) {
    vector<int> answer;
    vector<int> temp = array;
    sort(array.begin(), array.end());
    
    answer.push_back(array[array.size() - 1]);
    answer.push_back(find(temp.begin(), temp.end(), array[array.size() - 1]) - temp.begin());
    return answer;
}