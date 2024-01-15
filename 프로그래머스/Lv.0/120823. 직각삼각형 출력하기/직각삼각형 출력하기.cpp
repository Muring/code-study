#include <iostream>

using namespace std;

int main(void) {
    int n;
    cin >> n;
    for(int idx = 1; idx <= n; idx++) {
        for(int i = 0; i < idx; i++) {
            cout << "*";
        }
        cout << endl;
    }
    return 0;
}