package casariego.jorge.redditwithkotlin.di.news

import casariego.jorge.redditwithkotlin.api.NewsAPI
import casariego.jorge.redditwithkotlin.api.NewsRestAPI
import casariego.jorge.redditwithkotlin.api.RedditApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by jorgecasariego on 18/8/17.
 */
@Module
class NewsModule {

    @Provides
    @Singleton
    fun provideNewsApi(redditApi: RedditApi): NewsAPI = NewsRestAPI(redditApi)

    @Provides
    @Singleton
    fun provideRedditApi(retrofit: Retrofit): RedditApi = retrofit.create(RedditApi::class.java)

    /**
     * NewsManager is automatically provided by Dagger as we set the @Inject annotation in the
     * constructor, so we can avoid adding a 'provider method' here.
     */
}