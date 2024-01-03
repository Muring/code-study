#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, vector<int> slicer, vector<int> num_list) {
    vector<int> answer;
    int start = 0, end = 0;
    int interval = 1;
    
    if(n == 1){
        start = 0;
        end = slicer.at(1);
        interval = 1;
    }
    else if(n == 2){
        start = slicer.at(0);
        end = num_list.size() - 1;
        interval = 1;
    }
    else if(n == 3){
        start = slicer.at(0);
        end = slicer.at(1);
        interval = 1;
    }
    else if(n == 4){
        start = slicer.at(0);
        end = slicer.at(1);
        interval = slicer.at(2);
    }
    
    for(int idx = start; idx <= end; idx += interval){
        answer.push_back(num_list.at(idx));
    }
    return answer;
}