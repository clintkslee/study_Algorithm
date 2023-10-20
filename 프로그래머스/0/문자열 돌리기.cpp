#include <iostream>
#include <string>

using namespace std;

int main(void) {
    string str;
    cin >> str;
    for(int i=0; i<str.length(); i++){ 
        cout << str.at(i) << endl;
    }
    return 0;
}

// str.at(i) 함수