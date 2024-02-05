package day7.problems;

import java.io.*;
import java.util.*;
/*
* 제목
* <사칙연산 유효성 검사>
* 링크
* https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV141176AIwCFAYD
* 요약
* 트리로 주어진 수식이 유효한 지 검사
*/
public class SW1233_사칙연산_유효성_검사_이강산_트리후위순회이용 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	static int N;			// 노드 개수
	static String[] arr;	// Tree
	static Stack<String> stack = new Stack<>();	// 후위순회 수식 위한 스택
	static int numCnt = 0;
	static boolean ans;
	public static void main(String[] args) throws IOException {
		for(int i=1; i<=10; ++i) {	// TC 10개 고정
			// 입력
			N = Integer.parseInt(br.readLine());
			arr = new String[N+1];
			for(int j=1; j<=N; j++) {
				st = new StringTokenizer(br.readLine());
				st.nextToken();
				arr[j] = st.nextToken();
			}
			// 풀이
			System.out.print("#"+i+" ");
			ans = true;
			postOrder(1);
			System.out.println();
			// 출력 
			if(ans) sb.append("#"+i+" "+1);
			else sb.append("#"+i+" "+0);
			sb.append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
	}
	// 브랜치에는 연산자, 리프에는 숫자만 온다는 것을 모른다고 가정하고 풀이
	private static void postOrder(int i) {	// 후위순회
		if(1 <= i && i <= N) {
			if(ans==false) return;
			if(i*2>N) {	// 리프면 재귀 종료
				try {	// 현재 노드가 숫자인 지 연산자인 지 파악
					Integer.parseInt(arr[i]);
					stack.push(arr[i]);	// 숫자면 스택에 삽입
					return;
				} catch (NumberFormatException e) {	// 숫자가 아닌 경우
					if(stack.size()<2) { // 스택에서 pop 할 수 있는 숫자가 2개 미만인데 연산자가 들어오면 틀린 수식 
						ans = false;
						return;
					} else { // 현재 연산자로 숫자 2개 연산 후 스택에 다시 삽입			
						stack.pop();
						stack.pop();
						stack.push("1"); // 유효성 검사이므로 실제 계산된 값을 넣진 않음 (적당한 숫자 삽입)
						return;
					}
				}
			}
			postOrder(i*2);
			postOrder(i*2+1);
			if(ans==false) return;
			try {
				Integer.parseInt(arr[i]);
				stack.push(arr[i]);
				return;
			} catch (NumberFormatException e) {	
				if(stack.size()<2) {
					ans = false;
					return;
				} else { 		
					stack.pop();
					stack.pop();
					stack.push("1");
					return;
				}
			}
		}
	}	
}

