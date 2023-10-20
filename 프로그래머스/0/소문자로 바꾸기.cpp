#include <string>
#include <vector>

using namespace std;

string solution(string myString) {
    string answer = "";
    for(int i=0; i<myString.length(); i++){
        if('a'<=myString.at(i)&&myString.at(i)<='z')    answer+=myString.at(i);
        else    answer+=(myString.at(i)+32);
    }
    return answer;
}