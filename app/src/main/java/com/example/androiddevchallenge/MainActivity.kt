/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.robotoMonoFamily

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyTheme {
                HomePage()
            }
        }
    }
}

@Composable
fun HomePage() {
    val viewModel: MainViewModel = viewModel()
    //总时间
    val count = 30
    //高度比例
    var heightScale by remember { mutableStateOf(viewModel.current / count.toFloat()) }
    //动画
    val animatorValue by animateFloatAsState(
        heightScale,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )

    BoxWithConstraints(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        //背景色, Box无法实现? 同时设置背景和高度无效?
        //Modifier.height(this.maxHeight * animatorValue).background(MaterialTheme.colors.primary)
        Scaffold(
            modifier = Modifier
                .height(this.maxHeight * animatorValue),
            backgroundColor = MaterialTheme.colors.primary,
            content = {
            }
        )



        //前景文字
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = "${viewModel.current}",
                color = MaterialTheme.colors.onPrimary,
                fontSize = 100.sp,
                fontFamily = robotoMonoFamily,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .clickable {
                        heightScale -= 1f / count
                        viewModel.current -= 1
                    }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    MyTheme(darkTheme = false) {
        HomePage()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeDark() {
    MyTheme(darkTheme = true) {
        HomePage()
    }
}
