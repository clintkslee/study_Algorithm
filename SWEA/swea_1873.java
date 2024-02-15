package SWEA;

import java.awt.Point;
import java.io.*;
import java.util.*;
/*
 * 제목
 * <상호의 배틀필드> D3
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5LyE7KD2ADFAXc
 * 요약
 * 주어진 입력 순으로 조건에 맞게 배열 수정
 * 풀이
 * 열심히 구현 ^^
 */
public class swea_1873 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int T;		// 테스트케이스 개수 
	static int H, W;	// 게임판크기 H*W
	static char[][] map; // 게임판
	static int N;		// 사용자 입력 개수
	static char[] userInput;	// 사용자입력
	
	static Point location;	// 탱크 위치
	static int direction;
	final static int UP=0, DOWN=1, LEFT=2, RIGHT=3;
	
    public static void main(String[] args) throws IOException {
		T = Integer.parseInt(br.readLine());
		for(int tc=1; tc<=T; tc++) {
			// 입력
			st = new StringTokenizer(br.readLine());	
			H = Integer.parseInt(st.nextToken());	// 맵 높이
			W = Integer.parseInt(st.nextToken());	// 맵 너비
			map = new char[H][W];
			boolean isFound = false;
			for(int i=0; i<H; i++) {
				map[i] = br.readLine().toCharArray();	// 맵
				if(!isFound) {
					for(int j=0; j<W; j++) {			// 탱크 시작 위치 찾기
						if(map[i][j]=='^' || map[i][j]=='v' || map[i][j]=='<' || map[i][j]=='>') {
							isFound=true;
							switch(map[i][j]) {
								case '^': direction=UP; break;
								case 'v': direction=DOWN; break;
								case '<': direction=LEFT; break;
								case '>': direction=RIGHT; break;
							}
							location = new Point(i, j);
						}
					}
				}
			}
			N = Integer.parseInt(br.readLine());		// 사용자 입력 개수
			userInput = br.readLine().toCharArray();	// 사용자 입력
//			// 입력 검증
//			System.out.println("TC "+tc);
//			System.out.println("H W : "+H+" "+W);
//			System.out.println("starting point : "+location.x+" "+location.y);
//			System.out.println("starting direction : "+direction);
//			for(int i=0; i<H; i++)
//				System.out.println(Arrays.toString(map[i]));
//			System.out.println("N : "+N);
//			System.out.println(Arrays.toString(userInput));
			// 풀이
			solution();
			// 출력
			sb.append("#"+tc+" ");
			for(int i=0; i<H; i++) 
				sb.append(String.valueOf(map[i])+"\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}

	private static void solution() {
		for(int i=0; i<userInput.length; i++) {
			switch(userInput[i]) {
				case 'U':
					move(UP);
					break;
				case 'D':
					move(DOWN);
					break;
				case 'L':
					move(LEFT);
					break;
				case 'R':
					move(RIGHT);
					break;
				case 'S':
					shoot();
					break;
			}
		}		
	}

	private static void shoot() {
		int si, sj;	// 포탄 시작 위치
		si=location.x;
		sj=location.y;
		switch(direction) {
			case UP:
				while(--si>=0) {
					if(map[si][sj]=='*' || map[si][sj]=='#') {	// 벽돌, 강철 벽 만나면
						if(map[si][sj]=='*') map[si][sj]='.';
						break;
					}
				}
				break;
			case DOWN:
				while(++si<H) {
					if(map[si][sj]=='*' || map[si][sj]=='#') {	// 벽돌, 강철 벽 만나면
						if(map[si][sj]=='*') map[si][sj]='.';
						break;
					}
				}
				break;
			case LEFT:
				while(--sj>=0) {
					if(map[si][sj]=='*' || map[si][sj]=='#') {	// 벽돌, 강철 벽 만나면
						if(map[si][sj]=='*') map[si][sj]='.';
						break;
					}
				}
				break;
			case RIGHT:
				while(++sj<W) {
					if(map[si][sj]=='*' || map[si][sj]=='#') {	// 벽돌, 강철 벽 만나면
						if(map[si][sj]=='*') map[si][sj]='.';
						break;
					}
				}
				break;
		}
	}

	private static void move(int move) {
		direction = move;
		int ni, nj;
		char temp;
		switch(move) {
			case UP:
				map[location.x][location.y] = '^';	// 맵 그림 변경
				// 방향으로 이동 가능하면 스왑
				ni = location.x-1;
				nj = location.y;
				if(0 <= ni && ni < H && 0 <= nj && nj < W) {	// 맵을 벗어나지 않으면서
					if(map[ni][nj]=='.' ) {	// 평지면 스왑
						temp = map[location.x][location.y];
						map[location.x][location.y] = map[ni][nj];
						map[ni][nj] = temp;
						location.x=ni;
						location.y=nj;
					}
				}
				break;
			case DOWN:
				map[location.x][location.y] = 'v';	// 맵 그림 변경
				ni = location.x+1;
				nj = location.y;
				if(0 <= ni && ni < H && 0 <= nj && nj < W) {	// 맵을 벗어나지 않으면서
					if(map[ni][nj]=='.' ) {	// 평지면 스왑
						temp = map[location.x][location.y];
						map[location.x][location.y] = map[ni][nj];
						map[ni][nj] = temp;
						location.x=ni;
						location.y=nj;
					}
				}
				break;
			case LEFT:
				map[location.x][location.y] = '<';	// 맵 그림 변경
				ni = location.x;
				nj = location.y-1;
				if(0 <= ni && ni < H && 0 <= nj && nj < W) {	// 맵을 벗어나지 않으면서
					if(map[ni][nj]=='.' ) {	// 평지면 스왑
						temp = map[location.x][location.y];
						map[location.x][location.y] = map[ni][nj];
						map[ni][nj] = temp;
						location.x=ni;
						location.y=nj;
					}
				}
				break;
			case RIGHT:
				map[location.x][location.y] = '>';	// 맵 그림 변경
				ni = location.x;
				nj = location.y+1;
				if(0 <= ni && ni < H && 0 <= nj && nj < W) {	// 맵을 벗어나지 않으면서
					if(map[ni][nj]=='.' ) {	// 평지면 스왑
						temp = map[location.x][location.y];
						map[location.x][location.y] = map[ni][nj];
						map[ni][nj] = temp;
						location.x=ni;
						location.y=nj;
					}
				}
				break;
		}
	}
	
	
}
