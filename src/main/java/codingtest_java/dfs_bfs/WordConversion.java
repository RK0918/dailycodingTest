package codingtest_java.dfs_bfs;

import java.util.ArrayDeque;
import java.util.Deque;

public class WordConversion {
    public int solution(String begin, String target, String[] words) {
        // 1. words에서 target 문자가 있는지 체크
        boolean targetWord = false;

        for (int i =0; i < words.length; i++) {
            if(target.equals(words[i])) {
                targetWord = true;
                break;
            }

        }
        if (!targetWord) return 0;

        // 2. word를 이용할 deque, 방문체크를 위해 visited 생성

        Deque<String> deque = new ArrayDeque<>();
        boolean[] visited = new boolean[words.length];

        deque.offerLast(begin);
        int step = 0;

        while(!deque.isEmpty()) {
            int size =  deque.size();

            for( int i=0; i < size; i++) {
                String current = deque.pollFirst();

                if (current.equals(target)) return step;

                for (int j=0; j < words.length; j++) {
                    if(!visited[j] && canConvert(current, words[j])) {
                        visited[j] = true;
                        deque.offerLast(words[j]);

                    }
                }
            }
            step++;

        }
        return 0;
    }

    private boolean canConvert(String w1, String w2) {
        int diffCount = 0;
        for(int i =0; i < w1.length(); i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                diffCount++;
            }
            if(diffCount > 1) return false;

        }
        // diffCount 1이하면 되는데 diffCount가 0일 경우 false로 되기 때문에
        // 반드시 diffCount ==1;을 반환해준다.
        return diffCount ==1;
    }
    // 테스트 케이스를 위한 main 메서드
    public static void main(String[] args) {
        WordConversion solver = new WordConversion();

        // [테스트 케이스 1] 정상적으로 변환 가능한 경우
        String begin1 = "hit";
        String target1 = "cog";
        String[] words1 = {"hot", "dot", "dog", "lot", "log", "cog"};
        int result1 = solver.solution(begin1, target1, words1);
        System.out.println("테스트 케이스 1 결과: " + result1 + " (기댓값: 4) -> " + (result1 == 4 ? "✅ 통과" : "❌ 실패"));

        // [테스트 케이스 2] target 단어가 배열에 없는 경우 (초기 조건에서 걸러짐)
        String begin2 = "hit";
        String target2 = "cog";
        String[] words2 = {"hot", "dot", "dog", "lot", "log"};
        int result2 = solver.solution(begin2, target2, words2);
        System.out.println("테스트 케이스 2 결과: " + result2 + " (기댓값: 0) -> " + (result2 == 0 ? "✅ 통과" : "❌ 실패"));

        // [테스트 케이스 3] 변환할 수 없는 단어 구조인 경우 (연결 고리가 끊김)
        String begin3 = "hit";
        String target3 = "cog";
        String[] words3 = {"hot", "dot", "dog", "lot", "log", "cat", "cog"};
        // hit -> hot -> dot/lot -> dog/log 까지는 가지만, cog로 가려면 cog와 한 글자 차이나는 단어가 부족하거나 연결이 안 될 때 (이 배열은 연결됨)
        // 연결 안 되는 배열로 수정:
        String[] disconnectedWords = {"hot", "dot", "dog", "lot", "mac", "cog"};
        int result3 = solver.solution(begin3, target3, disconnectedWords);
        System.out.println("테스트 케이스 3 결과: " + result3 + " (기댓값: 0) -> " + (result3 == 0 ? "✅ 통과" : "❌ 실패"));
    }
}
