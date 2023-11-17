#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> arr, vector<vector<int>> queries) {
    int numOfQueries = queries.size();
    for(int j=0; j<numOfQueries; j++) {
        int start = queries[j][0];
        int end = queries[j][1];
        int k = queries[j][2];
        for(int i=start; i<=end; i++) { 
            if(i%k==0)
                arr[i]++;
        }
    }   
    return arr;
}