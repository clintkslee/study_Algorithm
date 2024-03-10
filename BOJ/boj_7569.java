package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <리모컨> G5
 * 링크
 * https://www.acmicpc.net/problem/1107
 * 요약
 * 고장난 버튼이 있는 리모콘으로 N번 채널 가기 위해 최소 몇 번 버튼을 눌러야 하는 지 계산하기
 * 풀이
 * 1. 고장나지 않은 버튼으로 N과 최대한 가까운 번호 만들기
 * 		N에서 +1, -1 해가며 만들 수 있는 숫자인 지 확인
 * 		100에서 한칸씩 가는 것 보다 가까운 지 확인
 * 2. 차이만큼 ++ 또는 --
 */
public class boj_7569 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N; // 가고 싶은 채널 번호
	static int btPressCnt = Integer.MAX_VALUE;
	static int M; // 고장난 버튼 개수
	static HashSet<Integer> set = new HashSet<>();

	public static void main(String[] args) throws IOException {
		// 입력
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		if (M > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				set.add(Integer.parseInt(st.nextToken())); // 고장난 번호들
			}
		}
		// 풀이
		solution();
		sb.append(btPressCnt);
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		int diff = Math.abs(N - 100); // 100번 채널에서 + 또는 - 버튼만 눌러 N번 채널에 도달할 때 누르는 버튼 수
		if (diff == 0) { // 보고 싶은 채널이 100번인 경우
			// System.out.println("100번 보고 싶으면 리모컨 안눌러도 됨");
			btPressCnt = 0;
			return;
		} else if (M == 10) { // 버튼 전부 고장난 경우
			// System.out.println("버튼 전부 고장났으면 +이나 -만 누를 수 있음");
			btPressCnt = diff;
			return;
		}
		// 살아있는 숫자 버튼 있으므로 근사치 찾기 가능
		int posVal = N; // N보다 큰 근사값, N부터 시작 : 사용할 수 있는 숫자 눌러서 바로 만들 수 있는 경우부터 시작
		while (true) {
			if (isValid(posVal, numCnt(posVal))) { // posVal이 만들 수 있는 숫자이면
				// System.out.println(posVal+" is valid and its 자릿수="+numCnt(posVal));
				break;
			}
			posVal++;
		}
		int negVal = N; // N보다 작은 근사값
		while (true) {
			if (negVal < 0)
				break; // -1 되면 만들 수 있는 N보다 작은 근사값 없음
			if (isValid(negVal, numCnt(negVal))) { // negVal이 0이상의 만들 수 있는 숫자이면
				// System.out.println(negVal+" is valid and its 자릿수="+numCnt(negVal));
				break;
			}
			negVal--;
		}
		if (negVal < 0)
			btPressCnt = numCnt(posVal) + (posVal - N); // 근사값 누르는 버튼 횟수 + -버튼 누르는 횟수
		else {
			int posCnt = numCnt(posVal) + (posVal - N);
			int negCnt = numCnt(negVal) + (N - negVal);
			btPressCnt = Math.min(posCnt, negCnt);
		}
		btPressCnt = Math.min(diff, btPressCnt);
	}

	// 자릿수 반환하는 함수 n범위 0~500000
	private static int numCnt(int n) {
		int cnt = 1;
		for (int i = 10; i <= 1_000_000; i *= 10, cnt++) {
			if (n % i == n)
				break;
		}
		return cnt;
	}

	// val이 만들 수 있는 숫자인 지 판단
	private static boolean isValid(int val, int numCnt) {
		if (set.isEmpty())
			return true; // 고장난 버튼 없으면 무조건 만들 수 있는 숫자
		for (int i = 1; i <= numCnt; i++) {
			if (set.contains(val % 10)) {
				return false;
			}
			val /= 10;
		}
		return true;
	}
}
