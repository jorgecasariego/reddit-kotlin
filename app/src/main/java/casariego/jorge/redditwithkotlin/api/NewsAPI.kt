package casariego.jorge.redditwithkotlin.api

import retrofit2.Call

/**
 * Created by jorgecasariego on 17/8/17.
 */
interface NewsAPI {
    fun getNews(after: String, limit: String): Call<RedditNewsResponse>
}