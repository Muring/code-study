#include <string>
#include <vector>

using namespace std;

int answer = 0;

void checkComb(vector<int> comb, vector<int> numbers, int target) {
    int result = 0;
    for(int idx = 0; idx < comb.size(); idx++) {
        result += numbers[idx] * comb[idx];
    }
    
    if(target == result) {
        answer++;
    }
}

void generateComb(vector<int> comb, int index, int size, vector<int> numbers, int target) {
    
    if(index == size) {
        checkComb(comb, numbers, target);
        return;
    }
    
    comb[index] = -1;
    generateComb(comb, index + 1, size, numbers, target);
    
    comb[index] = 1;
    generateComb(comb, index + 1, size, numbers, target);
}

int solution(vector<int> numbers, int target) {
    vector<int> comb(numbers.size());
    generateComb(comb, 0, numbers.size(), numbers, target);
    return answer;
}