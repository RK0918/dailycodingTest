package codingtest_java.dfs_bfs;

class Network {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfs(computers, visited, i);
                answer++;
            }
        }
        return answer;
    }
    void dfs(int[][] computers, boolean[] visited, int start) {
        visited[start] = true;
        for(int i = 0; i < computers.length; i++) {
            // 이미 자기 자신은 chk[start] = true;를 통해 방문처리 했으므로
            // !chk[i]에서 걸러지기 때문에 자기자신이 아닌 조건을 추가할 필요가 없다.
            if(computers[start][i] == 1 && !visited[i]) {
                dfs(computers, visited, i);
            }
        }
    }

    public static void main(String[] args) {
        Network sb = new Network();
        int[][] computers = new int[][] {{1, 1, 0}, {1 ,1 ,1}, {0, 1, 1}};

        System.out.println(sb.solution(3, computers));
    }
}
