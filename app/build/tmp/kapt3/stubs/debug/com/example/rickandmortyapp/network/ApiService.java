package com.example.rickandmortyapp.network;

import com.example.rickandmortyapp.data.CharacterResponse;
import com.example.rickandmortyapp.data.RickCharacter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\b\b\u0001\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\b\b\u0003\u0010\t\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\n\u001a\u00020\b2\b\b\u0001\u0010\u000b\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"Lcom/example/rickandmortyapp/network/ApiService;", "", "getCharacterById", "Lcom/example/rickandmortyapp/data/RickCharacter;", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCharacters", "Lcom/example/rickandmortyapp/data/CharacterResponse;", "page", "searchCharacter", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.GET(value = "character")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCharacters(@retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.rickandmortyapp.data.CharacterResponse> $completion);
    
    @retrofit2.http.GET(value = "character/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCharacterById(@retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.rickandmortyapp.data.RickCharacter> $completion);
    
    @retrofit2.http.GET(value = "character/")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchCharacter(@retrofit2.http.Query(value = "name")
    @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.example.rickandmortyapp.data.CharacterResponse> $completion);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
    }
}