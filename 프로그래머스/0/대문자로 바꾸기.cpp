#include <string>
#include <vector>

using namespace std;

string solution(string myString) {
    string answer = "";
    int length = myString.length();
    for(int i=0; i<length; i++) {
        if('A' <= myString.at(i) && myString.at(i) <= 'Z')
            answer+=myString.at(i);
        else
            answer+=(myString.at(i)-32);
    }
    return answer;
}