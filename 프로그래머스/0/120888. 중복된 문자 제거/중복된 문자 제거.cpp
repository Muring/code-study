#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string solution(string my_string) {
    string answer = "";
    vector<string> str;
    
    for(int idx = 0; idx < my_string.size(); idx++) {
        // find 함수를 사용해 임시 저장 str 벡터에 해당 문자가 있는지 확인한다.
        // 이때 my_string의 각 요소는 char형이기 때문에 string으로 바꿔서 확인한다.
        if(find(str.begin(), str.end(), string(1, my_string[idx])) == str.end()) {
            answer += string(1, my_string[idx]);
        }
        str.push_back(string(1, my_string[idx]));
    }
    
    return answer;
}