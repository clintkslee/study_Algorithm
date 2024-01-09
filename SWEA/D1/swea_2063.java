package SWEA.D1;

import java.util.*;

public class swea_2063 {
    public static void main(String[] args) {
        Vector<Integer> v = new Vector<>();
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        for(int i=0; i<N; i++) {
            v.add(scanner.nextInt());
        }
        Collections.sort(v);
        System.out.println(v.get(N/2));
        scanner.close();
    }
}
