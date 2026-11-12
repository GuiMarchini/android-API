package com.example.rickandmortyapp.viewmodel;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.AndroidViewModel;
import com.example.rickandmortyapp.data.Favorite;
import com.example.rickandmortyapp.data.Repository;
import com.example.rickandmortyapp.data.RickCharacter;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.SharingStarted;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\fJ\u001e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/rickandmortyapp/viewmodel/FavoriteViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "repository", "Lcom/example/rickandmortyapp/data/Repository;", "favorites", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/example/rickandmortyapp/data/Favorite;", "userId", "", "remove", "", "characterId", "toggle", "character", "Lcom/example/rickandmortyapp/data/RickCharacter;", "currentlyFavorite", "", "app_debug"})
public final class FavoriteViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.rickandmortyapp.data.Repository repository = null;
    
    public FavoriteViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.rickandmortyapp.data.Favorite>> favorites(int userId) {
        return null;
    }
    
    public final void toggle(int userId, @org.jetbrains.annotations.NotNull()
    com.example.rickandmortyapp.data.RickCharacter character, boolean currentlyFavorite) {
    }
    
    public final void remove(int userId, int characterId) {
    }
}