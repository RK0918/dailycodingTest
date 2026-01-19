package codingtest_java.bruteforce;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MockExam {
    public int[] solution(int[] answers) {

        // 아래의 주석 코드를 리팩토링

        // 각 멤버별로 번호를 반복적으로 찍기 때문에 각 배열로 초기화
        int[] member1 = {1, 2, 3, 4, 5};
        int[] member2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] member3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] score = new int[3];


        // 배열을 이어붙이기 보단 인덱스값의 나머지(m.length/n)를 통해
        // 반복되는 수를 answers와 카운팅하여 score 각 인덱스값에 초기화
        for(int i=0; i<answers.length; i++) {
            if(answers[i] == member1[i%member1.length]) {score[0]++;}
            if(answers[i] == member2[i%member2.length]) {score[1]++;}
            if(answers[i] == member3[i%member3.length]) {score[2]++;}
        }
        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
        List<Integer> list = new ArrayList<>();
        if(maxScore == score[0]) {list.add(1);}
        if(maxScore == score[1]) {list.add(2);}
        if(maxScore == score[2]) {list.add(3);}



/*
        // 바깥: 3명 반복
        for (int m = 0; m < 3; m++) {
            int count = 0;
            int[] pattern = members[m];

            // 안쪽: 정답지 반복
            for (int i = 0; i < answers.length; i++) {
                // i % pattern.length 로 패턴 반복
                if (answers[i] == pattern[i % pattern.length]) {
                    count++;
                }
            }
            score[m] = count;
        }

        List<Integer> idx = new ArrayList<>();
        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
        for (int i=0; i < 3; i++) {
            if (score[i]== maxScore) {
                idx.add(i+1);
            }
        }
*/

        return list.stream().mapToInt(i->i).toArray();
    }
    public static void main(String[] args) {
        MockExam sol = new MockExam();

        // 테스트 1 (공식 예시): [1]
        int[] t1 = {1, 2, 3, 4, 5};
        System.out.println("t1 answers = " + Arrays.toString(t1));
        System.out.println("t1 result  = " + Arrays.toString(sol.solution(t1)));
        System.out.println();

        // 테스트 2 (공식 예시): [1,2,3]
        int[] t2 = {1, 3, 2, 4, 2};
        System.out.println("t2 answers = " + Arrays.toString(t2));
        System.out.println("t2 result  = " + Arrays.toString(sol.solution(t2)));
        System.out.println();

        // 테스트 3: 전부 2면 2번이 유리한지 확인 (패턴이 2가 많음)
        int[] t3 = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
        System.out.println("t3 answers = " + Arrays.toString(t3));
        System.out.println("t3 result  = " + Arrays.toString(sol.solution(t3)));
        System.out.println();

        // 테스트 4: 길이가 긴 케이스(패턴 반복 확인)
        int[] t4 = {1,2,3,4,5, 1,2,3,4,5, 1,2,3,4,5};
        System.out.println("t4 answers = " + Arrays.toString(t4));
        System.out.println("t4 result  = " + Arrays.toString(sol.solution(t4)));
        System.out.println();

        // 테스트 5: 랜덤 느낌 케이스
        int[] t5 = {3, 3, 2, 1, 5, 4, 2, 2, 1, 3, 4, 5};
        System.out.println("t5 answers = " + Arrays.toString(t5));
        System.out.println("t5 result  = " + Arrays.toString(sol.solution(t5)));
    }
}
