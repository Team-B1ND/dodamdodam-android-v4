package kr.hs.dgsw.smartschool.domain.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

object Utils {
    @JvmStatic
    @Throws(NoSuchAlgorithmException::class)
    fun encryptSHA512(target: String): String? {
        val messageDigest =
            MessageDigest.getInstance("SHA-512")
        val encryptedPassword = StringBuilder()
        messageDigest.update(target.toByteArray())
        val buffer = messageDigest.digest()
        for (temp in buffer) {
            var sb =
                StringBuilder(Integer.toHexString(temp.toInt()))
            while (sb.length < 2) {
                sb.insert(0, "0")
            }
            sb = StringBuilder(sb.substring(sb.length - 2))
            encryptedPassword.append(sb)
        }
        return encryptedPassword.toString()
    }
}