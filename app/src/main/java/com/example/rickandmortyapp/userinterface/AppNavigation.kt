package com.example.rickandmortyapp.userinterface

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.rickandmortyapp.data.Favorite
import com.example.rickandmortyapp.data.RickCharacter
import com.example.rickandmortyapp.viewmodel.AuthViewModel
import com.example.rickandmortyapp.viewmodel.CharacterViewModel
import com.example.rickandmortyapp.viewmodel.FavoriteViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import androidx.navigation.toRoute

private val PortalGreen = Color(0xFF9BEA65)
private val Background = Color(0xFF10151B)
private val CardColor = Color(0xFF202A35)

@Serializable
data object LoginRoute

@Serializable
data object RegisterRoute

@Serializable
data object HomeRoute

@Serializable
data class DetailRoute(val id: Int)

@Composable
fun AppNavigation(auth: AuthViewModel) {
    val userId by auth.userId.collectAsState()
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (userId == -1) LoginRoute else HomeRoute
    ) {
        composable<LoginRoute> {
            AuthScreen(
                auth = auth,
                isRegister = false,
                onSuccess = { navController.navigate(HomeRoute) {
                    popUpTo<LoginRoute> { inclusive = true }
                } },
                onSwitch = { navController.navigate(RegisterRoute) }
            )
        }
        composable<RegisterRoute> {
            AuthScreen(
                auth = auth,
                isRegister = true,
                onSuccess = { navController.navigate(HomeRoute) {
                    popUpTo<RegisterRoute> { inclusive = true }
                } },
                onSwitch = { navController.navigate(LoginRoute) }
            )
        }
        composable<HomeRoute> {
            MainTabs(auth, navController)
        }
        composable<DetailRoute> { entry ->
            val route = entry.toRoute<DetailRoute>()
            DetailScreen(route.id, auth, onBack = { navController.popBackStack() })
        }
    }

    LaunchedEffect(userId) {
        if (userId == -1) {
            navController.navigate(LoginRoute) {
                popUpTo(0) { inclusive = true }
                launchSingleTop = true
            }
        }
    }
}

@Composable
private fun AuthScreen(auth: AuthViewModel, isRegister: Boolean, onSuccess: () -> Unit, onSwitch: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val message by auth.message.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().background(Background).padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("🛸", style = MaterialTheme.typography.displayLarge)
        Text("Rick & Morty Explorer", style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold, color = PortalGreen)
        Spacer(Modifier.height(28.dp))
        OutlinedTextField(email, { email = it }, label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth(), singleLine = true)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(password, { password = it }, label = { Text("Senha") },
            modifier = Modifier.fillMaxWidth(), singleLine = true)
        if (message.isNotBlank()) {
            Text(message, color = Color(0xFFFF8A80), modifier = Modifier.padding(8.dp))
        }
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = {
                if (isRegister) auth.register(email, password, onSuccess)
                else auth.login(email, password, onSuccess)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = PortalGreen, contentColor = Color.Black)
        ) { Text(if (isRegister) "Criar conta" else "Entrar") }

        TextButton(onClick = { auth.clearMessage(); onSwitch() }) {
            Text(if (isRegister) "Já tenho conta" else "Criar uma conta", color = PortalGreen)
        }
        Text(
            if (isRegister) "Após criar a conta, você entrará automaticamente."
            else "Entre para explorar o multiverso.",
            color = Color.LightGray
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)private fun MainTabs(auth: AuthViewModel, navController: androidx.navigation.NavHostController) {
    val userId by auth.userId.collectAsState()
    var selected by remember { mutableIntStateOf(0) }
    val titles = listOf("Início", "Explorar", "Favoritos")

    Scaffold(
        containerColor = Background,
        topBar = {
            TopAppBar(
                title = { Text("Rick & Morty", color = PortalGreen, fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = { auth.logout() }) {
                        Icon(Icons.Default.ExitToApp, "Sair", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Background)
            )
        },
        bottomBar = {
            NavigationBar(containerColor = CardColor) {
                val icons = listOf(Icons.Default.Home, Icons.Default.Search, Icons.Default.Favorite)
                titles.forEachIndexed { index, title ->
                    NavigationBarItem(
                        selected = selected == index,
                        onClick = { selected = index },
                        icon = { Icon(icons[index], title) },
                        label = { Text(title) }
                    )
                }
            }
        }
    ) { padding ->
        when (selected) {
            0 -> CharacterListScreen(Modifier.padding(padding), navController, userId, false)
            1 -> CharacterListScreen(Modifier.padding(padding), navController, userId, true)
            2 -> FavoritesScreen(Modifier.padding(padding), navController, userId)
        }
    }
}

@Composable
private fun CharacterListScreen(
    modifier: Modifier,
    navController: androidx.navigation.NavHostController,
    userId: Int,
    searchable: Boolean
) {
    val vm: CharacterViewModel = viewModel()
    val favVm: FavoriteViewModel = viewModel()
    val characters by vm.characters.collectAsState()
    val loading by vm.loading.collectAsState()
    val error by vm.error.collectAsState()
    val favorites by favVm.favorites(userId).collectAsState()
    var query by remember { mutableStateOf("") }

    Column(modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        if (searchable) {
            OutlinedTextField(
                value = query, onValueChange = { query = it },
                label = { Text("Pesquisar personagem") },
                trailingIcon = {
                    IconButton(onClick = { vm.search(query) }) { Icon(Icons.Default.Search, "Pesquisar") }
                },
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            Text("Explore o multiverso", style = MaterialTheme.typography.titleLarge,
                color = Color.White, modifier = Modifier.padding(vertical = 12.dp))
        }

        if (loading) {
            CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally), color = PortalGreen)
        } else if (error.isNotBlank()) {
            Text(error, color = Color(0xFFFF8A80), modifier = Modifier.padding(16.dp))
            Button(onClick = { if (searchable) vm.search(query) else vm.loadCharacters() }) {
                Text("Tentar novamente")
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(characters, key = { it.id }) { character ->
                    CharacterCard(
                        character = character,
                        isFavorite = favorites.any { it.characterId == character.id },
                        onClick = { navController.navigate(DetailRoute(character.id)) },
                        onFavorite = {
                            favVm.toggle(userId, character, favorites.any { it.characterId == character.id })
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun CharacterCard(
    character: RickCharacter,
    isFavorite: Boolean,
    onClick: () -> Unit,
    onFavorite: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = CardColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(10.dp)) {
            AsyncImage(model = character.image, contentDescription = character.name,
                modifier = Modifier.size(94.dp))
            Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                Text(character.name, fontWeight = FontWeight.Bold, color = Color.White)
                Text(character.species, color = Color.LightGray)
                Text("● ${character.status}", color = if (character.status == "Alive") PortalGreen else Color(0xFFFF8A80))
            }
            IconButton(onClick = onFavorite) {
                Icon(Icons.Default.Favorite, "Favorito",
                    tint = if (isFavorite) Color(0xFFFF6B81) else Color.Gray)
            }
        }
    }
}

@Composable
private fun DetailScreen(id: Int, auth: AuthViewModel, onBack: () -> Unit) {
    val vm: CharacterViewModel = viewModel()
    val favVm: FavoriteViewModel = viewModel()
    val userId by auth.userId.collectAsState()
    val favorites by favVm.favorites(userId).collectAsState()
    var character by remember { mutableStateOf<RickCharacter?>(null) }
    var error by remember { mutableStateOf("") }

    LaunchedEffect(id) {
        try { character = vm.getCharacter(id) }
        catch (e: Exception) { error = "Não foi possível carregar os detalhes." }
    }

    Column(
        Modifier.fillMaxSize().background(Background).verticalScroll(rememberScrollState()).padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextButton(onClick = onBack) {
            Text("Detalhes do personagem", color = PortalGreen)
        }
        if (character == null) {
            if (error.isNotBlank()) Text(error, color = Color.Red)
            else CircularProgressIndicator(color = PortalGreen)
        } else {
            val c = character!!
            AsyncImage(c.image, c.name, modifier = Modifier.size(220.dp))
            Text(c.name, style = MaterialTheme.typography.headlineMedium, color = PortalGreen,
                fontWeight = FontWeight.Bold)
            DetailLine("Status", c.status)
            DetailLine("Espécie", c.species)
            DetailLine("Gênero", c.gender)
            DetailLine("Origem", c.origin.name)
            DetailLine("Última localização", c.location.name)
            val isFav = favorites.any { it.characterId == c.id }
            Button(onClick = { favVm.toggle(userId, c, isFav) }) {
                Text(if (isFav) "Remover dos favoritos" else "Adicionar aos favoritos")
            }
        }
    }
}

@Composable
private fun DetailLine(label: String, value: String) {
    Row(Modifier.fillMaxWidth().padding(vertical = 7.dp)) {
        Text("$label: ", color = PortalGreen, fontWeight = FontWeight.Bold)
        Text(value, color = Color.White)
    }
}

@Composable
private fun FavoritesScreen(
    modifier: Modifier,
    navController: androidx.navigation.NavHostController,
    userId: Int
) {
    val vm: FavoriteViewModel = viewModel()
    val favorites by vm.favorites(userId).collectAsState()

    Column(modifier.fillMaxSize().padding(16.dp)) {
        Text("Meus favoritos", style = MaterialTheme.typography.titleLarge,
            color = PortalGreen, modifier = Modifier.padding(bottom = 12.dp))
        if (favorites.isEmpty()) {
            Text("Você ainda não salvou personagens.", color = Color.LightGray)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(favorites, key = { it.characterId }) { favorite ->
                    FavoriteCard(favorite) { navController.navigate(DetailRoute(favorite.characterId)) }
                }
            }
        }
    }
}

@Composable
private fun FavoriteCard(favorite: Favorite, onClick: () -> Unit) {
    Card(Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = CardColor)) {
        Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(favorite.image, favorite.name, modifier = Modifier.size(82.dp))
            Column(Modifier.padding(start = 12.dp)) {
                Text(favorite.name, color = Color.White, fontWeight = FontWeight.Bold)
                Text(favorite.species, color = Color.LightGray)
                Text(favorite.status, color = PortalGreen)
            }
        }
    }
}
