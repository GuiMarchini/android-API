package com.example.rickandmortyapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.Favorite
import com.example.rickandmortyapp.data.Repository
import com.example.rickandmortyapp.data.RickCharacter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)
    private val prefs = application.getSharedPreferences("session", Context.MODE_PRIVATE)

    private val _userId = MutableStateFlow(prefs.getInt("user_id", -1))
    val userId: StateFlow<Int> = _userId.asStateFlow()

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message.asStateFlow()

    fun register(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = repository.register(email, password)
            result.onSuccess {
                saveSession(it)
                _message.value = ""
                onSuccess()
            }.onFailure { _message.value = it.message ?: "Não foi possível cadastrar." }
        }
    }

    fun login(email: String, password: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            val result = repository.login(email, password)
            result.onSuccess {
                saveSession(it)
                _message.value = ""
                onSuccess()
            }.onFailure { _message.value = it.message ?: "E-mail ou senha incorretos." }
        }
    }

    private fun saveSession(id: Int) {
        prefs.edit().putInt("user_id", id).apply()
        _userId.value = id
    }

    fun logout() {
        prefs.edit().remove("user_id").apply()
        _userId.value = -1
    }

    fun clearMessage() { _message.value = "" }
}

class CharacterViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)
    private val _characters = MutableStateFlow<List<RickCharacter>>(emptyList())
    val characters: StateFlow<List<RickCharacter>> = _characters.asStateFlow()
    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()
    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error.asStateFlow()

    init { loadCharacters() }

    fun loadCharacters() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = ""
            try {
                _characters.value = repository.getCharacters().results
            } catch (e: Exception) {
                _error.value = "Não foi possível carregar. Verifique sua conexão."
            } finally {
                _loading.value = false
            }
        }
    }

    fun search(name: String) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = ""
            try {
                _characters.value = if (name.isBlank()) repository.getCharacters().results
                else repository.searchCharacters(name).results
            } catch (e: Exception) {
                _characters.value = emptyList()
                _error.value = "Nenhum personagem encontrado ou erro de conexão."
            } finally {
                _loading.value = false
            }
        }
    }

    suspend fun getCharacter(id: Int) = repository.getCharacter(id)
}

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)

    fun favorites(userId: Int) = repository.getFavorites(userId)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun toggle(userId: Int, character: RickCharacter, currentlyFavorite: Boolean) {
        viewModelScope.launch {
            if (currentlyFavorite) repository.removeFavorite(userId, character.id)
            else repository.addFavorite(userId, character)
        }
    }

    fun remove(userId: Int, characterId: Int) {
        viewModelScope.launch { repository.removeFavorite(userId, characterId) }
    }
}
