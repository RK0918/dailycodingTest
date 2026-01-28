package codingtest_java.bruteforce;

public class Fatigue {
    boolean[] visited;
    int answer=0;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, dungeons, 0);
        return answer;
    }

    void dfs(int k, int[][] dungeons, int count) {
        // 순열로 던전을 방문하여 제일 많이 탐험한 경우의 수(answer)를 초기화
        answer = Math.max(answer, count);
        for (int i=0; i < dungeons.length; i++) {
            // 방문하지 않은 던전 and k(피로도)가 최소 피로도와 같거나 높을 경우
            if (!visited[i] && k >= dungeons[i][0]) {
                //  던전 방문처리(true)
                visited[i] = true;
                // dfs를 통해 재귀하여 순열을 구함.
                dfs(k-dungeons[i][1], dungeons, count +1);
                // 백트래킹을 함. 다시 그 다음 순열을 구함
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Fatigue sb = new Fatigue();

        int[][] d = new int[][] {{80,20},{50,40},{30,10}};
        System.out.println(sb.solution(80, d));
    }
}
