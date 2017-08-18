package casariego.jorge.redditwithkotlin.features.news.adapter

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import casariego.jorge.redditwithkotlin.R
import casariego.jorge.redditwithkotlin.commons.adapter.ViewType
import casariego.jorge.redditwithkotlin.commons.adapter.ViewTypeDelegateAdapter
import casariego.jorge.redditwithkotlin.commons.extensions.inflate

/**
 * Created by jorgecasariego on 16/8/17.
 */
class LoadingDelegateAdapter: ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(
        parent.inflate(R.layout.news_item_loading)){

    }
}