week3 리뷰하고 폴더로 이동

import java.io.*;
import java.util.*;
/*
 * 제목
 * <벽 부수고 이동하기>
 * 링크
 * https://www.acmicpc.net/problem/2206
 * https://iseunghan.tistory.com/316
 * 요약
 * N * M 행렬에서 (1, 1) -> (N, M) 최단경로 구하는데, 벽 만나면 한 번 부수기 가능
 * 풀이
 * BFS
 */
public class boj_2206 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int N, M;
    static int[][] map;
    static boolean[][] isVisit;
    static Queue<Coor> q = new ArrayDeque<>();

    // static int[] dx = {-1, 1, 0, 0};    // 상, 하, 좌, 우
    // static int[] dy = {0, 0, -1, 1};    
    static int[] dx = {0, 1, 0, -1};    // 우, 하, 좌, 상
    static int[] dy = {1, 0, -1, 0};
    static int shortestPath = Integer.MAX_VALUE;
    
    public static void main(String[] args) throws IOException {
		// 입력
        st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N+1][M+1];    // [0] 제외
        isVisit = new boolean[N+1][M+1];    // [0] 제외
        for(int i=1; i<=N; i++) {
            String str = br.readLine();
            for(int j=1; j<=M; j++) {
                 map[i][j] = Integer.parseInt(str.charAt(j-1)+"");
                 if(map[i][j]==1) isVisit[i][j]=true;  // 1 이면 벽
            }
        }
        // System.out.println("map===========");
        // for(int i=1; i<=N; i++) {
        //     for(int j=1; j<=M; j++) {
        //         System.out.print(map[i][j]+"\t");
        //     }
        //     System.out.println();
        // }
        // System.out.println("isvisit===========");
        // for(int i=1; i<=N; i++) {
        //     for(int j=1; j<=M; j++) {
        //         System.out.print(isVisit[i][j]+"\t");
        //     }
        //     System.out.println();
        // }
        // System.out.println("===========");
		// 풀이
        q.offer(new Coor(1, 1, false, 1));   // bfs 루트 : (x, y), 이전에 벽 파괴 여부, 현재 위치까지 지나온 방 개수 
		bfs();
		// 출력
        if(shortestPath==Integer.MAX_VALUE) shortestPath=-1;
		bw.write(shortestPath+"\n");
		bw.flush();
	}

    private static void bfs() {
        while(!q.isEmpty()) {
            Coor p = q.poll();
            isVisit[p.getX()][p.getY()]=true;
            if((int)p.getX()==N && (int)p.getY()==M) { // 현 위치가 (N,M) 이면
                shortestPath = Math.min(shortestPath, p.getCnt()); // 최단 경로인지 비교
            }
            else { // 종료지점이 아니면
                for(int i=0; i<4; i++) {    // 현 위치 (p.x, p.y)에서 4방향 탐색
                    int nx = (int)p.getX()+dx[i];
                    int ny = (int)p.getY()+dy[i];
                    if(nx <= 0 || nx > N || ny <= 0 || ny > M) continue;    // 인덱스 범위 밖이면 다음 방향으로
                    if(map[nx][ny]==0) { // 다음 칸이 방일 때 map[nx][ny]==0
                        if(!isVisit[nx][ny]) { // 방문한 적 없는 방이면
                            isVisit[nx][ny]=true;
                            q.offer(new Coor(nx, ny, p.getIsWallCrushedBefore(), p.getCnt() + 1));
                        }  
                        else {  // 방문한 적 있는 방이면
                            continue;
                        }
                    } 
                    else if(map[nx][ny]==1) { // 다음 칸이 벽일 때 map[nx][ny]==1
                        if(!p.getIsWallCrushedBefore()) {  // 현 위치 까지 오면서 벽 부순 적 없으면
                            q.offer(new Coor(nx, ny, true, p.getCnt() + 1));
                        } 
                        else {    // 벽 부순 적 있으면
                            continue;
                        }
                    }
                } 
            }
        }
	}
}

class Coor {
    private int x;
    private int y;
    private boolean isWallCrushedBefore;
    private int cnt;

    public Coor(int x, int y, boolean isWallCrushedBefore, int cnt) {
        this.x = x;
        this.y = y;
        this.isWallCrushedBefore = isWallCrushedBefore;
        this.cnt = cnt;
    }

    public int getX() { return x; };
    public int getY() { return y; };
    public boolean getIsWallCrushedBefore() { return isWallCrushedBefore; };
    public int getCnt() { return cnt; };
}
