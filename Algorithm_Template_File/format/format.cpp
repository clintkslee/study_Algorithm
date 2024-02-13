#include<iostream>
#include <string>
#include <algorithm>
#include <vector>
#include <map>
#include <queue>
#include <deque>
#include <list>
#include <set>
#include <stack>


using namespace std;

int main(int argc, char** argv)
{	
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	
	pair<int, string> p;
	vector<int> v;
	map<int, string> m; 
	queue<int> q;
	deque<int> d;
	list<int> l;
	set<int> s;
	stack<int> t;
	
	cout << "hello world!\n";

	return 0;
}






// 라인 주석
// ctrl K C <-> Ctrl K U

// input 스트림 비우기
// cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n'); 

// 람다 함수
// [](int a, int b) { cout << a+b << ' '; }(30, 70); 

// pair
// 두 가지 값을 하나의 객체로 묶음
// fisrt, second 로 접근
// pair<int, string> myPair(42, "Hello");
// cout << myPair.first << ", " << myPair.second << '\n';
// map에 사용됨

// vector
// 가변 배열
// vector<int> v = {1, 2, 4, 8, 16, 7};
// v.assign(5, 0); // {0, 0, 0, 0, 0}
// for (const auto &element : myVector) {	// 범위 기반 loop
//         std::cout << element << " ";
// }
// for (std::vector<int>::iterator it = myVector.begin(); it != myVector.end(); ++it) {	// 반복자 이용 loop
//         std::cout << *it << " ";
// }
// sort(v.begin(), v.end(), [](int a, int b) { return a < b; }); // 오름차순 정렬
// v.erase(v.begin() + i); // v[i] 삭제(pop)
// front() : 첫 번째 원소 (<type> 형)
// back() : 마지막 원소
// begin() : 첫번째 위치 (vector<type> iterator 형)
// end() : 마지막의 다음 위치 (비어있는 벡터 시 begin()과 동일)
// push_back() : 마지막에 데이터 추가
// pop_back() : 마지막에서 데이터 뽑기
// size() : 원소의 개수
// clear() : 비우기

// map
// key-value 쌍으로 이루어진 레드블랙트리 자료구조, key 의 중복 허용x
// 저장 시 자동으로 key 기준 오름차순 정렬
// 이진트리사용 -> 검색, 삽입, 삭제 연산의 시간 복잡도는 O(log n)
// map<int, string> m
// map<int, string, greater> m;	// 내림차순 
// 삽입 : key 중복 시 수행되지 않음
// 함수로 삽입 m.insert({0, "Lee"}); 
// 인덱스(키)로 삽입 m[1]="Kang"; (이미 존재 시 덮어쓰기)
// 접근
// cout << myMap[1] << '\n';
// 순회
// for (const auto &pair : m) {
// 	cout << pair.first << ": " << pair.second << '\n';
// }
// 검색
// auto 는 std::map<int, std::string>::iterator 과 동일
// auto it = m.find(3);
// if (it != m.end()) {
//     std::cout << "Value for key 3: " << it->second << std::endl;
// }
// // 삭제
// auto it = m.find(2);
// if (it != m.end()) {
// 	m.erase(it);
// }
// // 크기
// m.size();
// // 맵이 비어 있는지 확인
// m.empty();
// // 맵의 요소 전체 삭제
// m.clear() 

// queue
// FIFO(First In First Out) 자료구조
// BFS 유용
// push() : 마지막에 데이터 추가
// pop() : 맨앞에서 데이터 뽑기
// front() : 첫 번째 원소
// back() : 마지막 원소
// size() : queue의 크기
// empty() : queue가 비어있는지 확인

// deque
// push_front(value)
// push_back(value)
// pop_front()
// pop_back()
// front() : 첫 번째 원소
// back() : 마지막 원소
// size() : queue의 크기
// empty() : queue가 비어있는지 확인

// list (DLL) / forward_list (SLL)
// 삽입과 삭제가 빠르게 수행
// 중간에 요소를 삽입하거나 삭제하는 작업이 많은 경우에 유용

// push_back(value) 및 push_front(value)
// begin() 및 end() //반복자(iterator) 반환
// insert(position, value)
// erase(position) 또는 erase(value) //특정 위치의 요소를 삭제, 특정 값을 가진 요소 삭제
// size()
// empty()
// front() 및 back() // 각각 리스트의 맨 앞과 맨 뒤에 있는 요소를 반환
// pop_front() 및 pop_back() // 각각 리스트의 맨 앞과 맨 뒤에 있는 요소를 제거
// clear() // 리스트의 모든 요소를 제거
// splice(position, otherList) // 다른 리스트의 모든 요소를 특정 위치에 이동, 두 리스트 간에 요소를 이동하거나 병합하는 데 사용

// // 특정 위치에 요소 삽입
// auto it = std::find(myList.begin(), myList.end(), 2);
// if (it != myList.end()) {
//     myList.insert(it, 4);
// }

// // list2의 모든 요소를 list1의 맨 뒤로 이동
// list1.splice(list1.end(), list2);

// // list2에 특정 위치에다가 list1의 일부를 이동
// auto it = std::find(list1.begin(), list1.end(), 3);
// if (it != list1.end()) {
//     list2.splice(list2.begin(), list1, it, list1.end()); //  list1에서 it 위치부터 끝까지의 일부 요소를 list2의 맨 앞으로 이동
// }

// set
// map 과 유사하나 키만 저장하고 키로 정렬됨 (unordered_set 사용 시 정렬x)

// stack
// push(val): 스택에 원소를 추가
// emplace(args...): 스택에 새로운 원소를 생성하고 추가
// pop(): 맨 위에 있는 원소를 제거
// top(): 맨 위에 있는 원소에 접근
// empty(): 비어 있는지 여부를 확인
// size(): 원소의 수를 반환
// swap(stack2): 현재 스택과 다른 스택을 교환
// 대입하여 스택 복사 : copiedStack = originalStack;
// 생성자로 스택 복사 : stack<int> copiedStack(originalStack);  
