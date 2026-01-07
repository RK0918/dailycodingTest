package codingtest_java.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
// Deque(stack)을 이용한 풀이 - 시간복잡도 O(n)
public class StockPrice {
    public int[] solution(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i=0; i < n; i++) {
            answer[i] = n-i-1;
        }
        /*
        Stack<Integer> stack = new ArrayStack<>(); 에서는 맨 위(맨 뒤)부터
        Stack의 "맨 위" = "맨 뒤"
        push(): 맨 위에 쌓음
        pop(): 맨 위에서 꺼냄
        peek(): 맨 위 확인

        따라서 Deque를 쓸 경우, offer(i), peekLast(), pollLast() 메서드를 활용
         */
        for (int i=0; i < n; i++) {
            while (!stack.isEmpty() && prices[stack.peekLast()] > prices[i]) {
                // stack(idx값 저장)이 비어있지 않고 prices[i]가 앞선 주식 가격보다 낮을
                // 경우
                int idx = stack.pollLast();
                answer[idx] = i-idx;
            }
            stack.offer(i);
        }
        return answer;
    }

    public static void main(String[] args) {
        StockPrice sol = new StockPrice();
        int[] prices1= {1, 2, 3, 2, 3};
        int[] result = sol.solution(prices1);
        System.out.println("sol = " + Arrays.toString(result));
        int[] prices2= {1, 2, 1, 2, 3};
        int[] result2 = sol.solution(prices2);
        System.out.println("sol = " + Arrays.toString(result2));
    }
}

/* 단순 중첩for문 활용시 (시간복잡도 O(N^2)) 로 테스트케이스 증가시
성능이슈 발생

Deque<Integer> stack = new ArrayDeque<>();
        int len = prices.length;

        for (int i =0; i < len-1; i++) {
            // 1초 시점부터 for문을 통해 n초까지 반복해서 담아냄
            int price = prices[i];
            // 담아낸 초가 1초라면 언제까지 가격이 떨어지지 않는지 count 초기화
            int count = 0;
            for (int j=i+1; j < len; j++ ) {
                // 비교하기 앞서 이미 초가 증가했으므로 ++;
                count ++;
                // price에 담긴 주식가격이 앞선 시간에 주식가격과 비교하여 크다면 중첩for문을 중지
                if (price > prices[j] ) {
                    break;
                }
            }
            // 주식가격이 떨어지지 않는 가격 deque에 추가
            stack.offer(count);
        }
        // 인덱스 예외처리를 위해 for문에서 i <len-1 했으므로 마지막 주식가격은 처리하지 않았으므로 deque에 0의 값 추가
        stack.offer(0);
        return stack.stream().mapToInt(i->i).toArray();

*/