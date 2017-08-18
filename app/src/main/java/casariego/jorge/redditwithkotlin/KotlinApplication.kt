package casariego.jorge.redditwithkotlin

import android.app.Application
import casariego.jorge.redditwithkotlin.di.AppModule
import casariego.jorge.redditwithkotlin.di.news.DaggerNewsComponent
import casariego.jorge.redditwithkotlin.di.news.NewsComponent

/**
 * Created by jorgecasariego on 18/8/17.
 */
class KotlinApplication: Application() {

    companion object {
        lateinit var newsComponent: NewsComponent
    }

    override fun onCreate() {
        super.onCreate()

        newsComponent = DaggerNewsComponent.builder()
                .appModule(AppModule(this))
                //.newsModule(NewsModule()) Module with empty constructor is implicitly created by dagger.
                .build()
    }
}
