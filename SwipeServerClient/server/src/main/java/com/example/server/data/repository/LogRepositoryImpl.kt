package com.example.server.data.repository

import android.content.Context
import android.util.Log
import com.example.server.R
import com.example.server.data.local.LogDao
import com.example.server.data.mapper.toDomainModel
import com.example.server.data.mapper.toEntity
import com.example.server.domain.model.LogEntry
import com.example.server.domain.repository.LogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Реализация репозитория для работы с логами.
 *
 * @param logDao DAO для работы с базой данных логов.
 * @param context Контекст приложения.
 */
class LogRepositoryImpl @Inject constructor(
    private val logDao: LogDao,
    private val context: Context
) : LogRepository {

    /**
     * Сохраняет лог в базу данных.
     *
     * @param logEntry Лог, который нужно сохранить.
     */
    override suspend fun saveLog(logEntry: LogEntry) {
        try {
            withContext(Dispatchers.IO) {
                logDao.insert(logEntry.toEntity())
            }
        } catch (e: Exception) {
            handleError(e, context.getString(R.string.error_saving_log))
        }
    }

    /**
     * Получает все логи из базы данных.
     *
     * @return Список логов.
     */
    override suspend fun getLogs(): List<LogEntry> {
        return try {
            withContext(Dispatchers.IO) {
                logDao.getAllLogs().map { it.toDomainModel() }
            }
        } catch (e: Exception) {
            handleError(e, context.getString(R.string.error_fetching_logs))
            emptyList()
        }
    }

    /**
     * Удаляет все логи.
     */
    override suspend fun deleteAllLogs() {
        try {
            withContext(Dispatchers.IO) {
                logDao.deleteAll()
            }
        } catch (e: Exception) {
            handleError(e, context.getString(R.string.error_deleting_logs))
        }
    }

    /**
     * Обрабатывает ошибки.
     *
     * @param e Исключение.
     * @param message Сообщение об ошибке.
     */
    private fun handleError(e: Exception, message: String) {
        Log.e("LogRepositoryImpl", "$message: ${e.message}")
    }
}
