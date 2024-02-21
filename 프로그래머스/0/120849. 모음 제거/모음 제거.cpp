#include <string>
#include <vector>
#include <algorithm>

using namespace std;

string solution(string my_string) {
    string vowel_string = "aeiou";
    for(char c : vowel_string) {
        my_string.erase(remove(my_string.begin(), my_string.end(), c), my_string.end());
    }
    return my_string;
}