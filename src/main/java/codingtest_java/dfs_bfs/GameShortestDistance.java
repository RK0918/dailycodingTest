package codingtest_java.dfs_bfs;

import java.util.*;

class GameShortestDistance {
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        int[] dx = {-1, 1 , 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 방문체크
        boolean[][] visited = new boolean[n][m];

        // BFS를 위한 덱 선언
        Deque<int[]> deque = new ArrayDeque<>();

        // 시작점 (0, 0)을 덱의 맨 뒤에 삽입, 이동 거리는 1칸부터 시작
        // { x좌표, y좌표재 거리 }
        deque.offerLast(new int[]{0, 0, 1});
        visited[0][0] = true;

        // 덱이 빌 때까지 반복

        while (!deque.isEmpty()) {
            // 덱의 맨 앞에서 현재 위치를 꺼냄
            int[] current = deque.pollFirst();
            int x = current[0];
            int y = current[1];
            int distance = current[2];

            // 목적지(맨 우측 하단) 도착했으면 현재까지 거리 반환
            if (x== n - 1 && y == m - 1) {
                return distance;
            }

            // 현재 위치에서 상 하 좌 우 방향탐색

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i]; // 다음 이동할 x좌표
                int ny = y + dy[i];

                // 1. 맵을 벗어나지 말아야 함.
                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    // 맵을 벗어나지 않았다면
                    // 2. 벽이 아니여야 함. (maps 값 1)
                    // 3. 아직 방문하지 않은 길이여야 함.

                    if (maps[nx][ny] == 1 && !visited[nx][ny]) {

                        visited[nx][ny] = true;  // 방문처리

                        // 이동 거리를 1 증가시키고 덱의 맨 뒤에 다음 좌표
                        deque.offerLast(new int[]{nx, ny, distance + 1});

                    }

                }


            }

        }
        // 모든 큐를 돌았는데 목적지 도착 못했다면 -1
        return -1;
    }
/*

        int n = maps.length; // 행
        int m = maps[0].length; // 열
        // 상하좌우
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        // 방문 여부, 거리 저장
        // distance -> 해당 좌표까지의 거리값을 구하여 저장
        int[][] distance = new int[n][m];
        for (int[] row : distance) Arrays.fill(row, -1);
        // Deque -> 탐색할 좌표(위치)
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offerLast(new int[]{0, 0});

        distance[0][0] = 1; // 시작점 거리 1
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int x = cur[0];
            int y = cur[1];

            if (x == n - 1 && y == m - 1) {
                return distance[x][y];
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if (maps[nx][ny] == 0 || distance[nx][ny] != -1) continue;
                distance[nx][ny] = distance[x][y] + 1;
                deque.offerLast(new int[]{nx, ny});
            }


        }


        return -1;
    }

*/


    // 💡 테스트를 위한 main 메서드 (psvm)
    public static void main(String[] args) {
        GameShortestDistance sol = new GameShortestDistance();

        // 테스트 케이스 1: 정상적으로 도달 가능한 경우 (기댓값: 11)
        int[][] maps1 = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 1},
                {0, 0, 0, 0, 1}
        };

        // 테스트 케이스 2: 벽에 막혀 도달할 수 없는 경우 (기댓값: -1)
        int[][] maps2 = {
                {1, 0, 1, 1, 1},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 0, 0},
                {0, 0, 0, 0, 1}
        };

        // 결과 출력
        System.out.println("테스트 케이스 1 실행 결과: " + sol.solution(maps1));
        System.out.println("테스트 케이스 2 실행 결과: " + sol.solution(maps2));
    }
}

/* 만약 모든 거리의 경우의 수를 모두 구해야 되며 거기서 최소거리를 반환해야 될 경우
 dfs + dp 조합이 좋을 것

 int[][] dp = new int[n][m];
for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);

// DFS로 모든 경로 탐색하면서 최솟값 갱신
void dfs(int[][] maps, int[][] dp, int x, int y, int dist) {
    if (x == n-1 && y == m-1) {
        dp[x][y] = Math.min(dp[x][y], dist); // 도달할 때마다 최솟값 갱신
        return;
    }

    for (int d = 0; d < 4; d++) {
        int nx = x + dx[d];
        int ny = y + dy[d];

        if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
        if (maps[nx][ny] == 0) continue;
        if (dist >= dp[nx][ny]) continue; // DP: 이미 더 짧은 거리면 스킵

        dp[nx][ny] = dist + 1; // 현재까지 최솟값 저장
        dfs(maps, dp, nx, ny, dist + 1);
    }
}

 */