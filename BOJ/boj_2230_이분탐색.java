package BOJ;

import java.io.*;
import java.util.*;

/*
* 제목
<수 고르기> G5
* 링크
https://www.acmicpc.net/problem/2230
* 요약
N개의 정수에서 두 수를 골랐을 때, 차이가 M 이상이면서 제일 작은 경우
* 풀이
이분탐색 + lower bound
주어진 수열을 정렬한 후, N개의 숫자에 대해 N+M 값이 위치할 수 있는 인덱스를 찾으면, 그 인덱스의 숫자가 현재 숫자와 차이 M이상이면서 가장 작은 경우
수열 마지막에 무한대값을 넣어주면 더 편해진다
* 메모리
28360 KB
* 시간
356 ms
*/

public class boj_2230_이분탐색 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M;
    static int[] arr;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        // 입력
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N+1];
        arr[N] = Integer.MAX_VALUE;
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        // 풀이
        for(int i=0; i<N; i++) {
            int currentNum = arr[i];
            int currentIndex = binarySearchToGetLowerBoundIndex(currentNum+M);
            min = Math.min(min, arr[currentIndex] - currentNum);
        }

    	// 출력
    	sb.append(min);
        bw.write(sb.toString());
    	bw.flush();
    	
    }

    private static int binarySearchToGetLowerBoundIndex(int num) {
        int start = 0;
        int end = N;
        int mid = (start + end) / 2;

        while(start < end) {
            if(arr[mid] < num) {
                start = mid + 1;
                mid = (start + end) / 2;
            } else {
                end = mid;
                mid = (start + end) / 2;
            }
        }

        return mid;
    }
}
