package casariego.jorge.redditwithkotlin.commons

import android.os.Parcel
import android.os.Parcelable
import android.provider.MediaStore
import casariego.jorge.redditwithkotlin.commons.adapter.AdapterConstants
import casariego.jorge.redditwithkotlin.commons.adapter.ViewType

/**
 * Created by jorgecasariego on 16/8/17.
 *
 * 1. Companion object example
 *      data class RedditNews(...)
 *            companion object {
 *                val ENDPOINT = "http://kotlinlang.org"
 *            }
 *        }
 *
 *      Use it:
 *        RedditNews.ENDPOINT
 *
 */
data class RedditNews(
        val after: String,
        val before: String,
        val news: List<RedditNewsItem>) : Parcelable {

    /**
     * 1. Companion object
     * -------------------
     * In Kotlin there is no static keyword to create a static member for a class but exists
     * something called companion objects which allows you to provide the same behavior.
     *
     * When using this keyword we are declaring an object (an instance) directly in the code and all
     * the properties and method inside companion object can be called directly by using the class name
     */
    companion object {
        @Suppress("unused")
        /**
         * 2. @JvmField Annotation
         * -----------------------
         * Classes implementing the Parcelable interface must also have a non-null static field
         * called CREATOR of a type that implements the Parcelable.Creator interface.
         * In order to make our CREATOR implementation visible as a field in Java we need this
         * special annotation called “@JvmField”, otherwise it will not be found and in the middle
         * of the process it will throw an exception.
         */
        @JvmField val CREATOR: Parcelable.Creator<RedditNews> = object : Parcelable.Creator<RedditNews> {
            override fun createFromParcel(source: Parcel): RedditNews = RedditNews(source)
            override fun newArray(size: Int): Array<RedditNews?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.createTypedArrayList(RedditNewsItem.CREATOR))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(after)
        dest?.writeString(before)
        dest?.writeTypedList(news)
    }
}

data class RedditNewsItem(
        val author: String,
        val title: String,
        val numComments: Int,
        val created: Long,
        val thumbnail: String,
        val url: String
): ViewType, Parcelable {

    override fun getViewType() = AdapterConstants.NEWS

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<RedditNewsItem> = object : Parcelable.Creator<RedditNewsItem> {
            override fun createFromParcel(source: Parcel): RedditNewsItem = RedditNewsItem(source)
            override fun newArray(size: Int): Array<RedditNewsItem?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            source.readString(),
            source.readString(),
            source.readInt(),
            source.readLong(),
            source.readString(),
            source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(author)
        dest?.writeString(title)
        dest?.writeInt(numComments)
        dest?.writeLong(created)
        dest?.writeString(thumbnail)
        dest?.writeString(url)
    }
}