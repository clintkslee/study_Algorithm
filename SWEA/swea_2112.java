package SWEA;

import java.io.*;
import java.util.*;

/*
 * 제목
 * <보호 필름> D?
 * 링크
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V1SYKAaUDFAWu
 * 요약
 * 
 * 풀이
 * dfs
 */
public class swea_2112 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int rowSize, colSize, passSize; // 행, 열, 합격기준
    static int[][] film; // A : 0, B : 1
    static int minInjectionCount;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; ++tc) {
            // 입력
            st = new StringTokenizer(br.readLine());
            rowSize = Integer.parseInt(st.nextToken());
            colSize = Integer.parseInt(st.nextToken());
            passSize = Integer.parseInt(st.nextToken());

            film = new int[rowSize][colSize];
            for (int i = 0; i < rowSize; ++i) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < colSize; ++j) {
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 풀이
            minInjectionCount = Integer.MAX_VALUE;
            testingFilm(0, 0);

            // 출력
            sb.append("#").append(tc).append(" ").append(minInjectionCount).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }

    private static boolean check() {
        for (int j = 0; j < colSize; ++j) {
            boolean pass = false;
            int sameColorCount = 1;
            for (int i = 1; i < rowSize; ++i) { // 1부터 시작해서 이전 행과 비교
                // 현재 행과 이전 행을 비교해서 색상 동일하면 sameColorCount++;
                // 현재 행과 이전 행의 색상 다르면 sameColorCount=1;
                // sameColorCount가 passSize와 같아지면 다음 열 확인
                if (film[i][j] == film[i - 1][j]) {
                    ++sameColorCount;
                } else {
                    sameColorCount = 1;
                }
                if (sameColorCount >= passSize) {
                    pass = true;
                    break;
                }
            }
            if (!pass) { // 현재 열에서 통과 못했으면 다음 열 볼 필요 x
                return false;
            }
        }
        return true;
    }

    private static void testingFilm(int currentRowIndex, int currentInjectionCount) {
        // 필름 테스트
        // 통과할 수 있는 필름이면 minInjection과 비교해서 더 적으면 갱신 후 return
        if (check()) { // 통과 한 경우
            minInjectionCount = Math.min(minInjectionCount, currentInjectionCount);
            return;
        }

        // 현재 주입한 횟수가 기존 최소 주입 횟수보다 많으면 더 주입해볼 필요 없음
        if (minInjectionCount < currentInjectionCount) {
            return;
        }

        // 필름 끝 도달하면 더 진행 불가하므로 return
        if (currentRowIndex == rowSize) {
            return;
        }

        // 아직 주입 더 해야 하는 상태
        // 1) 현재 행 그대로 다음 행 진행
        testingFilm(currentRowIndex + 1, currentInjectionCount);

        // 현재 행 백업
        int[] temp = new int[colSize];
        for (int i = 0; i < colSize; ++i)
            temp[i] = film[currentRowIndex][i];

        // 2) 현재 행에 A=0 주입 후 다음 행 진행
        for (int i = 0; i < colSize; ++i)
            film[currentRowIndex][i] = 0;
        testingFilm(currentRowIndex + 1, currentInjectionCount + 1);

        // 3) 현재 행에 B=0 주입 후 다음 행 진행
        for (int i = 0; i < colSize; ++i)
            film[currentRowIndex][i] = 1;
        testingFilm(currentRowIndex + 1, currentInjectionCount + 1);

        // 현재 행 초기화
        for (int i = 0; i < colSize; ++i)
            film[currentRowIndex][i] = temp[i];
    }
}
