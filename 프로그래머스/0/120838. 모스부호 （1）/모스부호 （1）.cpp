#include <string>
#include <vector>

using namespace std;

string solution(string letter) {
    string answer = "";
    string morse[] = {
        ".-","-...","-.-.","-..",".","..-.",
        "--.","....","..",".---","-.-",".-..",
        "--","-.","---",".--.","--.-",".-.",
        "...","-","..-","...-",".--","-..-",
        "-.--","--.." };
    char alphabet[] = { 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o',
                       'p','q','r','s','t','u','v','w','x','y','z' };

    string temp = "";
    for (int idx = 0; idx <= letter.length(); idx++) {
        if (letter[idx] == ' ' || idx == letter.length()) {
            for (int m_idx = 0; m_idx < sizeof(morse)/sizeof(*morse); m_idx++) {
                if (temp == morse[m_idx]) {
                    answer += alphabet[m_idx];
                }
            }
            temp = "";
            continue;
        }
        temp += letter[idx];
    }
    return answer;
}