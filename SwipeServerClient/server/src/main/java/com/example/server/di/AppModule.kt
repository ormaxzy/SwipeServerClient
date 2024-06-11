package com.example.server.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.server.data.local.AppDatabase
import com.example.server.data.local.LogDao
import com.example.server.data.network.WebSocketServer
import com.example.server.data.network.ClientHandler
import com.example.server.data.network.GesturesList
import com.example.server.data.repository.ConfigRepositoryImpl
import com.example.server.data.repository.LogRepositoryImpl
import com.example.server.domain.repository.ConfigRepository
import com.example.server.domain.repository.LogRepository
import com.example.server.domain.usecase.*
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
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "server-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideLogDao(appDatabase: AppDatabase): LogDao {
        return appDatabase.logDao()
    }

    @Provides
    @Singleton
    fun provideLogRepository(@ApplicationContext context: Context, logDao: LogDao): LogRepository {
        return LogRepositoryImpl(
            logDao,
            context
        )
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("server_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideConfigRepository(sharedPreferences: SharedPreferences): ConfigRepository {
        return ConfigRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideGetLogsUseCase(logRepository: LogRepository): GetLogsUseCase {
        return GetLogsUseCase(logRepository)
    }

    @Provides
    @Singleton
    fun provideSaveLogUseCase(logRepository: LogRepository): SaveLogUseCase {
        return SaveLogUseCase(logRepository)
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
    fun provideDeleteAllLogsUseCase(logRepository: LogRepository): DeleteAllLogsUseCase {
        return DeleteAllLogsUseCase(logRepository)
    }

    @Provides
    @Singleton
    fun provideWebSocketServer(
        clientHandler: ClientHandler,
        gesturesList: GesturesList,
        saveLogUseCase: SaveLogUseCase
    ): WebSocketServer {
        return WebSocketServer(clientHandler, gesturesList, saveLogUseCase)
    }

    @Provides
    @Singleton
    fun provideClientHandler(): ClientHandler {
        return ClientHandler()
    }

    @Provides
    @Singleton
    fun provideGesturesList(): GesturesList {
        return GesturesList()
    }
}
