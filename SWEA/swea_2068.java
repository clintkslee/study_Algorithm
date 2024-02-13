package SWEA;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Vector;

public class swea_2068 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt();
        Vector<Integer> v = new Vector<>();
        for(int i=0; i<T; i++) {
            for(int j=0; j<10; j++) {
                v.add(scanner.nextInt());
            }
            Collections.sort(v, Comparator.reverseOrder());
            System.out.println("#"+(i+1)+" "+v.get(0));
            v.clear();
        }        
        scanner.close();
    }
}
