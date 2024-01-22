#include <string>
#include <vector>

using namespace std;

int solution(vector<int> num_list) {
    int answer = 0;
    int num1=1, num2=0;
    int size = num_list.size();
    for(int i=0; i<size; i++) {
        num1*=num_list[i];
        num2+=num_list[i];
    }
    num2*=num2;
    answer = (num1>num2) ? 0 : 1;
    return answer;
}