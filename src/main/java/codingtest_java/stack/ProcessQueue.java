package codingtest_java.stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class ProcessQueue {
    public int solution(int[] priorities, int location) {
        // 프로세스, 원래 인덱스 저장하는 큐 (Process는 중첩클래스로 인덱스값, 우선순위 저장)
        Deque<Process> queue = new ArrayDeque<>();

        // 큐의 값 인덱스값을 Process클래스로 초기화
        for (int i=0; i < priorities.length; i++) {
            queue.offer(new Process(i, priorities[i]));
        }
        int executionOrder = 0; // 실행순서

        while (!queue.isEmpty()) {
            // 1.  큐에서 프로세스 꺼내기 poll()
            Process current = queue.poll();

            // 2. 큐에 더 높은 우선순위 있는지 탐색
            boolean HigherPriority = false;
            for (Process p : queue) {
                if (p.priority > current.priority) {
                    HigherPriority = true;
                    break;
                }
            }
            // 3. 더 높은 우선순위가 있음(true) -> 다시 큐에 넣기
            //  더 높은 우선순위가 없음(false) -> 실행순서 ++; 하여 실행했다는 것
            if (HigherPriority) {
                queue.offer(current);
            } else {
                executionOrder++;
                if (current.index == location) {
                    return executionOrder;
                }
            }
        }
        return executionOrder;
    }
    // 프로세스 정보(원래 위치, 우선순위)
    static class Process {
        int index;    // 원래 위치
        int priority; // 우선순위

        Process(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
    // test case
    public static void main(String[] args) {
        ProcessQueue sol = new ProcessQueue();

        // 예제 1
        int[] priorities1 = {2, 1, 3, 2};
        int location1 = 2;
        System.out.println("예제 1: " + sol.solution(priorities1, location1)); // 1

        // 예제 25
        int[] priorities2 = {1, 1, 9, 1, 1, 1};
        int location2 = 0;
        System.out.println("예제 2: " + sol.solution(priorities2, location2)); // 5
    }



}
