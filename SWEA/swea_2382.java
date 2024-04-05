package SWEA;

import java.io.*;
import java.util.*;

/*
* 제목
* <미생물 격리> D?
* 링크
* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV597vbqAH0DFAVl
* 요약
* 
* 풀이
* 시뮬레이션
*/
public class swea_2382 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class Pos {
		int num, i, j;

		public Pos(int num, int i, int j) { // 군집 번호, 군집 위치
			this.num = num;
			this.i = i;
			this.j = j;
		}

		@Override
		public String toString() {
			return "Group" + num + "[" + i + "][" + j + "]";
		}
	}

	static int areaSize, time, groupCount;
	static int[][] area; // 군집 위치 표시하는 배열
	static int[] size; // [i]번 군집의 크기
	static int[] direction; // [i]번 군집의 이동 방향 (상=1, 하=2, 좌=3, 우=4)

	static int[] di = { 0, -1, 1, 0, 0 }; // x 상 하 좌 우
	static int[] dj = { 0, 0, 0, -1, 1 };

	static Queue<Pos> q; // 군집의 위치 저장

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; ++tc) {
			q = new ArrayDeque<>();
			// 입력
			st = new StringTokenizer(br.readLine().trim());
			areaSize = Integer.parseInt(st.nextToken()); // 구역 크기
			time = Integer.parseInt(st.nextToken()); // 격리 시간
			groupCount = Integer.parseInt(st.nextToken()); // 미생물 군집의 개수 K (1~K)
			area = new int[areaSize][areaSize];
			size = new int[groupCount + 1];
			direction = new int[groupCount + 1];
			for (int i = 1; i <= groupCount; ++i) {
				st = new StringTokenizer(br.readLine().trim());
				int row = Integer.parseInt(st.nextToken());
				int col = Integer.parseInt(st.nextToken());
				int siz = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				area[row][col] = i;
				size[i] = siz;
				direction[i] = dir;
				q.offer(new Pos(i, row, col)); // 군집 위치, 군집 번호
			}

			// 풀이
			for (int i = 1; i <= time; ++i)
				moveGroups();
			int answer = getNumOfGroups();
			// 출력
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	// 미생물 이동
	private static void moveGroups() {
		int[][] nextArea = new int[areaSize][areaSize];
		int[][] nextAreaMaxGroupSize = new int[areaSize][areaSize];
		int cnt = q.size();
		for (int i = 0; i < cnt; ++i) {
			Pos cur = q.poll();
			int curNum = cur.num;
//			System.out.println("current : "+curNum);
			int curDir = direction[curNum];
			int curI = cur.i;
			int curJ = cur.j;
			int ni = curI + di[curDir];
			int nj = curJ + dj[curDir];

			if (!isIn(ni, nj)) { // curNum번 군집의 다음 이동 칸이 빨간칸일 때
				size[curNum] = size[curNum] / 2;
				if (size[curNum] == 0) {
					direction[curNum] = -1;
					continue;
				}
				direction[curNum] = reverseDir(curDir);
				nextArea[ni][nj] = curNum;
			} else { // curNum번 군집의 다음 이동 칸이 일반칸일 때
				// 사이즈 동일
				// 방향 동일
				// 해당 위치에 다른 군집이 올 예정인 지 확인 필요
				// 없으면 그냥 그 위치에 군집 번호 표기
				if (nextArea[ni][nj] == 0) {
					nextArea[ni][nj] = curNum;
					nextAreaMaxGroupSize[ni][nj] = size[curNum];
				} else {// 있으면 비교해서 방향이랑 최대크기 갱신 필요
					int residentNum = nextArea[ni][nj];
					if (nextAreaMaxGroupSize[ni][nj] < size[curNum]) {
						nextArea[ni][nj] = curNum;
						nextAreaMaxGroupSize[ni][nj] = size[curNum];
						size[curNum] += size[residentNum];
						size[residentNum] = 0;
						direction[residentNum] = -1;
					} else {
						size[residentNum] += size[curNum];
						size[curNum] = 0;
						direction[curNum] = -1;
					}
				}
			}
		}
		for (int ii = 0; ii < areaSize; ++ii) {
			for (int jj = 0; jj < areaSize; ++jj) {
				if (nextArea[ii][jj] != 0)
					q.offer(new Pos(nextArea[ii][jj], ii, jj));
			}
		}
		area = nextArea;
	}

	private static int reverseDir(int curDir) {
		if (curDir == 1) {
			return 2;
		} else if (curDir == 2) {
			return 1;
		} else if (curDir == 3) {
			return 4;
		} else { // curDir == 4
			return 3;
		}
	}

	// 빨간 셀 안쪽 인지 확인
	private static boolean isIn(int ni, int nj) {
		if (1 <= ni && ni <= areaSize - 2 && 1 <= nj && nj <= areaSize - 2)
			return true;
		return false;
	}

	// 미생물 수 계산
	private static int getNumOfGroups() {
		int num = 0;
		for(int n : size) 
			num+=n;
		return num;
	}
}