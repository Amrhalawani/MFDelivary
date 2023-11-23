package com.aks.shagra.data.db

import androidx.room.*
import com.aks.shagra.data.models.RequestEntity


@Dao
interface RequestsDBDao {

    @Query("SELECT * FROM requests")
    suspend fun getAllRequests(): List<RequestEntity>

    @Insert
    suspend fun insertRequest(request: RequestEntity)

    @Delete
    suspend fun deleteRequest(request: RequestEntity)


}