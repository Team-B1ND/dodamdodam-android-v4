package kr.hs.dgsw.smartschool.data.database

import android.content.Context
import android.media.session.MediaSession
import androidx.room.Database
import androidx.room.Room
import kr.hs.dgsw.smartschool.data.database.dao.CardPostDao
import kr.hs.dgsw.smartschool.data.database.dao.MealDao
import kr.hs.dgsw.smartschool.data.database.dao.SignInDao
import kr.hs.dgsw.smartschool.data.database.dao.TokenDao
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity
import kr.hs.dgsw.smartschool.data.database.entity.SignInEntity
import kr.hs.dgsw.smartschool.data.database.entity.TokenEntity
import kr.hs.dgsw.smartschool.data.database.dao.UserInfoDao
import kr.hs.dgsw.smartschool.data.database.entity.CardPostEntity
import kr.hs.dgsw.smartschool.data.database.entity.UserInfoEntity
import java.util.concurrent.Executors

@Database(
    entities = [MealEntity::class, UserInfoEntity::class, CardPostEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : androidx.room.RoomDatabase() {
    abstract fun mealDao(): MealDao
    abstract fun signInDao(): SignInDao
    abstract fun tokenDao(): TokenDao
    abstract fun userInfoDao(): UserInfoDao
    abstract fun cardPostDao(): CardPostDao

    companion object {
        private var instance: RoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatabase::class.java, "dpple_database"
                )
                    .setQueryCallback({ sqlQuery, bindArgs ->
                        println("SQL Query: $sqlQuery SQL Args: $bindArgs")
                    }, Executors.newSingleThreadExecutor())
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance
        }
    }
}