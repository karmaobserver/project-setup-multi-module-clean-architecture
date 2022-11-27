package com.aleksejmakaji.data.repository.source

import com.aleksejmakaji.data.mapper.UserMapper
import com.aleksejmakaji.data.room.dao.UserDao
import com.aleksejmakaji.data.room.entity.UserEntity
import com.aleksejmakaji.domain.error.DatabaseError
import com.aleksejmakaji.domain.model.UserModel
import com.aleksejmakaji.domain.wrapper.ErrorState
import com.aleksejmakaji.domain.wrapper.State
import com.aleksejmakaji.domain.wrapper.SuccessState
import com.aleksejmakaji.domain.wrapper.suspendResultWithStateCatchingDatabaseError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.mapNotNull

class UserLocalDataSource(
    private val userDao: UserDao,
    private val userMapper: UserMapper
) {
    suspend fun save(userModel: UserModel): State<Long> {
        return suspendResultWithStateCatchingDatabaseError {
            userDao.save(userMapper.mapToEntity(userModel))
        }
    }

    fun getUser(): Flow<State<UserModel?>> {
        return userDao.get().mapNotNull<UserEntity?, State<UserModel?>> {
            it?.let { SuccessState(userMapper.mapFromEntity(it)) } ?: SuccessState(null)
        }.catch {
            emit(ErrorState(DatabaseError(it)))
        }
    }
}
