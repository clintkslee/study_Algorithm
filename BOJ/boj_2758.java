package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
<로또> G4
* 링크
https://www.acmicpc.net/problem/2758
* 요약
1 ≤ n ≤ 10
1 ≤ m ≤ 2,000
n ≤ m
1~m 에서 n개 숫자 선택
숫자 선택 시 k+1번째 숫자는 k번째 숫자의 2배 이상의 값으로만 선택 가능
살 수 있는 모든 로또 조합의 수 구하기
* 풀이
dp
arr[i][j] : i번쨰 숫자 선택 시 j가 오는 경우의 수
* 메모리
14804 KB
* 시간
148 ms
*/

public class boj_2758 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int T; // 테스트케이스 수
    static int n, m; // 1~m 중 n개의 숫자 선택
    static long cnt; // 조합의 수
    static long[][] arr; // dp array

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        dp();

        for (int i = 0; i < T; i++) {
            // 입력
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            // 풀이
            cnt = getRes(n, m);
            // 출력
            sb.append(cnt).append('\n');
        }
        bw.write(sb.toString());
        bw.flush();
    }

    private static void dp() {
        // arr[i][j] : i번째 숫자 선택 시 j를 고르는 경우의 수
        arr = new long[11][2001];

        // 0행 제외
        for (int k = 0; k <= 10; k++) {
            arr[k][0] = 0;
        }

        // 0열 제외
        for (int k = 0; k <= 2000; k++) {
            arr[0][k] = 0;
        }

        // 첫번쨰 숫자 선택 == 1가지 경우의 수
        for(int k=1; k <=2000; k++) {
            arr[1][k] = 1;
        }

        // i번째 숫자에 j가 오는 경우의 수 계산
        for (int i = 2; i <= 10; i++) {
            for (int j = 1; j <= 2000; j++) {
                arr[i][j] = 0;
                int half = j/2;
                for (int k = 1; k <= half; k++) {
                    arr[i][j] += arr[i - 1][k];
                }
            }
        }

//        for(int i=1; i<=n; i++) {
//            for(int j=1; j<=m; j++) {
//                System.out.print(arr[i][j] + " ");
//            }
//            System.out.println();
//        }
    }

    private static long getRes(int n, int m) {
        long sum = 0;
        for(int i=1; i<=m; i++) {
            sum += arr[n][i];
        }
        return sum;
    }
}
