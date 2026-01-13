package codingtest_java.sorting;

import java.util.Arrays;

public class LargestNumber {
    public String solution(int[] numbers) {
        // numbers의 int[]의 인덱스값이 전부 0일 경우 체크
        // for문으로 처리해도 됨.

        // int[] numbers는 collection(List, Set) 등이 아니기 때문에
        // numbers.stream() 이 아니라 Arrays.stream(numbers)
        // Arrays.stream(int[])의 결과는 IntStream이고, IntStream에는
        // sum()이 있어서 합계를 바로 구할 수 있음
        int sum = Arrays.stream(numbers).sum();
        if (sum == 0) {
            return "0";
        }

        // int[] -> String[] 배열 변환
        String[] arr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }
        // compareTo를 통해
        // (b+a).compareTo(a+b) : 큰 조합이 앞으로(내림차순) -> 이게 정답
        // (a+b).compareTo(b+a) : 작은 조합이 앞으로(오름차순)
        // (b+a).compareTo(a+b) -> "303".compareTo("330") 는 음수 (내림차순)
        // (a+b).compareTo(b+a) -> "330".compareTo("303") 는 양수 (오름차순)
        //→ “a가 앞” → "3", "30" (정답 방향)
        Arrays.sort(arr, (a, b) -> (b+a).compareTo(a+b));


//        첫 문단에서 stream().sum()을 통해
//        배열값이 전부 0인지 검증하지 않았을 경우
//        if (arr[0].equals("0")) {
//            return "0";
//        }

        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LargestNumber sol = new LargestNumber();
        int[] numbers = {6, 34, 30, 9};
        System.out.println("result = " + sol.solution(numbers));

    }
}
