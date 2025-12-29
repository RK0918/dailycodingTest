package codingtest_java.hash;

import java.util.*;

class BestAlbum {
    static class Song {
        int id;
        int play;
        public Song(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        // 1. 장르별 총 재생횟수
        Map<String, Integer> genrePlayCount = new HashMap<>();
        // 2. 장르별 노래 정보 (노래정보[고유번호 i, 재생횟수 play] 리스트로 map에 저장)
        Map<String, List<Song>> genreSongs = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            genrePlayCount.put(genre, genrePlayCount.getOrDefault(genre, 0) + play);
            genreSongs.putIfAbsent(genre, new ArrayList<>());
            genreSongs.get(genre).add(new Song(i, play));
        }

        // 3. 장르별 총 재생횟수 기준 정렬 ( 장르를 '총 재생횟수의 내림차순'으로 정렬 )
        List<String> sortedGenre = new ArrayList<>(genrePlayCount.keySet());
        // (a, b) => 총 재생횟수
        // genrePlayCount.get(b) - ... get(a) => 총 재생횟수의 내림차순으로 정렬하겠다는 뜻
        sortedGenre.sort((a, b) -> genrePlayCount.get(b) - genrePlayCount.get(a));
        // 4. 향상된 for문을 통해 장르에 저장된 노래정보(고유번호, 재생횟수)를 꺼냄.
        List<Integer> result = new ArrayList<>();
        for (String genre : sortedGenre) {
            // 장르의 노래정보(list)를 songs에 저장
            List<Song> songs = genreSongs.get(genre);
            // 재생횟수(play) 내림차순, 같으면 고유번호(id) 오름차순
            songs.sort((a, b) -> (a.play != b.play) ? b.play - a.play : a.id - b.id);

            // 장르의 곡이 2곡 미만인 경우도 고려하여 Math.min 함수 사용
            int count = Math.min(songs.size(), 2);
            for (int i = 0; i < count; i++) {
                // 이전에 초기화해둔 list result에
                // 정렬되어있는 장르 노래의 각 고유번호를 result에 저장
                result.add(songs.get(i).id);
            }
        }

        //  List.stream().mapToInt(i->i).toArray() 함수를 통해 List -> 배열 변환
        return result.stream().mapToInt(i -> i).toArray();

        /* ===== for문 변환과 비교 =====
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }
        return answer;
        */
    }

    public static void main(String[] args) {
        BestAlbum sol = new BestAlbum();
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] plays = {500, 600, 150, 800, 2500};
        System.out.println(Arrays.toString(sol.solution(genres, plays)));
        // 예상 출력: [4, 1, 3, 0]
    }
}