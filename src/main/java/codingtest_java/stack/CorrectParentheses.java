package codingtest_java.stack;

import java.util.ArrayList;
import java.util.List;

public class CorrectParentheses {
    boolean solution(String s) {
        List<Character> stack = new ArrayList<>();
        // Deque<Character> stack = new ArrayDeque<>();
        // Deque를 쓰면 offer() / poll() / peek() 로 가능
        for (char c : s.toCharArray()) {
            if (c == '(') { // char c가 '('면 추가하고 아니라면, stack 비어있음 -> 이미 false
                stack.add(c);
                // stack.offer(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.remove(stack.size() - 1);
                // stack.pollLast();
            }
        /*  count로도 가능
            if (c == '(') {
                count++;
            } else {
                count--;
                if (count < 0) {
                    return false;  // 닫는 괄호가 더 많음
                }
            }
        */
        }
        return stack.isEmpty(); // stack이 비어있으면 true 비어있지 않다면 괄호가 닫히지 않으므로 false

    }

    public static void main(String[] args) {
        CorrectParentheses sol = new CorrectParentheses();

        System.out.println(sol.solution("(())()")); // true
        System.out.println(sol.solution("(())())")); // false
    }
}
