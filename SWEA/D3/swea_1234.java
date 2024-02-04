package SWEA.D3;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class swea_1234 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static List<Character> arr;
    public static void main(String[] args) throws IOException {
        // 입력
        for(int i=0; i<10; ++i) {
            st = new StringTokenizer(br.readLine());
            String temp = st.nextToken();           // 길이
            arr = st.nextToken().chars()
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toList());
        System.out.println(arr);
        // 풀이
        solution();
        // 출력
        //bw.write(sb.toString());
        //bw.flush();
    }

    private static void solution() {
        int idx1, idx2;
        for(int i=0; i<10; ++i) {                       // TC = 10
            for(int j=0; j<=arr.size()-2; ++j) {     // 배열 탐색 
                if(arr.get(j)==arr.get(j+1)) {            // 동일 문자 2연속 시
                    idx1 = j;                   
                    idx2 = j+1;
                    while(idx1>=0 && idx2 < arr.size() && arr.get(idx1)==arr.get(idx2)) {  // 번호쌍 삭제
                        arr.get(idx1--) = 'x';
                        arr[i][idx2++] = 'x';
                    }
                    j = idx2-2;                         // 다음 탐색 시작 위치 조정
                }
            }
            sb.append("#"+(i+1)+" ");
            for(int j=0; j<arr[i].length; ++j)
                if(arr[i][j]!='x') sb.append(arr[i][j]);
            sb.append("\n");
        }
    }
    
}