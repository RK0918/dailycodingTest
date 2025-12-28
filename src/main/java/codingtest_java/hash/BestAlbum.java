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
        // 2. 장르별 노래 정보
        Map<String, List<Song>> genreSongs = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];

            genrePlayCount.put(genre, genrePlayCount.getOrDefault(genre, 0) + play);
            genreSongs.putIfAbsent(genre, new ArrayList<>());
            genreSongs.get(genre).add(new Song(i, play));
        }

        // 3. 장르별 총 재생횟수 기준 정렬
        List<String> sortedGenre = new ArrayList<>(genrePlayCount.keySet());
        sortedGenre.sort((a, b) -> genrePlayCount.get(b) - genrePlayCount.get(a));
        // 4. 향상된 for문을 통해 장르에 저장된 노래정보(고유번호, 재생횟수)를 꺼냄.
        List<Integer> result = new ArrayList<>();
        for (String genre : sortedGenre) {
            List<Song> songs = genreSongs.get(genre);
            // 재생횟수 내림차순, 같으면 ID 오름차순
            songs.sort((a, b) -> (a.play != b.play) ? b.play - a.play : a.id - b.id);
            // 장르의 곡이 2곡 이하일 경우도 있을 수 있기 때문에 Math.min 함수 사용
            int count = Math.min(songs.size(), 2);
            for (int i = 0; i < count; i++) {
                // result List를 초기화하고 장르의 각 곡의 고유번호를 result에 저장
                result.add(songs.get(i).id);
            }
        }

        //  stream()함수를 통해 List -> 배열 변환
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