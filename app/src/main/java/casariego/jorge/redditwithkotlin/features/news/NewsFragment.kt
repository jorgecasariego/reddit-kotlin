package casariego.jorge.redditwithkotlin.features.news


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import casariego.jorge.redditwithkotlin.R
import casariego.jorge.redditwithkotlin.commons.InfiniteScrollListener
import casariego.jorge.redditwithkotlin.commons.RedditNews
import casariego.jorge.redditwithkotlin.commons.RedditNewsItem
import casariego.jorge.redditwithkotlin.commons.RxBaseFragment
import casariego.jorge.redditwithkotlin.commons.extensions.inflate
import casariego.jorge.redditwithkotlin.features.news.adapter.NewsAdapter

import kotlinx.android.synthetic.main.fragment_news.*;
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : RxBaseFragment() {

    private val newsList by lazy {
        news_list
    }

    companion object {
        private val KEY_REDDIT_NEWS = "redditNews"
    }

    private var redditNews: RedditNews? = null

    // This newsManager will be initialized with the NewsManager() only the first time that we use newsManagers field.
    private val newsManager by lazy { NewsManager() }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return container?.inflate(R.layout.fragment_news)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newsList.apply {
            setHasFixedSize(true)

            var linearLayout  = LinearLayoutManager(context)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({requestNews()}, linearLayout))
        }

        initAdapter()

        if(savedInstanceState != null && savedInstanceState.containsKey(KEY_REDDIT_NEWS)){
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (newsList.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (newsList.adapter as NewsAdapter).getNews()

        if(redditNews != null && news.size > 0){
            outState.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }

    private fun requestNews() {
        /**
        * first time will send empty string for after parameter.
        * Next time we will have redditNews set with the next page to
        * navigate with the after param.
        */
        val subscription = newsManager.getNews(redditNews?.after ?: "")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            retrieveNews ->
                            redditNews = retrieveNews
                            (newsList.adapter as NewsAdapter).addNews(retrieveNews.news)
                        },
                        {
                            e -> Snackbar.make(newsList, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )

        subscriptions.add(subscription)
    }

    private fun initAdapter() {
        if(newsList.adapter == null) {
            newsList.adapter = NewsAdapter()
        }
    }

}
