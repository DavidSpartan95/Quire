package com.example.quire.ui.theme.components


import androidx.compose.foundation.Canvas

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import com.example.quire.R
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.quire.dataBase.note.Note
import kotlin.random.Random


@Composable
fun NoteItem(
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerRadius: Dp = 30.dp,
    onDeleteClick:() -> Unit,
    //onItemClick:() -> Unit,
    //navController: (String) -> Unit


    ) {

    var isFavorite by remember { mutableStateOf(false) }
    val blueColor = Color(0xFF4ECCD3)


    /*fun randomColor(): Color {
        val random = Random.Default
        return Color(
            red = random.nextFloat(),
            green = random.nextFloat(),
            blue = random.nextFloat(),
            alpha = 1f
        )
    }
     */

    Box(modifier = modifier){

        Canvas(modifier = Modifier.matchParentSize()){

            val clipPath = Path().apply {
                lineTo(size.width - cutCornerRadius.toPx(), 0f)
                lineTo(size.width, cutCornerRadius.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }
            clipPath(clipPath){
                drawRoundRect(
                    color = Color.White,
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
                drawRoundRect(
                    color = blueColor,
                    //color = randomColor(),
                    topLeft = Offset(size.width - cutCornerRadius.toPx(), -100f),
                    size = Size(cutCornerRadius.toPx() + 100f, cutCornerRadius.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(text = note.title,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 8,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(text = note.date!!,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onSurface,
                maxLines = 8,
                overflow = TextOverflow.Ellipsis)
        }

        IconButton(
            onClick = { isFavorite = !isFavorite },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 25.dp)

        ) {
            Image(
                painter = if (isFavorite) painterResource(id = R.drawable.baseline_favorite_24)
                else painterResource(id = R.drawable.baseline_favorite_border_24),
                contentDescription = "Favorite Icon",
                modifier = Modifier.size(34.dp)
            )
        }


        IconButton(onClick = onDeleteClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete note.",
                tint = MaterialTheme.colors.onSurface
            )
        }


    }
}