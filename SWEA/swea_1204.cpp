#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main(int argc, char** argv)
{
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);

	int T;
	cin >> T;

	vector<int> v;
	vector<int> score;
	int input, result;
	for (int i = 0; i < T; i++) {
		cin >> input;   // test case 회차
		for (int j = 0; j < 1000; j++) {
			cin >> input;
			v.push_back(input);
		}

		score.assign(101, 0);
		for (int j = 0; j < 1000; j++) {
			score[v[j]]++;
		}

		result = 0;
		for(int j=1; j<101; j++) {
		     if(score[j] >= score[result]) {
		         result=j;
		     }
		 }
		cout << "#" << (i+1) << " " << result << '\n';
		v.clear();
		score.clear();
	}
	return 0;
}