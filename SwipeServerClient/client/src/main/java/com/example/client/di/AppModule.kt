package com.example.client.di

import android.content.Context
import com.example.client.data.local.SharedPreferencesManager
import com.example.client.data.network.WebSocketClient
import com.example.client.data.repository.ConfigRepositoryImpl
import com.example.client.domain.repository.ConfigRepository
import com.example.client.domain.usecase.GetConfigUseCase
import com.example.client.domain.usecase.SaveConfigUseCase
import com.example.client.service.AccessibilityPermissionManager
import com.example.client.service.GestureManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferencesManager(@ApplicationContext context: Context): SharedPreferencesManager {
        return SharedPreferencesManager(context)
    }

    @Provides
    @Singleton
    fun provideConfigRepository(sharedPreferencesManager: SharedPreferencesManager): ConfigRepository {
        return ConfigRepositoryImpl(sharedPreferencesManager)
    }

    @Provides
    @Singleton
    fun provideSaveConfigUseCase(configRepository: ConfigRepository): SaveConfigUseCase {
        return SaveConfigUseCase(configRepository)
    }

    @Provides
    @Singleton
    fun provideGetConfigUseCase(configRepository: ConfigRepository): GetConfigUseCase {
        return GetConfigUseCase(configRepository)
    }

    @Provides
    @Singleton
    fun provideGestureManager(): GestureManager {
        return GestureManager()
    }

    @Provides
    @Singleton
    fun provideWebSocketClient(gestureManager: GestureManager): WebSocketClient {
        return WebSocketClient(gestureManager)
    }

    @Provides
    @Singleton
    fun provideAccessibilityPermissionManager(@ApplicationContext context: Context): AccessibilityPermissionManager {
        return AccessibilityPermissionManager(context)
    }
}
