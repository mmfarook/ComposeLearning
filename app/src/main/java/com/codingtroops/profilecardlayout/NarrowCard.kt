package com.codingtroops.profilecardlayout

import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.dp

@Composable
fun TileRow() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.weight(1f, fill = true).padding(10.dp)) {
            TestTile()
        }

        Box(modifier = Modifier.weight(1f, fill = true).padding(10.dp)) {
            TestTile()
        }
    }
}

@Composable
fun TestTile (){
    Surface(
        color = Color.Red,
        modifier = Modifier
            .layout { measurable, constraints ->
                val placeable = measurable.measure((constraints))
                //placeable.height = placeable.width // can't resize. height has a private setter
                layout(placeable.width, placeable.width) {
                    placeable.place(x = 0, y = 0, zIndex = 0f)
                }
            }.fillMaxSize() // fills the width, but not height, likely due to above layout
    ){
        Column {
            Text(text = "item1")
            Text(text = "item2 to fill",
                modifier = Modifier
                    .weight(1f)) // this is gone when weight is added
            Text(text = "item3")
        }
    }
}
