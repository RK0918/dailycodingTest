package codingtest_java.bruteforce;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class PowergridDivision {
    private List<Integer>[] graph; // 클래스 전역(필드) 선언

    public int solution(int n, int[][] wires) {
        graph = new ArrayList[n+1]; // 메서드 안에서 실제 그래프 초기화/생성
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

    // 여기서 알아야 될 점은, 한 쪽 전력망의 전선의 개수만 알아내면
    // 총 전선 수 -(구한 node 수) = 다른 전력망의 전선 수이기 때문에
    // 굳이 양 쪽의 전력망 각각의 개수를 구할 필요도 없고 문제 설명에서
    // 각 전력망의 전선 수의 차이(abs)를 return하면 되기 때문에 더더욱
    // 한 쪽만 알아내면 된다.
    private int bfsCount(int n, int start, int blockU, int blockV) {
        boolean[] visited = new boolean[n+1];
        Deque<Integer> dq = new ArrayDeque<>();

        // solutino()함수에서 for문을 통해 반복처리하며
        // bfs를 실행하고 있으므로 bfs()함수에서 반복문(while문) 전에
        // 방문한 전선을 방문처리
        visited[start] = true;
        dq.offerLast(start);
        int count =1; // 방문한 전선 수를 이미 센 걸로 판정

        while (!dq.isEmpty())  {
            // 첫 cur은 start이고, 그 다음부턴 첫 전선부터
            // 이어진 전선을 while문과 deque를 통해 구하게 됨.
            int cur = dq.pollFirst();

            // graph[cur] => 이어진 전선을 모두 향상된 for문을 토앻
            // 꺼내어 방문하지 않은 전선이면 전선 수를 count++;
            for (int next : graph[cur]) {
                // 여기서 cur=커팅한 전선 -> next=다음에 자를 전선, 따라서 커팅한 전선 => 커팅한 다른 부분의 전선으로 넘어갈 수 없음
                // 커팅한 전선(blockU - blockV 사이, "-" 를 자름.)
                // (1. blockU => blockV)  || (2. blockU <= blockV)
                // 위 두 가지 경우처럼 차단된 건선이면 건너 뛰는 것을 의미

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
