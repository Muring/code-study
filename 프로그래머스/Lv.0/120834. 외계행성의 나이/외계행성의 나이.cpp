#include <string>
#include <vector>

using namespace std;

// a = 97
string solution(int age) {
    string answer = "";

    while(age != 0) {
        int num = age % 10;
        char ch = num + 97;
        string str = "";
        str += ch;
        answer.insert(0, str);
        age /= 10;
    }

    return answer;
}