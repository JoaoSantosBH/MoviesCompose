package br.com.compose.compose_movies_udemy.util

class NetworkUtils {
    companion object {
        const val BASE_URL = "https://api.themoviedb.org"
        const val MOVIES_POPULAR = "/3/movie/popular"
        const val ENGLISH_LANGUAGE = "en-US"
        const val PORTUGUESE_LANGUAGE = "pt-BR"
        const val DEFAULT_NUMBER_PAGES = 1
        const val PATH_PREFIX_URL = "https://image.tmdb.org/t/p/w300/"
    }
}