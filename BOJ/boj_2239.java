package BOJ;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <스도쿠> G4
 * 링크
 * https://www.acmicpc.net/problem/2239
 * 요약
 * 스도쿠
 * 풀이
 * dfs + bt
 * 스도쿠판의 빈 칸을 순서대로 탐색하면서 넣을 수 있는 작은 값 부터 넣어보기
 * 마지막 칸 도달 시 모든 칸 채워 넣은 첫 정답이므로 출력
 */
public class boj_2239 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int[][] sudoku = new int[9][9];
	static boolean answer = false;

	public static void main(String[] args) throws IOException {
		// 입력
		for (int i = 0; i < 9; ++i) {
			String str = br.readLine();
			for (int j = 0; j < 9; ++j) {
				sudoku[i][j] = str.charAt(j) - '0';
			}
		}

		// 풀이
		dfs(0);

		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	private static void dfs(int pos) {
		if (pos == 81) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					sb.append(sudoku[i][j] + "");
				}
				sb.append("\n");
			}
			answer = true;
			return;
		}

		int curi = pos / 9;
		int curj = pos % 9;
		if (sudoku[curi][curj] != 0) { // 현재 위치에 값이 있는 경우에는 그냥 다음 칸으로 넘어간다.
			dfs(pos + 1);
		} else { // 현재 위치가 0인 경우 1~9 넣어보기(dfs)
			// System.out.println("current pos is [" + curi + "][" + curj + "]");
			for (int k = 1; k <= 9; ++k) {
				if (answer)
					return; // 정답 나왔으면 더이상 진행x
				// [curi][curj]에 k 놓을 수 있는 지 확인
				if (checkRow(curi, curj, k))
					continue;
				if (checkCol(curi, curj, k))
					continue;
				if (checkBox(curi, curj, k))
					continue;
				sudoku[curi][curj] = k;
				// System.out.println("##################");
				// for (int i = 0; i < 9; ++i) {
				// for (int j = 0; j < 9; ++j) {
				// System.out.print(sudoku[i][j] + " ");
				// }
				// System.out.println();
				// }
				// System.out.println("##################");
				dfs(pos + 1);
				sudoku[curi][curj] = 0;
			}
		}
	}

	private static boolean checkRow(int i, int jj, int k) {
		for (int j = 0; j < 9; ++j) {
			if (sudoku[i][j] == k) {
				// System.out.println("Row : cannot put "+k+" on [" + i + "][" + jj + "]");
				return true;
			}
		}
		return false;
	}

	private static boolean checkCol(int ii, int j, int k) {
		for (int i = 0; i < 9; ++i) {
			if (sudoku[i][j] == k) {
				// System.out.println("Col : cannot put "+k+" on [" + ii + "][" + j + "]");
				return true;
			}
		}
		return false;

	}

	private static boolean checkBox(int i, int j, int k) {
		int starti = i / 3 * 3;
		int startj = j / 3 * 3;
		for (int ii = 0; ii < 3; ++ii) {
			for (int jj = 0; jj < 3; ++jj) {
				if (sudoku[starti + ii][startj + jj] == k) {
					// System.out.println("Box : cannot put "+k+" on ["+i+"]["+j+"]");
					return true;
				}
			}
		}
		return false;
	}

}