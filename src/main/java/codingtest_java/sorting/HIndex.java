package codingtest_java.sorting;

import java.util.*;
import java.io.*;

public class HIndex {
    public int solution(int[] citations) {
        int answer = 0;
        // 논문의 인용수를 정렬
        Arrays.sort(citations);
        int len = citations.length;
        for (int i =0; i < len; i++) {
            // citations[i]의 인덱스 i만큼 인용 횟수가
            // 있어야 하므로 ex) citations[i] = 3일 경우 적어도 자신(3)을 포함한 인용횟수가 3개 이상 있어야 함
            // 따라서 citations[i]는 전체 논문 인용 횟수(citaitions.length)에서 i를 뺀 값보다 커야 H-Index가 될 수 있다.
            if ( citations[i] >= len-i)  {
                answer = len-i;
                break;
                // 조건을 만족한다면 H-Index값이 정해졌기 때문에 break
            }
        }
        return answer;
    }

    public static void main(String[] args) throws IOException {
        HIndex sol = new HIndex();

        // 입력 예시:
        // 1 2 3 4 5

        System.out.println("한 줄 입력하세요 (예: 1 2 3 4 5)");

        /* --------- 1) Scanner 방식 ---------- */
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();            // 한 줄 입력
        String[] parts = line.split(" ");       // 공백 기준 분리

        int[] arr1 = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr1[i] = Integer.parseInt(parts[i]);
        }


        /* --------- 2) BufferedReader + split ---------- */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts2 = br.readLine().split(" ");
        int[] arr2 = new int[parts2.length];
        for (int i = 0; i < parts2.length; i++) {
            arr2[i] = Integer.parseInt(parts2[i]);
        }


        /* --------- 3) BufferedReader + StringTokenizer ---------- */
        StringTokenizer st = new StringTokenizer(br.readLine()); // 공백 기준
        int n = st.countTokens();        // 토큰 개수만큼 배열 크기 지정
        int[] arr3 = new int[n];

        for (int i = 0; i < n; i++) {
            arr3[i] = Integer.parseInt(st.nextToken());
        }


        /* --------- 출력 ---------- */
        System.out.println("Scanner 결과 = " + sol.solution(arr1));
        System.out.println("split 결과   = " + sol.solution(arr2));
        System.out.println("Tokenizer 결과 = " + sol.solution(arr3));
    }
}
