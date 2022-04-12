package academy.devdojo.webflux.util;

import academy.devdojo.webflux.domain.Anime;

public class AnimeCreator {

    public static Anime createAnimeToBeSaved() {
        return Anime.builder()
                .name("Shurato")
                .build();
    }

    public static Anime createValidAnime() {
        return Anime.builder()
                .id(1)
                .name("Shurato")
                .build();
    }

    public static Anime createValidUpdatedAnime() {
        return Anime.builder()
                .id(1)
                .name("Shurato 2")
                .build();
    }

}
