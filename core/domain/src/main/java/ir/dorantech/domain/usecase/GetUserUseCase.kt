package ir.dorantech.domain.usecase

import ir.dorantech.domain.model.DataSource
import ir.dorantech.domain.repository.UserRepository
import ir.dorantech.domain.result.DataResult
import ir.dorantech.domain.result.DataError
import ir.dorantech.domain.result.DomainError
import ir.dorantech.domain.result.DomainResult
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(
        userId: String,
        dataSource: DataSource,
    ) =
         when (val result = userRepository.getUser(userId, dataSource)) {
            is DataResult.Error -> DomainResult.Failure(errorHandler(result.dataError))
            is DataResult.Success -> DomainResult.Success(result.data)
    }

    private fun errorHandler(dataError: DataError) =
        when (dataError) {
            is DataError.Unauthorized -> DomainError.Unauthorized
            is DataError.Unknown -> DomainError.Unknown(
                dataError.exception.message ?: "Unknown error"
            )
            is DataError.NotFound,
            is DataError.SeverDown -> DomainError.TryLater
        }
}