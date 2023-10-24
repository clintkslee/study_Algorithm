#include <string>
#include <vector>

using namespace std;

vector<int> solution(vector<int> num_list) {
    vector<int> answer;
    int size = num_list.size();
    int last = num_list[size-1];
    int before_last = num_list[size-2];
    num_list.push_back((last > before_last) ? (last - before_last) : (last * 2));
    return num_list;
}