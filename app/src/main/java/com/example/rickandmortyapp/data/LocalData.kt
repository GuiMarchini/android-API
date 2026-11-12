package com.example.rickandmortyapp.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow
import java.security.MessageDigest

@Entity(tableName = "users")
data class User(
    @androidx.room.PrimaryKey(autoGenerate = true) val id: Int = 0,
    val email: String,
    val passwordHash: String
)

@Entity(tableName = "favorites", primaryKeys = ["userId", "characterId"])
data class Favorite(
    val userId: Int,
    val characterId: Int,
    val name: String,
    val image: String,
    val species: String,
    val status: String
)

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun findByEmail(email: String): User?
}

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(favorite: Favorite)

    @Query("DELETE FROM favorites WHERE userId = :userId AND characterId = :characterId")
    suspend fun remove(userId: Int, characterId: Int)

    @Query("SELECT * FROM favorites WHERE userId = :userId ORDER BY name")
    fun getFavorites(userId: Int): Flow<List<Favorite>>

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE userId = :userId AND characterId = :characterId)")
    suspend fun isFavorite(userId: Int, characterId: Int): Boolean
}

@Database(entities = [User::class, Favorite::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "rick_morty_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}

fun String.sha256(): String {
    val bytes = MessageDigest.getInstance("SHA-256").digest(toByteArray())
    return bytes.joinToString("") { "%02x".format(it) }
}
