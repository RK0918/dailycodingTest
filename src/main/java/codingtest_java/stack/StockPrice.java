package codingtest_java.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class StockPrice {
    public int[] solution(int[] prices) {
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
    }

    public static void main(String[] args) {
        StockPrice sol = new StockPrice();
        int[] prices= {1, 2, 2, 3, 2};
        int[] result = sol.solution(prices);
        System.out.println("sol = " + Arrays.toString(result));
    }
}
