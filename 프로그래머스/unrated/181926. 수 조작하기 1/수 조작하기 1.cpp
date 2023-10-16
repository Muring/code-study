#include <string>
#include <vector>

using namespace std;

int solution(int n, string control) {

    for(int idx = 0; idx < control.length(); idx++){
        if(control[idx] == 'w'){
            n += 1;
        }
        else if(control[idx] == 's'){
            n -= 1;
        }
        else if(control[idx] == 'd'){
            n += 10;
        }
        else if(control[idx] == 'a'){
            n -= 10;
        }
    }
    return n;
}