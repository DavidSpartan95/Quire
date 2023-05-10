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
import com.example.quire.dataBase.note.Note



@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteComp(navController: NavController, note: Note) {

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
			val context = LocalContext.current

			val imageTop = painterResource(R.drawable.moln)
			val imageMiddle = painterResource(R.drawable.icon)
			//val hasSeenIntro = AppPreferences.hasSeenIntro(context)

			//if (!hasSeenIntro) {
			//if (hasSeenIntro) {

			//  IntroductionDialog(onDismiss = { AppPreferences.setHasSeenIntro(context, true) })
			//}

			Image(
				painter = imageTop,
				contentDescription = "Moln",
				modifier = Modifier
					.fillMaxWidth()
					.height(12.dp)
			)

			Spacer(modifier = Modifier.height(20.dp))

			Image(
				painter = imageMiddle,
				contentDescription = "Icon",
				modifier = Modifier
					.fillMaxWidth()
					.height(280.dp)
			)

			Column(
				modifier = Modifier
					.fillMaxSize(),
				horizontalAlignment = Alignment.CenterHorizontally
			) {



				Spacer(modifier = Modifier.height(30.dp))

				Text(
					text = "Quire",
					color = blueColor,
					fontWeight = FontWeight.Bold,
					fontSize = 45.sp
				)

				Spacer(modifier = Modifier.height(25.dp))

				Text(text = "Make notes with Quire app",
					fontWeight = FontWeight.Bold)

			}
		}
	}
}