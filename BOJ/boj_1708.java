package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
<볼록 껍질> P5
* 링크
https://www.acmicpc.net/problem/1708
* 요약
주어진 좌표들로 컨벡스 홀을 만들 때, 컨벡스 홀을 이루는 좌표의 개수
* 풀이
컨벡스 헐 찾기
* 메모리
49048 KB
* 시간
1064 ms
*/

public class boj_1708 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n; // 좌표 개수

    static ArrayList<Pos> list; // 시작점 찾기 위한 리스트
    static int sx, sy;
    static PriorityQueue<Pos> pq; // 극각 기준으로 정렬된 좌표들

    static Stack<Pos> stack; // 컨벡스 헐을 이루는 좌표들
    static int answer;

    public static void main(String[] args) throws IOException {
        // 입력
        n = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            list.add(new Pos(x, y));
        }
        // 풀이
        Collections.sort(list, (o1, o2) -> {
            if (o1.y == o2.y) {
                return Integer.compare(o1.x, o2.x);
            }
            return Integer.compare(o1.y, o2.y);
        });

        // 시작점 찾기
        sx = list.get(0).x;
        sy = list.get(0).y;
        list.remove(0);

        // 나머지 좌표들 극각 순으로 정렬 (극각 작은 순, 같다면 기준점과의 거리 가까운 순)
        pq = new PriorityQueue<>((o1, o2) -> {
            double angle1 = Math.atan2(o1.y - sy, o1.x - sx);
            double angle2 = Math.atan2(o2.y - sy, o2.x - sx);
            if (angle1 == angle2) {
                double dist1 = Math.pow(o1.y - sy, 2) + Math.pow(o1.x - sx, 2);
                double dist2 = Math.pow(o2.y - sy, 2) + Math.pow(o2.x - sx, 2);
                return Double.compare(dist1, dist2);
            }
            return Double.compare(angle1, angle2);
        });

        for (Pos pos : list) {
            pq.offer(pos);
        }

        // 스택 초기화 : 기준점과 정렬된 첫 두 점을 스택에 추가
        stack = new Stack<>();
        stack.add(new Pos(sx, sy));
        stack.add(pq.poll());
        stack.add(pq.poll());

        while (!pq.isEmpty()) {
            Pos current = pq.poll();

            // 벡터 외적으로 회전 계산
            while (stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.peek(), current) != -1) {
                stack.pop();
            }
            stack.push(current);
        }

        answer = stack.size();
        while(stack.size() > 3) {
            stack.pop();
        }

        Pos p3 = stack.pop();
        Pos p2 = stack.pop();
        Pos p1 = stack.pop();

        // 처음 세 점이 일직선 상에 있는 경우 중간 점 제외
        if(ccw(p1, p2, p3) == 0)
            answer--;

        // 출력
        bw.write(answer+"");
        bw.flush();

    }

    // Counter Clockwise
    private static int ccw(Pos p1, Pos p2, Pos p3) {
        int val = (p2.y - p1.y) * (p3.x - p2.x) - (p2.x - p1.x) * (p3.y - p2.y);
        if (val == 0) return 0;  // 일직선
        return (val > 0) ? 1 : -1;  // 오른쪽 회전(1), 왼쪽 회전(-1)
    }
}
