import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NavBarComp(task:() -> Unit,favorite:() -> Unit,folder:() -> Unit ) {
	Box() {
		BottomNavigation(
			modifier = Modifier.fillMaxWidth()
				.align(Alignment.BottomCenter),
			backgroundColor = Color.White
		) {
			BottomNavigationItem(
				onClick = { task.invoke() },
				selected = false,
				icon = { Icon(Icons.Filled.Home, contentDescription = "Button 1") },
				label = { Text("Button 1") }
			)

			BottomNavigationItem(
				onClick = { favorite.invoke() },
				selected = false,
				icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorite") },
				label = { Text("Favorite") }
			)

			BottomNavigationItem(
				onClick = { folder.invoke() },
				selected = false,
				icon = { Icon(Icons.Filled.Person, contentDescription = "Button 3") },
				label = { Text("Button 3") }
			)
		}
	}
}