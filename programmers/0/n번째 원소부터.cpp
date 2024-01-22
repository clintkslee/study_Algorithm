#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> num_list, int n) {
    vector<int> answer;
    int size=num_list.size();
    for(int i=n-1; i<size; i++){
        answer.push_back(num_list.at(i));
    }
    return answer;
}