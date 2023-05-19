package com.example.quire.ui.theme.components.notes


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note
import com.example.quire.ui.theme.blueColor
import com.example.quire.ui.theme.components.DeleteNoteAlertDialog
import com.example.quire.utilities.setFavorite


@Composable
fun NoteItem(
    userRepository: UserRepository,
    i: Int,
    note: Note,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp,
    cutCornerRadius: Dp = 30.dp,
    onDeleteClick:() -> Unit,
    update:() -> Unit,
    ) {

    var showDialog by remember { mutableStateOf(false) }
    DeleteNoteAlertDialog(showDialog, { showDialog = it }, onDeleteClick)

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
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = note.content,
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onSurface,
                maxLines = 8,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)) {
                        append(note.date!!)
                    }
                },
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        IconButton(
            onClick = {
              setFavorite(userRepository = userRepository, index = i, mainTread = update)
                println(note.favorite)
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 75.dp)

        ) {
            Image(
                painter = if (note.favorite) painterResource(id = R.drawable.baseline_favorite_24)
                else painterResource(id = R.drawable.baseline_favorite_border_24),
                contentDescription = "Favorite Icon",
                modifier = Modifier.size(32.dp)
            )
        }


        IconButton(onClick = { showDialog = true },
            modifier = Modifier.align(Alignment.BottomEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete note.",
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier.size(32.dp)
            )
        }


    }
}