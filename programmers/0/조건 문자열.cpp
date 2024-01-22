#include <string>
#include <vector>

using namespace std;

int solution(string ineq, string eq, int n, int m) {
    int answer = 0;
    bool cmp1 = ineq[0]=='>'; // 1이면 >, 0이면 <
    bool cmp2 = eq[0]=='='; // 1이면 =, 0이면 !

    if(cmp1) {
        if(cmp2) {      // >=
            answer = n>=m;
        }
        else {          // >
            answer = n>m;
        }
    }
    else {
        if(cmp2) {      // <=
            answer = n<=m;
        }
        else {          // <
            answer = n<m;            
        }
    }

    return answer;
}