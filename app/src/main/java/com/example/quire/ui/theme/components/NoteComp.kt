package com.example.quire.ui.theme.components


import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quire.R
import com.example.quire.dataBase.UserRepository
import com.example.quire.dataBase.note.Note



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteComp(navController: NavController, note: Note, userRepository: UserRepository) {

	Scaffold(
		topBar = {
			TopAppBar(
				title = { Text(note.title) },
				navigationIcon = {
					IconButton(onClick = { navController.popBackStack() }) {
						Icon(Icons.Default.ArrowBack, contentDescription = "Back")
					}
				}
			)
		}
	) {
		Surface(color = backgroundColor) {
			EditNote(userRepository)
		}
	}
}