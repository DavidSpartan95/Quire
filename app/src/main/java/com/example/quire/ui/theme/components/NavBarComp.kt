import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun NavBarComp(navigate:() -> Unit) {
	Box() {
		BottomNavigation(
			modifier = Modifier.fillMaxWidth()
				.align(Alignment.BottomCenter),
			backgroundColor = Color.White
		) {
			BottomNavigationItem(
				onClick = { navigate.invoke() },
				selected = false,
				icon = { Icon(Icons.Filled.Home, contentDescription = "Button 1") },
				label = { Text("Button 1") }
			)

			BottomNavigationItem(
				onClick = { navigate.invoke() },
				selected = false,
				icon = { Icon(Icons.Default.Favorite, contentDescription = "Favorite") },
				label = { Text("Favorite") }
			)

			BottomNavigationItem(
				onClick = { navigate.invoke() },
				selected = false,
				icon = { Icon(Icons.Filled.Person, contentDescription = "Button 3") },
				label = { Text("Button 3") }
			)
		}
	}
}
