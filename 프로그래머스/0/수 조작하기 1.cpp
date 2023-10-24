#include <string>
#include <vector>

using namespace std;

int solution(int n, string control) {
    int answer = n;
    int length = control.length();
    for(int i=0; i<length; i++) {
        switch(control.at(i)) {
            case 'w': answer++; break;
            case 's': answer--; break;
            case 'd': answer+=10; break;
            case 'a': answer-=10; break;
        }
    }
    return answer;
}