#include <string>
#include <vector>

using namespace std;

string solution(string code) {
    string answer = "";
    int mode = 0;
    int length = code.length();
    for(int idx=0; idx<length; idx++) {
        if(mode) {  //mode == 1
            if(code[idx] == '1') { //code[idx] == 1
                mode = 0;
            }  
            else {          //code[idx] == 0
                if(idx%2==1) {
                    answer+=code[idx];
                }
            }
        }
        else {      //mode == 0 
            if(code[idx] == '1') { //code[idx] == 1
                mode = 1;
            }  
            else {          //code[idx] == 0
                if(idx%2==0) {
                    answer+=code[idx];
                }
            }
        }
    }
    if(answer.length()==0) answer+="EMPTY";
    return answer;
}