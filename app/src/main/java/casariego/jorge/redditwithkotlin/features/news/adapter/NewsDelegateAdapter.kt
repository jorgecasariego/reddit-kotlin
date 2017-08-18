package casariego.jorge.redditwithkotlin.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import casariego.jorge.redditwithkotlin.R
import casariego.jorge.redditwithkotlin.commons.RedditNewsItem
import casariego.jorge.redditwithkotlin.commons.adapter.ViewType
import casariego.jorge.redditwithkotlin.commons.adapter.ViewTypeDelegateAdapter
import casariego.jorge.redditwithkotlin.commons.extensions.getFriendlyTime
import casariego.jorge.redditwithkotlin.commons.extensions.inflate
import casariego.jorge.redditwithkotlin.commons.extensions.loadImg
import kotlinx.android.synthetic.main.news_item.view.*

/**
 * Created by jorgecasariego on 16/8/17.
 */
class NewsDelegateAdapter: ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    inner class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.news_item)) {

        fun bind(item: RedditNewsItem) = with(itemView) {
            img_thumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }

}