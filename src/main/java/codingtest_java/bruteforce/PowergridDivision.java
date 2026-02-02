package codingtest_java.bruteforce;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PowergridDivision {
    private List<Integer>[] graph;

    public int solution(int n, int[][] wires) {
        graph = new ArrayList[n+1];
        for(int i=1; i <= n; i++ ) {
            graph[i] = new ArrayList<>();
        }

        // 그래프 연결
        for (int[] w : wires) {
            int a = w[0];
            int b = w[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        int answer = Integer.MAX_VALUE;

        // 전선 하나씩 끊기
        for (int[] w: wires) {
            int blockU = w[0];
            int blockV = w[1];

            int count = bfsCount(n, blockU, blockU, blockV);
            int diff = Math.abs((n-count)-count);

            answer = Math.min(answer, diff);

        }

        return answer;
    }
    private int bfsCount(int n, int start, int blockU, int blockV) {
        boolean[] visited = new boolean[n+1];
        Deque<Integer> dq = new ArrayDeque<>();

        visited[start] = true;
        dq.offerLast(start);
        int count =1;

        while (!dq.isEmpty())  {
            int cur = dq.pollFirst();
            // 차단된 간선이면 건너 뛰기
            for (int next : graph[cur]) {
                if ((cur == blockU && next == blockV) ||
                        (cur == blockV && next == blockU)) {
                    continue;
                }
                if (!visited[next]) {
                    // 다음 전선에 방문처리(true)
                    visited[next] =true;
                    dq.offerLast(next);
                    count++;
                }
            }

        }

        return count;
    }
    public static void main(String[] args) {
        PowergridDivision sol = new PowergridDivision();

        // 예제 1
        int n1 = 9;
        int[][] wires1 = {
                {1, 3}, {2, 3}, {3, 4}, {4, 5},
                {4, 6}, {4, 7}, {7, 8}, {7, 9}
        };
        runTest(sol, 1, n1, wires1, 3);

        // 예제 2
        int n2 = 4;
        int[][] wires2 = {
                {1, 2}, {2, 3}, {3, 4}
        };
        runTest(sol, 2, n2, wires2, 0);

        // 예제 3
        int n3 = 7;
        int[][] wires3 = {
                {1, 2}, {2, 7}, {3, 7}, {3, 4}, {4, 5}, {6, 7}
        };
        runTest(sol, 3, n3, wires3, 1);
    }

    private static void runTest(PowergridDivision sol, int caseNo, int n, int[][] wires, int expected) {
        int actual = sol.solution(n, wires);
        System.out.printf("Case #%d -> actual=%d, expected=%d : %s%n",
                caseNo, actual, expected, (actual == expected ? "PASS ✅" : "FAIL ❌"));
    }

}
