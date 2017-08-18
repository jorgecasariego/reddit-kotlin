package casariego.jorge.redditwithkotlin.di

import android.app.Application
import android.content.Context
import casariego.jorge.redditwithkotlin.KotlinApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Provides the Context and the instance to KedditApp application.
 *
 * Created by jorgecasariego on 18/8/17.
 */
@Module
class AppModule(val app: KotlinApplication) {

    @Provides
    @Singleton
    fun provideContext() : Context = app

    @Provides
    @Singleton
    fun provideApplication() : Application = app
}