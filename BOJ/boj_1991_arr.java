package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <트리 순회>
 * 링크
 * https://www.acmicpc.net/problem/1991
 * 요약
 * 트리를 전위, 중위, 후위 순회하여 출력
 * 풀이
 * 배열로 트리 구현, 각각 재귀 함수로 출력
 */
public class boj_1991_arr {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N;
	static char[] tree;
	static String temp1, temp2, temp3;
	static int maxIdx;

	public static void main(String[] args) throws IOException {
		// 입력(트리 생성)
		N = Integer.parseInt(br.readLine()); // 노드 개수
		maxIdx = (int) Math.pow(2, N); // 루트=1, N개 노드일 떄 최악(skewed)인 경우 마지막 노드가 가능한 인덱스 2^N-1
		tree = new char[maxIdx];
		for (int i = 1; i < maxIdx; i++)
			tree[i] = '.'; // 트리 초기화
		st = new StringTokenizer(br.readLine()); // [1] : A (루트노드)
		temp1 = st.nextToken();
		temp2 = st.nextToken();
		temp3 = st.nextToken();
		tree[1] = temp1.charAt(0); // root
		tree[2] = temp2.charAt(0); // root 왼쪽자식
		tree[3] = temp3.charAt(0); // root 오른쪽자식
		for (int i = 1; i < N; i++) { // 트리 생성, 루트 제외한 N-1 회 반복하여 노드 추가
			st = new StringTokenizer(br.readLine());
			temp1 = st.nextToken();
			temp2 = st.nextToken();
			temp3 = st.nextToken();
			for (int j = 2; j < maxIdx; j++) {
				if (tree[j] == temp1.charAt(0)) { // 현재 노드 찾으면 자식 추가
					if (temp2.charAt(0) != '.')
						tree[j * 2] = temp2.charAt(0);
					if (temp3.charAt(0) != '.')
						tree[j * 2 + 1] = temp3.charAt(0);
				}
			}
		}
		// 풀이
		solution();
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() throws IOException {
		preOrder(1);
		sb.append("\n");
		inOrder(1);
		sb.append("\n");
		postOrder(1);
	}

	private static void preOrder(int idx) throws IOException {
		if (idx >= maxIdx || tree[idx] == '.')
			return;
		sb.append(tree[idx]);
		preOrder(idx * 2);
		preOrder(idx * 2 + 1);

	}

	private static void inOrder(int idx) throws IOException {
		if (idx >= maxIdx || tree[idx] == '.')
			return;
		inOrder(idx * 2);
		sb.append(tree[idx]);
		inOrder(idx * 2 + 1);
	}

	private static void postOrder(int idx) throws IOException {
		if (idx >= maxIdx || tree[idx] == '.')
			return;
		postOrder(idx * 2);
		postOrder(idx * 2 + 1);
		sb.append(tree[idx]);
	}

}
