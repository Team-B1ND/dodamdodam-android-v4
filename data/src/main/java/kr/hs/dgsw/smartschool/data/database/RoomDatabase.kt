package kr.hs.dgsw.smartschool.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import kr.hs.dgsw.smartschool.data.database.dao.AccountDao
import kr.hs.dgsw.smartschool.data.database.dao.MealDao
import kr.hs.dgsw.smartschool.data.database.dao.TokenDao
import kr.hs.dgsw.smartschool.data.database.entity.AccountEntity
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity
import kr.hs.dgsw.smartschool.data.database.entity.SignInEntity
import kr.hs.dgsw.smartschool.data.database.entity.TokenEntity
import java.util.concurrent.Executors

@Database(
    entities =
    [MealEntity::class, TokenEntity::class,
    AccountEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun mealDao(): MealDao
    abstract fun tokenDao(): TokenDao
    abstract fun accountDao(): AccountDao

    companion object {
        private var instance: RoomDatabase? = null

        @Synchronized
        fun getInstance(context: Context): RoomDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatabase::class.java, "dodam_database"
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