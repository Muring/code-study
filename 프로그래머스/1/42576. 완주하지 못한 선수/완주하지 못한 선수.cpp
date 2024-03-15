#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    unordered_map<string, int> hash;
    
    for(auto player : participant) {
        // 해시에 현재 인물이 없을 경우
        if(hash.find(player) == hash.end())
            hash.insert(make_pair(player, 1));
        // 해시에 동일 이름의 인물이 있을 경우
        else
            hash[player]++;
    }
    
    for(auto player : completion) {
        hash[player]--;
    }
    
    for(auto player : participant) {
        if(hash[player] != 0) {
            answer = player;
            break;
        }
    }
    
    return answer;
}