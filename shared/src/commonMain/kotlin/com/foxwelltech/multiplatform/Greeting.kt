package com.foxwelltech.multiplatform

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.rememberImagePainter
import multiplatform.shared.generated.resources.Res
import multiplatform.shared.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@Composable
fun App() {

    var visibleState by remember {
        mutableStateOf(false)
    }


    MaterialTheme {
        Column(Modifier.fillMaxSize().background(Color.White)) {
            Button(onClick = {
                visibleState = !visibleState
            }) {
                Text("显示/隐藏图片")
            }

            AnimatedVisibility(visible = visibleState) {
                Image(
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = ""
                )
            }

            LoadSpaceXData()

        }
    }
}

@OptIn(KoinExperimentalAPI::class)
@Composable
private fun LoadSpaceXData() {
    val vm = koinViewModel<RocketLaunchViewModel>()

    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(vm.state.value.launches) {
            Column {
                Row(
                    Modifier.fillMaxWidth().heightIn(60.dp).padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ImageHeaderComponent(modifier = Modifier.size(50.dp), it.links.patch?.small)
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text(it.missionName)
                        Text(it.details ?: "", maxLines = 2, overflow = TextOverflow.Ellipsis)
                    }
                }
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 0.5.dp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Composable
private fun ImageHeaderComponent(modifier: Modifier = Modifier, url: String?) {
    if (url.isNullOrEmpty()) {
        return
    }
    val painter = rememberImagePainter(url = url)
    Image(painter = painter, contentDescription = null, modifier = modifier)
}


class Greeting {
    private val platform: Platform = getPlatform()

    fun greet(): String {

        return "Hello, ${platform.name}!"
    }
}