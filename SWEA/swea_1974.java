package SWEA;

import java.util.*;

public class swea_1974 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        int[][] sudoku = new int[9][9];
        for(int i=0; i<T; i++) {
            //스도쿠 입력
            for(int j=0; j<9; j++) {
                for(int k=0; k<9; k++) {
                    sudoku[j][k] = scanner.nextInt();
                }
            }

            // 가로 검증
            if(isValidHorizontal(sudoku)) {
                ;
            } else {
                System.out.println("#"+(i+1)+" "+0);
                continue;
            }
            
            // 세로 검증
            if(isValidVertical(sudoku)) {
                ;
            } else {
                System.out.println("#"+(i+1)+" "+0);
                continue;
            }
            
            // 9칸 검증
            if(isValidSquare(sudoku)) {
                ;
            } else {
                System.out.println("#"+(i+1)+" "+0);
                continue;                
            }

            // 검증 성공
            System.out.println("#"+(i+1)+" "+1);
        }
        scanner.close();
    }
    
    static boolean isValidHorizontal(int[][] arr) {
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                set.add(arr[i][j]);
            }
            if(set.size()!=9) {              
                return false; // 중복 숫자 존재
            }
            set.clear();
        }
        return true; // 검증 성공
    }
    
    static boolean isValidVertical(int[][] arr) {
        HashSet<Integer> set = new HashSet<>();        
        for(int i=0; i<9; i++) {
            for(int j=0; j<9; j++) {
                set.add(arr[j][i]);
            }
            if(set.size()!=9) {
                return false; // 중복 숫자 존재
            }
            set.clear();
        }
        return true; // 검증 성공
    }

    static boolean isValidSquare(int[][] arr) {
        HashSet<Integer> set = new HashSet<>();        
        for(int i=0; i<9; i+=3) {
            for(int j=0; j<9; j+=3) {
                set.add(arr[0+i][0+j]);
                set.add(arr[0+i][1+j]);                
                set.add(arr[0+i][2+j]);
                set.add(arr[1+i][0+j]);
                set.add(arr[1+i][1+j]);
                set.add(arr[1+i][2+j]);
                set.add(arr[2+i][0+j]);
                set.add(arr[2+i][1+j]);
                set.add(arr[2+i][2+j]);
                if(set.size()!=9) {
                    return false; // 중복 숫자 존재
                }                
                set.clear();
            }
        }       
        return true; // 검증 성공
    }
}
