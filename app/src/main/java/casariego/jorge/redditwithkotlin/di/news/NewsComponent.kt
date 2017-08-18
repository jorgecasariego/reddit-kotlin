package casariego.jorge.redditwithkotlin.di.news

import casariego.jorge.redditwithkotlin.di.AppModule
import casariego.jorge.redditwithkotlin.di.NetworkModule
import casariego.jorge.redditwithkotlin.features.news.NewsFragment
import dagger.Component
import javax.inject.Singleton

/**
 * This component will be the bridge between the modules and injections
 *
 * Created by jorgecasariego on 18/8/17.
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        NewsModule::class,
        NetworkModule::class)
)
interface NewsComponent {

    fun inject(newsFragment: NewsFragment)
}