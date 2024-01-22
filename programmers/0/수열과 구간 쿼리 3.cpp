#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> arr, vector<vector<int>> queries) {
    vector<int> answer;
    
    int arr_size = arr.size();
    for(int i=0; i<arr_size; i++)
        answer.push_back(arr[i]);

    int queries_size = queries.size();
    for(int i=0; i<queries_size; i++)
        swap(answer[queries[i][0]], answer[queries[i][1]]);

    return answer;
}