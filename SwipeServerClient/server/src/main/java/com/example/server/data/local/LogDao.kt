package com.example.server.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface LogDao {

    /**
     * Вставляет новый лог в базу данных.
     *
     * @param log Лог, который нужно вставить.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(log: LogEntity)

    /**
     * Получает все логи из базы данных.
     *
     * @return Список всех логов.
     */
    @Query("SELECT * FROM logs")
    suspend fun getAllLogs(): List<LogEntity>

    /**
     * Удаляет все логи из базы данных.
     */
    @Query("DELETE FROM logs")
    suspend fun deleteAll()

}
