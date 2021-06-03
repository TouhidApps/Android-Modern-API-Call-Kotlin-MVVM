package com.touhidapps.modernApi.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class) // This object will alive as long as implemented activity
object MainActivityModule {

    @ActivityScoped
    @Provides
    @Named("my_string_2")
    fun provideMyAnotherString(
        @ApplicationContext context: Context,
        @Named("my_string") string: String
    ) = string

}


