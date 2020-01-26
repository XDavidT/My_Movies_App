import com.android.academy.movie_model.MovieModel
import com.google.gson.annotations.SerializedName

data class MoviesResult (
	@SerializedName("popularity") val popularity : Double,
	@SerializedName("vote_count") val vote_count : Int,
	@SerializedName("video") val video : Boolean,
	@SerializedName("poster_path") val poster_path : String,
	@SerializedName("id") val id : Int,
	@SerializedName("adult") val adult : Boolean,
	@SerializedName("backdrop_path") val backdrop_path : String,
	@SerializedName("original_language") val original_language : String,
	@SerializedName("original_title") val original_title : String,
	@SerializedName("genre_ids") val genre_ids : List<Int>,
	@SerializedName("title") val title : String,
	@SerializedName("vote_average") val vote_average : Double,
	@SerializedName("overview") val overview : String,
	@SerializedName("release_date") val release_date : String
) {
	companion object{
		private const val BASE_URL_PHOTO = "https://image.tmdb.org/t/p"
		private const val BASE_W500 = "$BASE_URL_PHOTO/w500"
		private const val BASE_ORIGINAL = "$BASE_URL_PHOTO/original"
	}
	fun toMovieModel():MovieModel{
		return MovieModel(
			id,
			title,
			BASE_W500+poster_path,
			BASE_ORIGINAL+backdrop_path,
			release_date,
			"",
			overview
		)
	}
}
