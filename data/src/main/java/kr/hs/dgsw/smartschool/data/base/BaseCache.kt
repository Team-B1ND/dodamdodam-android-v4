package kr.hs.dgsw.smartschool.data.base

import android.app.Application
import kr.hs.dgsw.smartschool.data.database.RoomDatabase

open class BaseCache(application: Application) {
    protected val database = RoomDatabase.getInstance(application)!!
}