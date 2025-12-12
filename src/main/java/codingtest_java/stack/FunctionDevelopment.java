package codingtest_java.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
