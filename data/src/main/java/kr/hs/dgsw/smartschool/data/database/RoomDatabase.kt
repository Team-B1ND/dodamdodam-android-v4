package kr.hs.dgsw.smartschool.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import kr.hs.dgsw.smartschool.data.database.dao.*
import kr.hs.dgsw.smartschool.data.database.entity.*
import java.util.concurrent.Executors

@Database(
    entities = [
    MealEntity::class, TokenEntity::class, AccountEntity::class,
    MemberEntity::class, StudentEntity::class, TeacherEntity::class,
    HistoryMemberEntity::class, TimeEntity::class, PlaceEntity::class,
    ClassInfoEntity::class
    ],
    version = 4,
    exportSchema = false
)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun mealDao(): MealDao
    abstract fun tokenDao(): TokenDao
    abstract fun accountDao(): AccountDao
    abstract fun memberDao(): MemberDao
    abstract fun studentDao(): StudentDao
    abstract fun teacherDao(): TeacherDao
    abstract fun historyMemberDao(): HistoryMemberDao
    abstract fun timeDao(): TimeDao
    abstract fun placeDao(): PlaceDao
    abstract fun classInfoDao(): ClassInfoDao

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