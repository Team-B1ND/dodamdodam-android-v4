package kr.hs.dgsw.smartschool.domain.repository

interface AuthRepository {
    suspend fun join(email: String, grade: Int, id: String, name: String, number: Int, phone: String, pw: String, room: Int): String

    suspend fun login(id: String, pw: String, encryption: Boolean = true)
}
