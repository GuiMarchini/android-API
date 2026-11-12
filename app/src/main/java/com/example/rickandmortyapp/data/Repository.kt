package com.example.rickandmortyapp.data

import android.content.Context
import com.example.rickandmortyapp.network.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class Repository(context: Context) {
    private val database = AppDatabase.getDatabase(context)
    private val userDao = database.userDao()
    private val favoriteDao = database.favoriteDao()

    suspend fun register(email: String, password: String): Result<Int> {
        val normalizedEmail = email.trim().lowercase()
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(normalizedEmail).matches()) {
            return Result.failure(Exception("Digite um e-mail válido."))
        }
        if (password.length < 6) {
            return Result.failure(Exception("A senha precisa ter pelo menos 6 caracteres."))
        }
        if (userDao.findByEmail(normalizedEmail) != null) {
            return Result.failure(Exception("Este e-mail já está cadastrado."))
        }
        val id = userDao.insert(User(email = normalizedEmail, passwordHash = password.sha256()))
        return Result.success(id.toInt())
    }

    suspend fun login(email: String, password: String): Result<Int> {
        val user = userDao.findByEmail(email.trim().lowercase())
        return if (user != null && user.passwordHash == password.sha256()) {
            Result.success(user.id)
        } else {
            Result.failure(Exception("E-mail ou senha incorretos."))
        }
    }

    suspend fun getCharacters() = RetrofitInstance.api.getCharacters()
    suspend fun searchCharacters(name: String) = RetrofitInstance.api.searchCharacter(name)
    suspend fun getCharacter(id: Int) = RetrofitInstance.api.getCharacterById(id)

    suspend fun addFavorite(userId: Int, character: RickCharacter) {
        favoriteDao.add(
            Favorite(userId, character.id, character.name, character.image, character.species, character.status)
        )
    }

    suspend fun removeFavorite(userId: Int, characterId: Int) {
        favoriteDao.remove(userId, characterId)
    }

    fun getFavorites(userId: Int): Flow<List<Favorite>> = favoriteDao.getFavorites(userId)
    suspend fun isFavorite(userId: Int, characterId: Int) =
        favoriteDao.isFavorite(userId, characterId)
}
