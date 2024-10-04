package BOJ;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
* 제목
<수 찾기> S4
* 링크
https://www.acmicpc.net/problem/1920
* 요약
이분탐색
* 풀이
이분탐색
* 메모리
49644 KB
* 시간
612 ms
*/

public class boj_1920 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static int[] arr;
    static int M;
    static int[] arr2;

    public static void main(String[] args) throws IOException {
        // 입력
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        arr2 = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        // 풀이
        Arrays.sort(arr);
        for (int i = 0; i < M; i++) {
            boolean result = binarySearch(arr2[i]);
            if (result) {
                sb.append("1").append(System.lineSeparator());
            } else {
                sb.append("0").append(System.lineSeparator());
            }
        }

        // 출력
        bw.write(sb.toString());
        bw.flush();
    }

    private static boolean binarySearch(int n) {
        int start = 0;
        int end = arr.length - 1;
        int mid = (start + end) / 2;

        while (start <= end) {
            if (arr[mid] == n) {
                return true;
            } else if (arr[mid] > n) {
                end = mid - 1;
                mid = (start + end) / 2;
            } else { // arr[mid] < n
                start = mid + 1;
                mid = (start + end) / 2;
            }
        }
        return false;
    }
}
