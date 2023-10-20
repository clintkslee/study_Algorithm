#include <iostream>
#include <string>

using namespace std;

int main(void) {
    string str;
    char c;
    cin >> str;

    for(int i=0; i<str.length(); i++) {
        if('A' <= str[i] && str[i] <= 'Z') c=str[i]+32;
        else c = str[i]-32;
        cout << c;
    }
    return 0;
}

// 아스키코드 대소문자 변환 : 소문자-32=대문자, 대문자+32=소문자