#include <string>
#include <vector>

using namespace std;

long long solution(string numbers) {
    long long answer = 0;
    vector<string> num_list = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    for(int idx = 0; idx < num_list.size(); idx++) {
        if(numbers.find(num_list[idx]) == string::npos) continue;
        while(numbers.find(num_list[idx]) != string::npos) {
            numbers.replace(numbers.find(num_list[idx]), num_list[idx].size(), to_string(idx));
        }
    }
    answer = stoll(numbers);
    return answer;
}