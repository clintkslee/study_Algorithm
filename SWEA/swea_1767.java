package SWEA;

import java.io.*;
import java.util.*;
/*
 * 제목
 * <프로세서 연결하기> difficulty unknown
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV4suNtaXFEDFAUf
 * 요약
 * 최대한 많은 코어를 전원에 연결하기
 * 풀이
 * dfs, 사방에 전선 놓기 + 안놓는 경우까지 고려
 * 설계
 * 1. 현재 코어에 대해 전선 놓기, 놓지 않기 시도
 * 2. 전선 놓기
 * 		4방향에 시도
 * 			해당 방향 가능한 지 체크
 * 			불가능하다면 다음 방향 continue
 * 			가능하다면 전선 놓기(상태 set) 후 다음 코어 처리(dfs)
 * 			전선 지우기
 * 		전선 놓지 않기 시도
 * 			현 상태 그대로 다음 코어 처리(dfs)
 * 3. 기저조건
 * 		마지막 코어 도달 
 * 		최대 코어수 관련 가지치기
 * 		최쇠 전선 길이 관련 가지치기
 */
public class swea_1767 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static class Pos {
		int y;
		int x;
		public Pos(int y, int x) {
			this.y = y;
			this.x = x;
		}		
	}
	
	static int N; // 엑시노스 크기 N*N
	static int[][] map; // 엑시노스
	static ArrayList<Pos> cellPos; // 가장자리가 아닌 셀들의 위치 저장
	static int alreadyPowered = 0; // 가장자리에 위치한 셀들 개수
	
	static int[] dy = {-1, 0, 1, 0}; // 상 우 하 좌
	static int[] dx = {0, 1, 0, -1};
	
	static int maxCore; // 최대 코어 수(비 가장자리 코어) (가장자리 코어는 dfs 완료 후 더해준다)
	static int minWire; // 최소 전선 길이
    public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			// 입력
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cellPos = new ArrayList<>();
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j]==1) {
						if(i==0 || j==0 || i==N-1 || j==N-1) {
							alreadyPowered++; // 가장자리에 위치해서 이미 전원 들어온 셀 개수
						} else {
							cellPos.add(new Pos(i, j));
						}
					}
				}
			}	
			// 풀이
			alreadyPowered = 0;
			maxCore = Integer.MIN_VALUE;
			minWire = Integer.MAX_VALUE;
			dfs(0, 0, 0); // depth, 연결된 셀 개수, 전선길이
			maxCore += alreadyPowered; // 처음부터 전원에 연결됐던 코어들 더해주기
			// 출력
			sb.append("#"+tc+" "+minWire+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void dfs(int depth, int poweredCell, int wireLength) {
		// 가지치기
		if(cellPos.size() - depth + poweredCell < maxCore) 
			return;
		
		if(depth == cellPos.size()) {
			if(maxCore < poweredCell) { // 전원 더 많이 연결한 경우 발견
				maxCore = poweredCell;
				minWire = wireLength;
			}
			else if(maxCore==poweredCell) { // 여태까지 최대로 연결한 셀 개수와 동일하면 전선 짧은 경우 선택
				minWire = Math.min(minWire,  wireLength);
			}
			return;
		}
		
		Pos curCell = cellPos.get(depth);		
		int x = curCell.x;
		int y = curCell.y;
		// 현재 셀에서 d[i] 방향으로 전선 연결 가능한 지 파악
		// 가능하다면 전선 그리고 dfs, 불가능하다면 전선 그리지 않고 dfs
		for(int i=0; i<4; i++) { 
			if(isAvailable(y, x, i)) { // 전선 놓기 가능한 지 체크
				int len = setStatus(y, x, i, 2);
				dfs(depth+1, poweredCell+1, wireLength+len);
				setStatus(y, x, i, 0); // 전선 지우기
			}
		}
		// 전선 놓지 않기
		dfs(depth+1, poweredCell, wireLength);
	}
	
	
	// r, c 위치에서 d방향으로 전선놓기 가능한 지 체크
	private static boolean isAvailable(int r, int c, int d) {
		int nr = r; 
		int nc = c;
		while(true) {
			nr += dy[d];
			nc += dx[d];
			if(nr<0 || nr >=N || nc<0 || nc >=N) return true;
			if(map[nr][nc] > 0) return false;
		}
	}
	
	// r, c 위치에서 d방향으로 s(2 : 전선, 0 : 빈칸)로 상태 set
	private static int setStatus(int r, int c, int d, int s) {
		int nr = r; 
		int nc = c;
		int cnt = 0;
		while(true) {
			nr += dy[d];
			nc += dx[d];
			if(nr<0 || nr >=N || nc<0 || nc >=N) break;
			map[nr][nc] = s;
			cnt++; // 처리한 빈칸의 개수
		}
		return cnt;
	}
}