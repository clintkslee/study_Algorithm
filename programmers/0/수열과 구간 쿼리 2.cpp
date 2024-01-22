#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> arr, vector<vector<int>> queries) {
    vector<int> answer;
    int size = queries.size();
    for(int j=0; j<size; j++) {
        int num = -1;
        for(int i=queries[j][0]; i<=queries[j][1]; i++) {
            if(arr[i] > queries[j][2]) {
                if(num == -1)   num=arr[i];
                else num=min(num, arr[i]);  
            }
        }
        answer.push_back(num);
    }
    return answer;
}