package com.example.rickandmortyapp.userinterface;

import androidx.compose.foundation.*;
import androidx.compose.foundation.layout.*;
import androidx.compose.foundation.lazy.*;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.*;
import androidx.compose.material3.*;
import androidx.compose.material3.ExperimentalMaterial3Api;
import androidx.compose.runtime.*;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.text.font.FontWeight;
import androidx.navigation.NavType;
import androidx.navigation.compose.*;
import com.example.rickandmortyapp.data.Favorite;
import com.example.rickandmortyapp.data.RickCharacter;
import com.example.rickandmortyapp.viewmodel.AuthViewModel;
import com.example.rickandmortyapp.viewmodel.CharacterViewModel;
import com.example.rickandmortyapp.viewmodel.FavoriteViewModel;
import kotlinx.serialization.Serializable;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a4\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0003\u001a4\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000b2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0003\u001a(\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000bH\u0003\u001a\u0018\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fH\u0003\u001a&\u0010!\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\u001b2\u0006\u0010\u0007\u001a\u00020\b2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0003\u001a\u001e\u0010$\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\rH\u0003\u001a \u0010\'\u001a\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0003\u001a\u0016\u0010(\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\u0019\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0002\u00a8\u0006)"}, d2 = {"Background", "Landroidx/compose/ui/graphics/Color;", "J", "CardColor", "PortalGreen", "AppNavigation", "", "auth", "Lcom/example/rickandmortyapp/viewmodel/AuthViewModel;", "AuthScreen", "isRegister", "", "onSuccess", "Lkotlin/Function0;", "onSwitch", "CharacterCard", "character", "Lcom/example/rickandmortyapp/data/RickCharacter;", "isFavorite", "onClick", "onFavorite", "CharacterListScreen", "modifier", "Landroidx/compose/ui/Modifier;", "navController", "Landroidx/navigation/NavHostController;", "userId", "", "searchable", "DetailLine", "label", "", "value", "DetailScreen", "id", "onBack", "FavoriteCard", "favorite", "Lcom/example/rickandmortyapp/data/Favorite;", "FavoritesScreen", "MainTabs", "app_debug"})
public final class AppNavigationKt {
    private static final long PortalGreen = 0L;
    private static final long Background = 0L;
    private static final long CardColor = 0L;
    
    @androidx.compose.runtime.Composable()
    public static final void AppNavigation(@org.jetbrains.annotations.NotNull()
    com.example.rickandmortyapp.viewmodel.AuthViewModel auth) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AuthScreen(com.example.rickandmortyapp.viewmodel.AuthViewModel auth, boolean isRegister, kotlin.jvm.functions.Function0<kotlin.Unit> onSuccess, kotlin.jvm.functions.Function0<kotlin.Unit> onSwitch) {
    }
    
    public static final void MainTabs(@org.jetbrains.annotations.NotNull()
    com.example.rickandmortyapp.viewmodel.AuthViewModel auth, @org.jetbrains.annotations.NotNull()
    androidx.navigation.NavHostController navController) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CharacterListScreen(androidx.compose.ui.Modifier modifier, androidx.navigation.NavHostController navController, int userId, boolean searchable) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CharacterCard(com.example.rickandmortyapp.data.RickCharacter character, boolean isFavorite, kotlin.jvm.functions.Function0<kotlin.Unit> onClick, kotlin.jvm.functions.Function0<kotlin.Unit> onFavorite) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DetailScreen(int id, com.example.rickandmortyapp.viewmodel.AuthViewModel auth, kotlin.jvm.functions.Function0<kotlin.Unit> onBack) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DetailLine(java.lang.String label, java.lang.String value) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void FavoritesScreen(androidx.compose.ui.Modifier modifier, androidx.navigation.NavHostController navController, int userId) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void FavoriteCard(com.example.rickandmortyapp.data.Favorite favorite, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
}