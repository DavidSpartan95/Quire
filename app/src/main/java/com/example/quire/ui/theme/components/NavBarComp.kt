import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quire.R
import com.example.quire.ui.theme.blueColor

@Composable
fun NavBarComp(task: () -> Unit, favorite: () -> Unit, folder: () -> Unit) {
	var selectedTabIndex by remember { mutableStateOf(0) }

	Box {
		BottomNavigation(
			modifier = Modifier.fillMaxWidth()
				.align(Alignment.BottomCenter),
			backgroundColor = Color.White
		) {
			BottomNavigationItem(
				onClick = {
					selectedTabIndex = 0
					task.invoke()
				},
				selected = selectedTabIndex == 0,
				icon = {
					val iconTint = if (selectedTabIndex == 0) blueColor else Color.Gray
					Icon(
						painter = painterResource(id = R.drawable.icon_notes),
						contentDescription = "Notes",
						tint = iconTint,
						modifier = Modifier.size(24.dp)

					)
				},
				label = {
					Text(
						text = "Notes",
						color = if (selectedTabIndex == 0) blueColor else Color.Gray
					)
				}
			)

			BottomNavigationItem(
				onClick = {
					selectedTabIndex = 1
					favorite.invoke()
				},
				selected = selectedTabIndex == 1,
				icon = {
					Icon(
						Icons.Filled.Favorite,
						contentDescription = "Favorites",
						tint = if (selectedTabIndex == 1) blueColor else Color.Gray
					)
				},
				label = {
					Text(
						text = "Favorites",
						color = if (selectedTabIndex == 1) blueColor else Color.Gray
					)
				}
			)

			BottomNavigationItem(
				onClick = {
					selectedTabIndex = 2
					folder.invoke()
				},
				selected = selectedTabIndex == 2,
				icon = {
					val iconTint = if (selectedTabIndex == 2) blueColor else Color.Gray
					Icon(
						painter = painterResource(id = R.drawable.icon_folder),
						contentDescription = "Folders",
						tint = iconTint,
						modifier = Modifier.size(26.dp)
					)
				},
				label = {
					Text(
						text = "Folders",

						color = if (selectedTabIndex == 2) blueColor else Color.Gray
					)
				}
			)
		}
	}
}