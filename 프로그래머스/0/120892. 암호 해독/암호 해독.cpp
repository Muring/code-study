#include <string>
#include <vector>

using namespace std;

string solution(string cipher, int code) {
    string answer = "";
    
    for(int idx = code - 1; idx < cipher.size(); idx += code) {
        answer += cipher.at(idx);
    }
    return answer;
}