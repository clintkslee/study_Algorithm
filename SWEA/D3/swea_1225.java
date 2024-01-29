package SWEA.D3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class swea_1225 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        LinkedList<Integer> l = new LinkedList<>();         // 8개 데이터 입력 받을 리스트
        int TC = 10;                                        // TC 10개 고정
        for(int i=0; i<TC; i++) {
            // 입력
            int c = Integer.parseInt(br.readLine());        // TC 번호
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<8; j++) {
                l.add(Integer.parseInt(st.nextToken()));    // 매 TC마다 8개 데이터 입력
            }

            // 계산
            int cnt=0;                                      // sub[] 의 index 용
            int[] sub = {1, 2, 3, 4, 5};
            while(true) {
                if(l.getFirst()-sub[cnt%5]<=0) {            // 0이하가 되는 경우 -> 암호
                    l.removeFirst();
                    l.add(0);
                    break;
                }
                l.add(l.getFirst()-sub[cnt%5]);             // 첫번째 원소에서 sub[cnt%5] 만큼 뺀 후 add()
                l.removeFirst();                            // 첫번째 원소 제거
                cnt++;
            }

            // 출력
            bw.write("#"+c+" ");
            bw.write(l.toString().replaceAll("\\[", "")
                                .replaceAll("\\]", "")
                                .replaceAll(",", "")+"\n");
            l.clear();
        }
        bw.close();
    }
}
