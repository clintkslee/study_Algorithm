#include <string>
#include <vector>

using namespace std;

string solution(string my_string, string overwrite_string, int s) {
    string answer = "";
    answer.append(my_string);
    answer.replace(s, overwrite_string.length(), overwrite_string);
    return answer;
}

// string.replace(문자열 대체 시작 인덱스, 대체할 문자열 길이, 대체할 문자열);