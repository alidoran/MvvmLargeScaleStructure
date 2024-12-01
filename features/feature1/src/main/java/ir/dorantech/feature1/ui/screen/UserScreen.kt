package ir.dorantech.feature1.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import ir.dorantech.domain.model.User
import ir.dorantech.ui.state.UIState
import ir.dorantech.feature1.viewmodel.UserViewModel

@Composable
fun UserScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: UserViewModel = hiltViewModel()
    val userState by viewModel.userState.collectAsState()

    UserScreenContent(
        onFetchUser = { userId ->
            viewModel.fetchUser(userId)
        },
        userState,
        modifier,
    )
}

@Composable
fun UserScreenContent(
    onFetchUser: (String) -> Unit,
    userState: UIState<User>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var userId by remember { mutableStateOf("0") }
        TextField(
            modifier = modifier,
            value = userId,
            onValueChange = {
                userId = it
            })
        Button(
            onClick = {
                onFetchUser(userId)
            }
        ) {
            Text("Fetch User")
        }
    }

    when (userState) {
        UIState.Loading -> Text("Loading...")
        is UIState.Success -> {
            val user = userState.data
            Column {
                Text("Welcome, ${user.name}!")
                Text("Email: ${user.email}")
            }
        }

        is UIState.Error -> Text("Error: ${userState.message}")
        UIState.Idle -> {}
    }
}

@Composable
fun UserScreenPreviewContent(
    userState: UIState<User> =
        UIState.Success(User("1", "Ali Doran", "alidoran@gmail.com")),
    modifier: Modifier = Modifier,
) {
    UserScreenContent(
        userState = userState,
        onFetchUser = {},
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun UserScreenPreview() {
    UserScreenPreviewContent()
}
