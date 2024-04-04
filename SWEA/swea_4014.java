package SWEA;

import java.io.*;
import java.util.*;

/*
* 제목
* <활주로 건설> D?
* 링크
* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWIeW7FakkUDFAVH
* 요약
* 
* 풀이
* 시뮬레이션
*/
public class swea_4014 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, X; // 지도 한 변 길이, 경사로 길이
	static int[][] map; // 지도
	static int availableCount; // 활주로 건설 가능한 경우의 수

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; ++tc) {
			// 입력
			st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine().trim());
				for (int j = 0; j < N; ++j) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
//			// 입력 검증
//			for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}

			// 풀이
			availableCount = 0;
			buildHorizontalRoad();
			buildVerticalRoad();
			// 출력
			sb.append("#").append(tc).append(" ").append(availableCount).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void buildVerticalRoad() {
		// 세로 방향 활주로 건설
		boolean[][] checkCol = new boolean[N][N];
		for (int j = 0; j < N; ++j) {
			boolean availableCheck = true;
			for (int i = 0; i < N - 1; ++i) {
				// 현 위치와 다음 위치 높이 같으면 계속 진행
				if (map[i][j] == map[i + 1][j])
					continue;

				// 현 위치와 다음 위치의 높이 차이 2 이상일 때 -> 불가능
				if (Math.abs(map[i][j] - map[i + 1][j]) != 1) {
					availableCheck = false;
					break;
				}

				// 1. 다음 위치가 더 높은 경우
				if (map[i][j] < map[i + 1][j]) {
					// 이전 X칸 높이가 같은 지 확인
					int start = i - X + 1;
					boolean checkBeforeXinCol = true;
					for (int k = 0; k < X - 1; ++k) {
						int ni = start++;
						if (ni < 0) { // 경사로가 범위 지도 범위 넘어가면 false
							checkBeforeXinCol = false;
							break;
						}
						if (checkCol[ni][j] == true) { // 이미 경사로 지어진 곳이면 false
							checkBeforeXinCol = false;
							break;
						}
						if (map[ni][j] != map[ni + 1][j]) { // X칸 중 높이 다른 곳 있으면 false
							checkBeforeXinCol = false;
							break;
						}
					}
					if (checkBeforeXinCol == false) {// X칸 조사해서 경사로 지을 수 없는 곳이면 break 하고 다음 행 조사
						availableCheck = false;
						break;
					}
					// 경사로 지을 수 있으면 경사로 지었다고 표시하고 다음 칸 조사
					for (int k = i - X + 1; k <= i; ++k) {
						checkCol[k][j] = true;
					}
				}

				// 2. 다음 위치가 더 낮은 경우
				else if (map[i][j] > map[i + 1][j]) {
					// 다음 X칸 높이가 같은 지 확인
					int end = i + X;
					boolean checkAfterXinCol = true;
					for (int k = 0; k < X - 1; ++k) {
						int ni = end--;
						if (ni > N - 1) { // 경사로가 범위 지도 범위 넘어가면 false
							checkAfterXinCol = false;
							break;
						}
						if (map[ni][j] != map[ni - 1][j]) { // X칸 중 높이 다른 곳 있으면 false
							checkAfterXinCol = false;
							break;
						}

					}
					if (checkAfterXinCol == false) {// X칸 조사해서 경사로 지을 수 없는 곳이면 break 하고 다음 행 조사
						availableCheck = false;
						break;
					}
					// 경사로 지을 수 있으면 경사로 지었다고 표시하고 다음 칸 조사
					for (int k = i + X; k > i; --k) {
						checkCol[k][j] = true;
					}
				}
			}
			if (availableCheck) {
				availableCount++;
			}
		}
	}

	private static void buildHorizontalRoad() {
		// 가로 방향 활주로 건설
		boolean[][] checkRow = new boolean[N][N];
		for (int i = 0; i < N; ++i) {
			boolean availableCheck = true;
			for (int j = 0; j < N - 1; ++j) {
				// 현 위치와 다음 위치 높이 같으면 계속 진행
				if (map[i][j] == map[i][j + 1])
					continue;

				// 현 위치와 다음 위치의 높이 차이 2 이상일 때 -> 불가능
				if (Math.abs(map[i][j] - map[i][j + 1]) != 1) {
					availableCheck = false;					
					break;
				}

				// 1. 다음 위치가 더 높은 경우
				if (map[i][j] < map[i][j + 1]) {
					// 이전 X칸 높이가 같은 지 확인
					int start = j - X + 1;
					boolean checkBeforeXinRow = true;
					for (int k = 0; k < X - 1; ++k) {
						int nj = start++;
						if (nj < 0) { // 경사로가 범위 지도 범위 넘어가면 false
							checkBeforeXinRow = false;
							break;
						}
						if (checkRow[i][nj] == true) { // 이미 경사로 지어진 곳이면 false
							checkBeforeXinRow = false;
							break;
						}
						if (map[i][nj] != map[i][nj + 1]) { // X칸 중 높이 다른 곳 있으면 false
							checkBeforeXinRow = false;
							break;
						}
					}
					if (checkBeforeXinRow == false) {// X칸 조사해서 경사로 지을 수 없는 곳이면 break 하고 다음 행 조사
						availableCheck = false;
						break;
					}
					// 경사로 지을 수 있으면 경사로 지었다고 표시하고 다음 칸 조사
					for (int k = j - X + 1; k <= j; ++k) {
						checkRow[i][k] = true;
					}
				}

				// 2. 다음 위치가 더 낮은 경우
				else if (map[i][j] > map[i][j + 1]) {
					// 다음 X칸 높이가 같은 지 확인
					int end = j + X;
					boolean checkAfterXinRow = true;
					for (int k = 0; k < X - 1; ++k) {
						int nj = end--;
						if (nj > N - 1) { // 경사로가 범위 지도 범위 넘어가면 false
							checkAfterXinRow = false;
							break;
						}
						if (map[i][nj] != map[i][nj - 1]) { // X칸 중 높이 다른 곳 있으면 false
							checkAfterXinRow = false;
							break;
						}

					}
					if (checkAfterXinRow == false) {// X칸 조사해서 경사로 지을 수 없는 곳이면 break 하고 다음 행 조사
						availableCheck = false;
						break;
					}
					// 경사로 지을 수 있으면 경사로 지었다고 표시하고 다음 칸 조사
					for (int k = j + X; k > j; --k) {
						checkRow[i][k] = true;
					}
				}
			}
			if (availableCheck) {
				availableCount++;
			}
		}
	}
}
