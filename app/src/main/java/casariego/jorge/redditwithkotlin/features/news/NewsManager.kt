package casariego.jorge.redditwithkotlin.features.news

import casariego.jorge.redditwithkotlin.api.NewsAPI
import casariego.jorge.redditwithkotlin.api.NewsRestAPI
import casariego.jorge.redditwithkotlin.commons.RedditNews
import casariego.jorge.redditwithkotlin.commons.RedditNewsItem
import rx.Observable


/**
 * Created by jorgecasariego on 16/8/17.
 */
class NewsManager(private val api: NewsAPI = NewsRestAPI()) {

    /**
    *
    * Returns Reddit News paginated by the given limit.
    *
    * @param after indicates the next page to navigate.
    * @param limit the number of news to request.
    */
    fun getNews(after: String, limit: String = "10"): Observable<RedditNews> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getNews(after, limit)
            val response = callResponse.execute() // This will execute the request synchronously.

            if (response.isSuccessful) {
                val dataResponse = response.body().data
                // Here we use the List function “map” to transform every children into a RedditNewsItem.
                val news = dataResponse.children.map {
                    // it” is a short way to access a single parameter from the lambda expression.
                    // This is only valid when you receive just one parameter, otherwise for more
                    // parameters you have to specify it, for example with two parameters it looks
                    // like this “x, y -> …”.
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments,
                            item.created, item.thumbnail, item.url)
                }

                val redditNews = RedditNews(
                        dataResponse.after ?: "",
                        dataResponse.before ?: "",
                        news
                )
                subscriber.onNext(redditNews)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}