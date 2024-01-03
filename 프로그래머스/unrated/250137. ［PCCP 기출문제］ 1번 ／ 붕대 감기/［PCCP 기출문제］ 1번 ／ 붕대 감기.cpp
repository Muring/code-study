#include <string>
#include <vector>

using namespace std;

int solution(vector<int> bandage, int health, vector<vector<int>> attacks) {
    int answer = 0;

    // 생존 여부 변수 생성
    bool dead = false;

    // 붕대의 정보를 변수에 저장
    int heal_time = bandage.at(0);
    int dot_heal = bandage.at(1);
    int extra_heal = bandage.at(2);
    // 유저의 정보 저장
    int current_health = health;

    // 첫번째 공격
    current_health -= attacks[0][1];

    // 생존 여부 확인
    if (current_health <= 0) dead = true;

    // 이후 공격
    if (attacks.size() > 1 & dead != true) {
        // 회복
        for (int idx = 1; idx < attacks.size(); idx++) {
            // 다음 공격까지의 시간 계산
            int attack_gap = attacks[idx][0] - attacks[idx - 1][0] - 1;
            // 다음 공격까지의 시간이 보너스 회복이 가능한 만큼의 시간이라면 추가회복
            current_health += (attack_gap / heal_time) * extra_heal;
            // 나머지 시간동안 도트 회복
            current_health += attack_gap * dot_heal;
            // 최대 체력 확인
            if (current_health > health) current_health = health;

            // 공격
            current_health -= attacks[idx][1];

            // 사망 확인
            if (current_health <= 0) {
                dead = true;
                break;
            }
        }
    }

    if (dead == true)
        answer = -1;
    else
        answer = current_health;
    
    return answer;
}