//package br.com.compose.compose_movies_udemy.ui.theme
//
//import br.com.compose.compose_movies_udemy.ui.theme.Avatar.Companion.DUMB_AVATAR
//import br.com.compose.compose_movies_udemy.ui.theme.Gravatar.Companion.DUMB_GRAVATAR
//import br.com.compose.compose_movies_udemy.ui.theme.Tmdb.Companion.DUMB_TMBD
//
//
////import com.google.gson.annotations.SerializedName
//
//data class DetailModel(
////    @SerializedName("avatar")
//    val avatar: Avatar?,
////    @SerializedName("id")
//    val id: Int?,
////    @SerializedName("include_adult")
//    val includeAdult: Boolean?,
////    @SerializedName("iso_3166_1")
//    val iso31661: String?,
////    @SerializedName("iso_639_1")
//    val iso6391: String?,
////    @SerializedName("name")
//    val name: String?,
////    @SerializedName("username")
//    val username: String?
//) {
//
//    companion object {
//        val DUMB_DETAIL = DetailModel(
//            avatar = DUMB_AVATAR,
//            id = 548,
//            includeAdult = false,
//            iso31661 = "CA",
//            iso6391 = "en",
//            name = "Travis Bell",
//            username = "travisbell",
//        )
//    }
//
//}
//
//data class Avatar(
////        @SerializedName("gravatar")
//    val gravatar: Gravatar?,
////        @SerializedName("tmdb")
//    val tmdb: Tmdb?
//) {
//    companion object {
//        val DUMB_AVATAR = Avatar(
//            gravatar = DUMB_GRAVATAR,
//            tmdb = DUMB_TMBD
//        )
//    }
//}
//
//data class Gravatar(
////            @SerializedName("hash")
//    val hash: String?
//) {
//    companion object {
//        val DUMB_GRAVATAR = Gravatar(
//            hash = "c9e9fc152ee756a900db85757c29815d"
//        )
//    }
//}
//
//data class Tmdb(
////            @SerializedName("avatar_path")
//    val avatarPath: String?
//) {
//    companion object {
//        val DUMB_TMBD = Tmdb(
//            avatarPath = "/xy44UvpbTgzs9kWmp4C3fEaCl5h.png"
//        )
//    }
//}