package kr.hs.dgsw.smartschool.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import kr.hs.dgsw.smartschool.data.database.dao.AccountDao
import kr.hs.dgsw.smartschool.data.database.dao.ClassroomDao
import kr.hs.dgsw.smartschool.data.database.dao.MealDao
import kr.hs.dgsw.smartschool.data.database.dao.MemberDao
import kr.hs.dgsw.smartschool.data.database.dao.PlaceDao
import kr.hs.dgsw.smartschool.data.database.dao.StudentDao
import kr.hs.dgsw.smartschool.data.database.dao.TeacherDao
import kr.hs.dgsw.smartschool.data.database.dao.TimeDao
import kr.hs.dgsw.smartschool.data.database.dao.TokenDao
import kr.hs.dgsw.smartschool.data.database.entity.AccountEntity
import kr.hs.dgsw.smartschool.data.database.entity.ClassroomEntity
import kr.hs.dgsw.smartschool.data.database.entity.MealEntity
import kr.hs.dgsw.smartschool.data.database.entity.MemberEntity
import kr.hs.dgsw.smartschool.data.database.entity.PlaceEntity
import kr.hs.dgsw.smartschool.data.database.entity.StudentEntity
import kr.hs.dgsw.smartschool.data.database.entity.TeacherEntity
import kr.hs.dgsw.smartschool.data.database.entity.TimeEntity
import kr.hs.dgsw.smartschool.data.database.entity.TokenEntity
import java.util.concurrent.Executors

@Database(
    entities = [
        TokenEntity::class, AccountEntity::class,
        MemberEntity::class, StudentEntity::class, TeacherEntity::class,
        TimeEntity::class, PlaceEntity::class,
        ClassroomEntity::class, MealEntity::class
    ],
    version = 9,
    exportSchema = false
)
abstract class RoomDatabase : androidx.room.RoomDatabase() {

    abstract fun tokenDao(): TokenDao
    abstract fun accountDao(): AccountDao
    abstract fun memberDao(): MemberDao
    abstract fun studentDao(): StudentDao
    abstract fun teacherDao(): TeacherDao
    abstract fun timeDao(): TimeDao
    abstract fun placeDao(): PlaceDao
    abstract fun classInfoDao(): ClassroomDao
    abstract fun mealDao(): MealDao

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
    