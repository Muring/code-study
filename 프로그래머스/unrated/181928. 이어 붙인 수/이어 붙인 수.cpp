#include <string>
#include <vector>

using namespace std;

int solution(vector<int> num_list) {
    int answer = 0;
    string odd_string = "";
    string even_string = "";
    for(int num : num_list){
        if(num % 2 == 0)
            even_string += to_string(num);
        else
            odd_string += to_string(num);
    }
    answer = stoi(even_string) + stoi(odd_string);
    return answer;
}