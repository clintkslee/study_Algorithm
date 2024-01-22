#include <string>
#include <vector>

using namespace std;

int solution(vector<int> num_list) {
    int answer = 0;
    int size=num_list.size();
    string odd="";
    string eve="";
    for(int i=0; i<size; i++) {
        if(num_list[i]%2)   odd+=to_string(num_list[i]); 
        else                eve+=to_string(num_list[i]); 
    }
    answer=stoi(odd)+stoi(eve);
    return answer;
}