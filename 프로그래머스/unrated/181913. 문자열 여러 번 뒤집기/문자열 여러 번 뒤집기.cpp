#include <string>
#include <vector>

using namespace std;

string solution(string my_string, vector<vector<int>> queries) {
    string answer = "";
    for(auto query : queries){
        for(int idx = 0; idx <= query[1] / 2; idx++){
            if(query[0] + idx > query[1] - idx) break;
            char ch = my_string[query[0] + idx];
            my_string[query[0] + idx] = my_string[query[1] - idx];
            my_string[query[1] - idx] = ch;
        }
    }
    return my_string;
}