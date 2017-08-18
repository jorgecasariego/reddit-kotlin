package casariego.jorge.redditwithkotlin.api

/**
 * Created by jorgecasariego on 16/8/17.
 */
class RedditNewsResponse(val data: RedditDataResponse)

/**
 * - children: Is the news’ list paginated by 10 items and each children has details about the news
 *  (name, image, author, etc)
 *  - after: It allows you to perform pagination with this Reddit API. “after” will bring you the next 10 items
 *  - before: The same as “after” but to navigate back.
 */
class RedditDataResponse(
        val children: List<RedditChildrenResponse>,
        val after: String?,
        val before: String?
)

class RedditChildrenResponse(val data: RedditNewsDataResponse)

class RedditNewsDataResponse(
        val author: String,
        val title: String,
        val num_comments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
)