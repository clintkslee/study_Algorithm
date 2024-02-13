package SWEA;

import java.util.Scanner;
import java.util.Vector;

public class swea_2072 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        Vector<Integer> v = new Vector<>();
        for(int i=0; i<T; i++) {
            for(int j=0; j<10; j++) {
                v.add(scanner.nextInt());
            }
            int sum = 0;
            for(int k=0; k<10; k++) {
                if(v.get(k)%2==1) {
                    sum += v.get(k);
                }
            }
            System.out.println("#"+(i+1)+" "+sum);
            v.clear();
        }        
        scanner.close();
    }
}
