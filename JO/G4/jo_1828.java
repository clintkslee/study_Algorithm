package JO.G4;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <냉장고>
 * 링크
 * https://jungol.co.kr/problem/1828
 * 요약
 * N개의 화학물질, 각 화학물질은 보관 가능 온도가 정해짐, 최소로 필요한 냉장고 개수 구하기
 * 풀이
 * 최소 온도 낮은 순, 같으면 최대온도 낮은 순으로 정렬 후 탐욕
 */
public class jo_1828 {	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;	// 화학물질 수 N
	static List<Temperature> list = new ArrayList<>();
	static Stack<Temperature> fridge = new Stack<>();
    public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Temperature(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		Collections.sort(list);	// min 온도 낮은 순, 동일하면 max 온도 낮은 순으로 정렬
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}
    
	private static void solution() {
		fridge.push(list.get(0));	// 첫번째 물질의 온도를 스택에 삽입 (초기화)
		for(int i=1; i<N; i++) {
			Temperature t = list.get(i);
			int fridgeMax = fridge.peek().max;
			int fridgeMin = fridge.peek().min;
			if(fridgeMax < t.min || fridgeMin > t.max) // 범위 외, 겹치는 부분이 아예 없는 경우엔 새로운 냉장고 도입
				fridge.push(list.get(i));
			else { // 온도가 겹치는 부분이 있다면 min은 더 높은 온도로, max는 더 낮은 온도로 설정
				Temperature temp = fridge.pop(); // 값 수정 위한 pop
				if(temp.max > list.get(i).max) temp.max = list.get(i).max;
				if(temp.min < list.get(i).min) temp.min = list.get(i).min;
				fridge.push(temp); // 수정된 냉장고의 온도
			}
		}
		sb.append(fridge.size()+"\n");
	}

	static class Temperature implements Comparable<Temperature> {
		int min;
		int max;
		
		public Temperature(int min, int max) {
			this.min = min;
			this.max = max;
		}
		
		@Override
		public int compareTo(Temperature o) {
			if(this.min==o.min) return this.max-o.max;
			return this.min-o.min;
		}

		@Override
		public String toString() {
			return "Temperature [min=" + min + ", max=" + max + "]";
		}
	}
}
