package casariego.jorge.redditwithkotlin.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by jorgecasariego on 16/8/17.
 */
class NewsRestAPI: NewsAPI {
    private val redditApi: RedditApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.reddit.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        /**
         * This syntax allows you to get the Java Class instance corresponding to the given KClass
         * instance. A KClass is an interface that represents a class and provides introspection capabilities.
         */
        redditApi = retrofit.create(RedditApi::class.java)
    }

    override fun getNews(after: String, limit: String): Call<RedditNewsResponse> {
        return redditApi.getTop(after, limit)
    }


}