package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
* <톱니바퀴> G5
* 링크
* https://www.acmicpc.net/problem/14891
* 요약
* 열심히 잘 풀기 ^^
* 풀이
* 시뮬레이션
*/
public class boj_14891 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int K; // 자석 회전 횟수
	static int[][] magnet; // [4][8] 자석 4개, i번 자석의 자성정보
	static int[][] rotate; // [2][2] i번째 회전 방향

	public static void main(String[] args) throws IOException {
		// 입력
		magnet = new int[4][8];
		for (int i = 0; i < 4; ++i) {
			String str = br.readLine().trim();
			for (int j = 0; j < 8; ++j) {
				magnet[i][j] = Integer.parseInt(str.charAt(j)+"");
			}
		}
		K = Integer.parseInt(br.readLine().trim());
		// 풀이
		for (int i = 0; i < K; ++i) {
			st = new StringTokenizer(br.readLine().trim());
			int magNum = Integer.parseInt(st.nextToken()) - 1; // 회전시킬 자석
			int rotDir = Integer.parseInt(st.nextToken()); // 회전 방향 1:시계, -1:반시계
			if (magNum == 0) { // 0번 자석 회전 시킬 때,
				if (magnet[0][2] != magnet[1][6]) { // 1번 자석 회전 시킬 수 있으면
					if (magnet[1][2] != magnet[2][6]) { // 2번 자석 회전 시킬 수 있으면
						if (magnet[2][2] != magnet[3][6]) { // 3번 자석 회전 시킬 수 있으면
							rotateMagnet(3, -rotDir); // 3번 회전
							rotateMagnet(2, rotDir); // 2번 회전
						} else { // 3번 자석 회전 시킬 수 없으면 2번까지 회전시키고 종료
							rotateMagnet(2, rotDir);
						}
						rotateMagnet(1, -rotDir);
					} else { // 2번 자석 회전 시킬 수 없으면 1번까지 회전시키고 종료
						rotateMagnet(1, -rotDir);
					}
					rotateMagnet(0, rotDir);
				} else { // 1번 자석 회전 시킬 수 없으면 0번만 돌리고 종료
					rotateMagnet(0, rotDir);
				}
			} else if (magNum == 1) { // 1번 자석 회전 시킬 때,
				if (magnet[1][6] != magnet[0][2]) {
					rotateMagnet(0, -rotDir);
				}
				if (magnet[1][2] != magnet[2][6]) {
					if (magnet[2][2] != magnet[3][6]) {
						rotateMagnet(3, rotDir);
						rotateMagnet(2, -rotDir);
					} else {
						rotateMagnet(2, -rotDir);
					}
					rotateMagnet(1, rotDir);
				} else {
					rotateMagnet(1, rotDir);
				}

			} else if (magNum == 2) { // 2번 자석 회전 시킬 때,
				if (magnet[3][6] != magnet[2][2]) {
					rotateMagnet(3, -rotDir);
				}
				if (magnet[2][6] != magnet[1][2]) {
					if (magnet[1][6] != magnet[0][2]) {
						rotateMagnet(0, rotDir);
						rotateMagnet(1, -rotDir);
					} else {
						rotateMagnet(1, -rotDir);
					}
					rotateMagnet(2, rotDir);
				} else {
					rotateMagnet(2, rotDir);
				}

			} else if (magNum == 3) { // 3번 자석 회전 시킬 때,
				if (magnet[3][6] != magnet[2][2]) {
					if (magnet[2][6] != magnet[1][2]) {
						if (magnet[1][6] != magnet[0][2]) {
							rotateMagnet(0, -rotDir);
							rotateMagnet(1, rotDir);
						} else {
							rotateMagnet(1, rotDir);
						}
						rotateMagnet(2, -rotDir);
					} else {
						rotateMagnet(2, -rotDir);
					}
					rotateMagnet(3, rotDir);
				} else {
					rotateMagnet(3, rotDir);
				}
			}
		}
		int ans = getAnswer(); // 화살표 위치 [0] 의 값 확인
		// 출력
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
	}

	private static void rotateMagnet(int i, int dir) { // 자석 번호, 회전 방향
		int[] mag = magnet[i];
		if (dir > 0) { // 시계 방향으로 회전
			int temp = mag[7];
			for (int d = 6; d >= 0; d--)
				mag[d + 1] = mag[d];
			mag[0] = temp;
		} else { // 반시계 방향으로 회전
			int temp = mag[0];
			for (int d = 1; d <= 7; d++)
				mag[d - 1] = mag[d];
			mag[7] = temp;
		}
	}

	private static int getAnswer() {
		int ans = 0;
		for (int i = 0; i < 4; ++i) {
			if (magnet[i][0] == 1) {
				ans += Math.pow(2, i);
			}
		}
		return ans;
	}
}