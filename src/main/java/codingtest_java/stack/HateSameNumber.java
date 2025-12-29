package codingtest_java.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HateSameNumber {
    public int[] solution(int[] arr) {
        List<Integer> stack = new ArrayList<>();
        // Deque<Integer> stack = new ArrayDeque<>();
        for (int num : arr) {
            // stackd 비어있거나 stack이 비어있지 않으면 stack의 마지막 인덱스값과 비교하여
            // 같지 않으면 stack에 추가

            // stack.size()-1 => getLast()로도 변경가능 JDK 21에서 default 메서드로 제공
            // Deque일 경우, stack.get() 대신 stack.peekLast() != num
            if (stack.isEmpty() || stack.get(stack.size() - 1) != num) {
                stack.add(num);
                // stack.offer(num); *Deque 사용시
            }
        }

        return stack.stream().mapToInt(i -> i).toArray();
        // List를 배열로 mapToInt(Integer::intValue)랑 동일함
    }
    public static void main(String[] args) {
        HateSameNumber sol = new HateSameNumber();

        int[] arr = {1, 1, 3, 3, 0, 1, 1};   // 테스트 입력
        int[] result = sol.solution(arr);

        System.out.println(Arrays.toString(result));
        // 출력: [1, 3, 0, 1]
    }
}

/* 순수 배열로 푼 문제
public class HateSameNumber {
    public int[] solution(int[] arr) {
        int n = arr.length;
        int[] temp = new int[n];  // 최대 n개까지 들어갈 수 있음
        int idx = 0;

        temp[idx++] = arr[0];     // 첫 값은 바로 넣기

        for (int i = 1; i < n; i++) {
            if (temp[idx - 1] != arr[i]) { // 바로 이전 값과 비교
                temp[idx++] = arr[i];
            }
        }

        // 정답 배열에 필요한 크기만큼 복사
        int[] answer = new int[idx];
        for (int i = 0; i < idx; i++) {
            answer[i] = temp[i];
        }

        return answer;
    }
}
*/

