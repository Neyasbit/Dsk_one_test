package com.example.dsk_one_test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dsk_one_test.ui.theme.Dsk_one_testTheme
import com.example.feature_complexs.ui.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Dsk_one_testTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()

            }
        }
    }
}
