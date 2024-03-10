package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <AC> G5
 * 링크
 * https://www.acmicpc.net/problem/5430
 * 요약
 * 덱 사용해보기
 * 풀이
 * deque
 */
public class boj_5430 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int T; // tc 개수
	static String p; // 수행할 연산
	static int n; // 배열 길이
	static Deque<Integer> dq;
	static boolean isErr, isReversed;

	public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			dq = new ArrayDeque<>();
			// 입력
			p = br.readLine();
			n = Integer.parseInt(br.readLine()); // 배열 길이
			st = new StringTokenizer(br.readLine(), "[ , ]");
			for (int i = 0; i < n; i++)
				dq.offer(Integer.parseInt(st.nextToken()));
			// 풀이
			solution();
			// 출력
			if (isErr)
				sb.append("error\n");
			else if (dq.isEmpty()) {
				sb.append("[]\n");
			} else {
				sb.append("[");
				if (isReversed) {
					while (!dq.isEmpty())
						sb.append(dq.pollLast() + ",");
				} else {
					while (!dq.isEmpty())
						sb.append(dq.pollFirst() + ",");
				}
				sb.replace(sb.length() - 1, sb.length(), "");
				sb.append("]\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		int len = p.length();
		isErr = false;
		isReversed = false; // false면 앞에서 빼기, true면 뒤에서 빼기
		for (int i = 0; i < len; i++) {
			if (p.charAt(i) == 'R') {
				isReversed = !isReversed;
			} else { // D연산
				if (dq.size() == 0) {
					isErr = true;
					return;
				}
				if (isReversed)
					dq.removeLast();
				else
					dq.removeFirst();
			}
		}
	}
}
