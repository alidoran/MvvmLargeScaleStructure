package ir.dorantech.mvvmlarge.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ir.dorantech.mvvmlarge.ui.theme.MvvmLargeScaleStructureTheme

@Composable
fun MainScreen(
    onClickRetrofit: () -> Unit
) {
    MvvmLargeScaleStructureTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = onClickRetrofit) { Text(text = "Retrofit") }
        }
    }
}

@Composable
@Preview
fun MainScreenPreview(){
    MainScreen {  }
}
