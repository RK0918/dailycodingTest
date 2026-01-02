package codingtest_java.stack;


import java.io.IOException;
// import java.util.ArrayList;
import java.util.Arrays;
// import java.util.List;

import java.util.*;

public class FunctionDevelopment {
    public int[] solution(int[] progresses, int[] speeds) {
        Deque<Integer> stack = new ArrayDeque<>();
        int date = 0;
        int count =0;

        for (int i=0; i < progresses.length; i++) {
            int progress = progresses[i];
            progress +=  (speeds[i]*date);

            // 현재 배포일(date)에 못 끝냈으면 이전 count를 stack에 저장
            if (progress < 100 && count >0) {
                stack.offer(count);
                count =0;
            }
            // (count가 0이고 progress가 100 이하일 경우)
            // count가 0인 경우는 결국
            // 1. 첫 번째 기능을 개발할 경우
            // 2. 기능개발을 마치고 첫 번째 if문을 통해 이전 count가 stack에 저장되고 현재 기능 개발을 진행
            // 3. progress가 100을 넘어서 이전 개발과 같이 count++;
            while (progress < 100) {
                progress += speeds[i];
                date++;
            }
            count++;
        }
        stack.offer(count);
        return stack.stream().mapToInt(i->i).toArray();
    }
/*

public class FunctionDevelopment {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> stack = new ArrayList<>();
        int date = 0; // 작업 일수
        int shipCount=0; // 배포하게 되는 progress 수

        for (int i=0; i < progresses.length; i++) {
            int progress = progresses[i];

            if (shipCount > 0) { // 배포할 기능이 1개 이상일 경우
                progress += speeds[i] * date;
                if (progress >= 100) { // 기존에 지난 작업일수만큼 개발속도 계산
                    shipCount +=1; // 이전 작업 일수에서 이미 개발완료이므로 같이 배포
                } else {
                    stack.add(shipCount); // 같이 배포하지 않으므로 이전 기능배포
                    shipCount=0; // 배포하는 기능의 수 0으로 초기화

                    //
                    while (progress < 100) { // 작업을 진행하여 진척도 100%가 되면 멈춤
                        progress += speeds[i];
                        date++; }
                    shipCount++;

                }
            } else {
                // shipCount가 0일 경우 작업하여 100% 이상이 될때까지 기능개발
                while (progress < 100) {
                    progress += speeds[i];
                    date++; }
                shipCount++;
            }
        }
        stack.add(shipCount);
        return stack.stream().mapToInt(i->i).toArray();
    }

*/
    public static void main(String[] args) throws IOException {
        FunctionDevelopment sol = new FunctionDevelopment();


        // 테스트 케이스 1
        int[] result1 = sol.solution(
                new int[]{93, 30, 55},
                new int[]{1, 30, 5}
        );
        System.out.println("결과 1: " + Arrays.toString(result1)); // [2, 1]

        // 테스트 케이스 2
        int[] result2 = sol.solution(
                new int[]{95, 90, 99, 99, 80, 99},
                new int[]{1, 1, 1, 1, 1, 1}
        );
        System.out.println("결과 2: " + Arrays.toString(result2)); // [1, 3, 2]
    }
}
