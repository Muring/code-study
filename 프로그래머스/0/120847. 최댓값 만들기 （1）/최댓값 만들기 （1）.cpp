#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> numbers) {
    int answer = 0;
    int length = numbers.size() - 1;
    sort(numbers.begin(), numbers.end());
    answer = numbers[length] * numbers[length - 1];
    return answer;
}