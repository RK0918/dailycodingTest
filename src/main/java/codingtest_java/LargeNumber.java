package codingtest_java;
// 11.24 21:29 push

class LargeNumber {
    public String solution(String number, int k) {
        StringBuilder builder = new StringBuilder(number);
        int length = builder.length() - k;

        for (int i = 1; i < builder.length() && builder.length() > length; i++) {
            if (builder.charAt(i) > builder.charAt(i - 1)) {
                builder.deleteCharAt(i - 1);
                i = Math.max(0, i - 2);
            }
        }

        return builder.substring(0, length);
    }
        public static void main(String[] args) {
        LargeNumber sol = new LargeNumber();
        System.out.println(sol.solution("1974", 2));
    }
}
/*
public class LargeNumber {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        int len = number.length()-k;
        int start = 0;

        for (int i=0; len > i; i++ ) {
            char max = '0';
            int maxIdx = 0;
            for (int j = start; j <= k + i; j++) {
                if (number.charAt(j) > max) {
                    max = number.charAt(j);
                    maxIdx = j;
                }
            }
            answer.append(max);
            start = maxIdx + 1;
        }
        return answer.toString();
    }

    public static void main(String[] args) {
        LargeNumber sol = new LargeNumber();
        System.out.println(sol.solution("1974", 2));
    }
}
*/


// 스택

/*
import java.util.Stack;
class Solution {
    public String solution(String number, int k) {
        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }
        for (int i=0; i<result.length; i++) {
            result[i] = stack.get(i);
        }
        return new String(result);
    }
}
*/
