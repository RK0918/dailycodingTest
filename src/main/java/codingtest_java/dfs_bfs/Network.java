package codingtest_java.dfs_bfs;

class Network {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] chk = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!chk[i]) {
                dfs(computers, chk, i);
                answer++;
            }
        }
        return answer;
    }
    void dfs(int[][] computers, boolean[] chk, int start) {
        chk[start] = true;
        for(int i = 0; i < computers.length; i++) {
            // 이미 자기 자신은 chk[start] = true;를 통해 방문처리 했으므로
            // !chk[i]에서 걸러지기 때문에 자기자신이 아닌 조건을 추가할 필요가 없다.
            if(computers[start][i] == 1 && !chk[i]) {
                dfs(computers, chk, i);
            }
        }
    }
}
