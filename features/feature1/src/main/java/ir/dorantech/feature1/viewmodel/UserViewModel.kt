package ir.dorantech.feature1.viewmodel

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.dorantech.domain.model.DataSource
import ir.dorantech.domain.model.User
import ir.dorantech.domain.result.DomainError
import ir.dorantech.domain.result.DomainResult
import ir.dorantech.domain.usecase.GetUserUseCase
import ir.dorantech.ui.state.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    private val _userState = MutableStateFlow<UIState<User>>(UIState.Idle)
    val userState: StateFlow<UIState<User>> get() = _userState

    fun fetchUser(
        userId: String,
        dataSource: DataSource,
    ) {
        if (userId.isDigitsOnly()) {
            _userState.value = UIState.Loading
            viewModelScope.launch {
                val result = getUserUseCase(userId, dataSource)
                _userState.value = when (result) {
                    is DomainResult.Failure -> UIState.Error(errorHandler(result))
                    is DomainResult.Success -> UIState.Success(result.data)
                }
            }
        } else _userState.value = UIState.Error("Invalid Input")
    }

    private fun errorHandler(dataError: DomainResult.Failure) =
        when (dataError.error) {
            is DomainError.InvalidInput -> "Invalid Input"
            is DomainError.Unauthorized -> "Unauthorized"
            is DomainError.TryLater -> "Try Later"
            is DomainError.Unknown -> "Unknown Error"
        }
}