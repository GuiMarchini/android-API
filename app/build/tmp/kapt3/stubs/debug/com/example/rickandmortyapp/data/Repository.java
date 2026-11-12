package com.example.rickandmortyapp.data;

import android.content.Context;
import com.example.rickandmortyapp.network.RetrofitInstance;
import kotlinx.coroutines.flow.Flow;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\u0016H\u0086@\u00a2\u0006\u0002\u0010\u0017J\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u00192\u0006\u0010\r\u001a\u00020\u000eJ\u001e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u001fJ,\u0010 \u001a\b\u0012\u0004\u0012\u00020\u000e0!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b%\u0010&J,\u0010\'\u001a\b\u0012\u0004\u0012\u00020\u000e0!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b(\u0010&J\u001e\u0010)\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010*\u001a\u00020\u00162\u0006\u0010+\u001a\u00020#H\u0086@\u00a2\u0006\u0002\u0010,R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006-"}, d2 = {"Lcom/example/rickandmortyapp/data/Repository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "database", "Lcom/example/rickandmortyapp/data/AppDatabase;", "favoriteDao", "Lcom/example/rickandmortyapp/data/FavoriteDao;", "userDao", "Lcom/example/rickandmortyapp/data/UserDao;", "addFavorite", "", "userId", "", "character", "Lcom/example/rickandmortyapp/data/RickCharacter;", "(ILcom/example/rickandmortyapp/data/RickCharacter;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCharacter", "id", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCharacters", "Lcom/example/rickandmortyapp/data/CharacterResponse;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getFavorites", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/rickandmortyapp/data/Favorite;", "isFavorite", "", "characterId", "(IILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "login", "Lkotlin/Result;", "email", "", "password", "login-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "register", "register-0E7RQCE", "removeFavorite", "searchCharacters", "name", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class Repository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.rickandmortyapp.data.AppDatabase database = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.rickandmortyapp.data.UserDao userDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.rickandmortyapp.data.FavoriteDao favoriteDao = null;
    
    public Repository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCharacters(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.rickandmortyapp.data.CharacterResponse> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchCharacters(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.rickandmortyapp.data.CharacterResponse> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getCharacter(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.rickandmortyapp.data.RickCharacter> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addFavorite(int userId, @org.jetbrains.annotations.NotNull()
    com.example.rickandmortyapp.data.RickCharacter character, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeFavorite(int userId, int characterId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.rickandmortyapp.data.Favorite>> getFavorites(int userId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object isFavorite(int userId, int characterId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
}