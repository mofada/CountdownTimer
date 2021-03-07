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
package com.example.androiddevchallenge.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.MyTheme

/**
 * current: 当前时间
 * count: 总时间
 */
@Composable
fun TimerBackground(current: Int, count: Int) {
    // 高度比例
    val heightScale by mutableStateOf(current / count.toFloat())
    // 动画
    val animatorValue by animateFloatAsState(
        heightScale,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )

    BoxWithConstraints(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        // 背景色, Box无法实现? 同时设置背景和高度无效?
        // Modifier.height(this.maxHeight * animatorValue).background(MaterialTheme.colors.primary)
        Scaffold(
            modifier = Modifier
                .height(this.maxHeight * animatorValue),
            backgroundColor = MaterialTheme.colors.primary,
            content = {
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBackground() {
    MyTheme() {
        TimerBackground(20, 30)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBackgroundDark() {
    MyTheme(darkTheme = true) {
        TimerBackground(10, 30)
    }
}
