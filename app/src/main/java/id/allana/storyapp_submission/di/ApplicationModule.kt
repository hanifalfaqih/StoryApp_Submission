package id.allana.storyapp_submission.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.allana.storyapp_submission.data.local.SessionPreference
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideSessionPreferences(
        @ApplicationContext context: Context,
        gson: Gson
    ): SessionPreference {
        return SessionPreference(context, gson)
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}