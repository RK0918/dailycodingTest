package codingtest_java.bruteforce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinimumRectangle {
    public int solution(int[][] sizes) {

        int maxW = 0;
        int maxH = 0;
        // 향상된 for문 int[] size
        for (int[] size : sizes) {
            // size 가로세로 길이에서 큰 수, 작은 수를 각각 모아
            // 명함 크기를 맞추기 쉽게 바꿈
            int big = Math.max(size[0], size[1]);
            int small = Math.min(size[0], size[1]);

            // 큰 수를 모아놓은 길이에서 제일 큰 수를 고름
            maxW = Math.max(maxW, big);
            // 작은 수를 모아놓은 길이에서 제일 큰 수를 고름.
            maxH = Math.max(maxH, small);
        }
        // 어떤 명함을 넣어도 가로 세로 길이를 만족하여 명함을 넣을 수 있음
        // 가로 * 세로 return
        return maxW * maxH;
/* List<>로 작성시
        List<Integer> w = new ArrayList<>();
        List<Integer> h = new ArrayList<>();

        for (int[] size : sizes) {
            w.add(Math.max(size[0], size[1]));
            h.add(Math.min(size[0], size[1]));
        }
        int a = Collections.max(w);
        int b = Collections.max(h);

        return a*b;*/
    }
    public static void main(String[] args) {
        MinimumRectangle sol = new MinimumRectangle();

        // 테스트 1 (공식 예시로 많이 쓰이는 케이스)
        int[][] sizes1 = { {60, 50}, {30, 70}, {60, 30}, {80, 40} };
        int expected1 = 4000;
        int result1 = sol.solution(sizes1);
        System.out.println("Test1 expected=" + expected1 + ", result=" + result1);

        // 테스트 2 (모든 명함이 같은 방향)
        int[][] sizes2 = { {10, 20}, {10, 20}, {10, 20} };
        int expected2 = 200; // 20*10
        int result2 = sol.solution(sizes2);
        System.out.println("Test2 expected=" + expected2 + ", result=" + result2);

        // 테스트 3 (회전이 핵심인 케이스)
        int[][] sizes3 = { {5, 7}, {6, 4}, {3, 2} };
        int expected3 = 35; // (7,5), (6,4), (3,2) => maxW=7, maxH=5 => 35
        int result3 = sol.solution(sizes3);
        System.out.println("Test3 expected=" + expected3 + ", result=" + result3);

        // 테스트 4 (1장만 있는 케이스)
        int[][] sizes4 = { {8, 13} };
        int expected4 = 104; // 13*8
        int result4 = sol.solution(sizes4);
        System.out.println("Test4 expected=" + expected4 + ", result=" + result4);

        // 테스트 5 (정사각형 포함)
        int[][] sizes5 = { {10, 10}, {12, 3}, {8, 15} };
        int expected5 = 180; // (10,10),(12,3),(15,8) => maxW=15, maxH=12 => 180
        int result5 = sol.solution(sizes5);
        System.out.println("Test5 expected=" + expected5 + ", result=" + result5);
    }
}
