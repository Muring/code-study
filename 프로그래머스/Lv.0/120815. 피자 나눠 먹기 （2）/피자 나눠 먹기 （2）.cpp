#include <string>
#include <vector>

using namespace std;

// 사람 수 n
int solution(int n) {
    int pieces_count = 6;
    
    while(pieces_count % n != 0) {
        pieces_count += 6;
    }

    return pieces_count / 6;
}