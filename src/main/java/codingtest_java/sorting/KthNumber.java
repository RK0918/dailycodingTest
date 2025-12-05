package codingtest_java.sorting;

//import java.io.BufferedReader;
//import java.io.InputStreamReader;
import java.util.Arrays;
//import java.util.Scanner;
//import java.util.StringTokenizer;
// 향상된 for문 사용
public class KthNumber {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int n = 0;
        for (int[] command : commands) {
            int i = command[0]-1;
            int j = command[1];
            int k = command[2]-1;
            
            int[] temp = Arrays.copyOfRange(array, i, j);
            Arrays.sort(temp);
            answer[n] = temp[k];
            n++;
        }
        
        return answer;
    }
    public static void main(String[] args) {

        int[] array = {1, 5, 2, 6, 3, 7, 4};

        int[][] commands = {
                {2, 5, 3},   // array[1]~array[4] → 정렬 후 3번째
                {4, 4, 1},   // array[3]~array[3] → 정렬 후 1번째
                {1, 7, 3}    // array[0]~array[6] → 정렬 후 3번째
        };

        KthNumber s= new KthNumber();
        System.out.println(Arrays.toString(s.solution(array, commands)));
    }
}

/* 다른 ver
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for(int i=0; i<commands.length; i++){
            int[] temp = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);
            Arrays.sort(temp);
            answer[i] = temp[commands[i][2]-1];
        }

        return answer;
    }
}
*/

/* 입출력 Scanner 버전
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();               // 배열 길이
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextInt();
        }

        int m = sc.nextInt();               // commands 개수
        int[][] commands = new int[m][3];
        for (int i = 0; i < m; i++) {
            commands[i][0] = sc.nextInt();
            commands[i][1] = sc.nextInt();
            commands[i][2] = sc.nextInt();
        }

        Solution s = new Solution();
        int[] result = s.solution(array, commands);

        for (int r : result) {
            System.out.println(r);
        }
    }
}
*/

/* 입출력 BufferedReader / BufferedWriter 버전

import java.io.*;
        import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());  // 배열 길이
        int[] array = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());  // commands 개수
        int[][] commands = new int[m][3];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            commands[i][0] = Integer.parseInt(st.nextToken());
            commands[i][1] = Integer.parseInt(st.nextToken());
            commands[i][2] = Integer.parseInt(st.nextToken());
        }

        Solution s = new Solution();
        int[] result = s.solution(array, commands);

        StringBuilder sb = new StringBuilder();
        for (int r : result) {
            sb.append(r).append("\n");
        }

        System.out.print(sb);
    }
}
*/
