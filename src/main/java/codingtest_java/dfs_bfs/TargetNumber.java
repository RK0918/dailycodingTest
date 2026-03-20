package codingtest_java.dfs_bfs;

/*
class TargetNumber {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = dfs(numbers, 0, 0, target);
        return answer;
    }
    int dfs(int[] numbers, int n, int sum, int target) {
        if(n == numbers.length) {
            if(sum == target) {
                return 1;
            }
            return 0;
        }
        return dfs(numbers, n + 1, sum + numbers[n], target) + dfs(numbers, n + 1, sum - numbers[n], target);
    }
}
*/



class TargetNumber {
    public int solution(int[] numbers, int target) {

        return dfs(numbers, target, 0, 0);
    }
    private int dfs(int[] numbers, int target, int index, int currentSum) {
        if (index == numbers.length) {
            if (target == currentSum) {
                return 1;
            } else {
                return 0;
            }
        }

        int addCase = dfs(numbers, target, index+1, currentSum+numbers[index]);
        int subCase = dfs(numbers, target, index+1, currentSum-numbers[index]);
        return addCase + subCase;
    }
}

/*

import java.util.LinkedList;
import java.util.Queue;

class Solution {
    Queue<Integer> q = new LinkedList<Integer>();
    public int solution(int[] numbers, int target) {
        int answer = 0;
        q.offer(0);
        q.offer(0);
        while(q.peek()!=null){
            int level =q.poll();
            int v = q.poll();
            if(level==numbers.length){
                if(v==target)
                    answer++;
            }else {

                q.offer(level + 1);
                q.offer(v + numbers[level]);

                q.offer(level + 1);
                q.offer(v - numbers[level]);
            }
        }
        return answer;
    }
}
*/

