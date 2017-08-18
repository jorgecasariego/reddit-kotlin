package casariego.jorge.redditwithkotlin.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject

/**
 * Created by jorgecasariego on 16/8/17.
 */
class NewsRestAPI @Inject constructor(private val redditApi: RedditApi) : NewsAPI {

    override fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }
}