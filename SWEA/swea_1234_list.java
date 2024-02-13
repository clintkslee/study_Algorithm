package SWEA;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class swea_1234_list {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static List<Character> arr;
    public static void main(String[] args) throws IOException {
        // 입력
        for(int i=1; i<=10; ++i) {                  // TC = 10
            st = new StringTokenizer(br.readLine());
            st.nextToken();                         // 길이
            arr = st.nextToken().chars()            // 스트링 -> ArrayList<Character>
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toList());
        // 풀이
            solution();
            sb.append("#"+i+" ");
            for(char c : arr)    sb.append(c);
            sb.append("\n");
        }
        // 출력
        bw.write(sb.toString());
        bw.flush();
    }

    private static void solution() {
        int idx1, idx2;                   
        for(int j=0; j<=arr.size()-2; ++j) {        // 배열 탐색 
            if(arr.get(j)==arr.get(j+1)) {          // 동일 문자 2연속 시
                idx1 = j;                   
                idx2 = j+1;
                while(idx1>=0 && idx2 < arr.size() && arr.get(idx1)==arr.get(idx2)) {  // 번호쌍 삭제
                    arr.remove(idx1);
                    arr.remove(idx1);
                    idx2=idx1;
                    idx1--;
                }
                if(idx1==-1)    j = idx1;                            // 다음 탐색 시작 위치 조정
                else j = idx1-1;
            }
        }
    }
    
}