#include <string>
#include <vector>

using namespace std;

string solution(string str1, string str2) {
    string answer = "";
    int length = str1.length();
    for(int i=0; i<length; i++) {
        answer+=str1.at(i);
        answer+=str2.at(i);
    } 
    return answer;
}