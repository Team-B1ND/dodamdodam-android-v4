package kr.hs.dgsw.smartschool.data.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import javax.inject.Inject

interface AppDispatchers {
    val io: CoroutineDispatcher
    val main: CoroutineDispatcher
    val default: CoroutineDispatcher
}

class AppDispatchersImpl @Inject constructor() : AppDispatchers {
    override val io: CoroutineDispatcher = IO
    override val main: CoroutineDispatcher = Main
    override val default: CoroutineDispatcher = Default
}