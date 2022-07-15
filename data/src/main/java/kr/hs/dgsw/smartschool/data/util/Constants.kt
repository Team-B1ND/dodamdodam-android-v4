package kr.hs.dgsw.smartschool.data.util

import android.os.Environment

/*
    광용 192.168.0.20
    진영 192.168.0.19
    진우 10.80.163.165:3000
    M2SYS HOST 192.168.1.13
    HOST dodam.b1nd.com/api/v2/
    SOCKET_HOST socket.dodam.b1nd.com/
*/
object Constants {
    val DIRECTORY_DOWNLOADS = Environment.getExternalStorageDirectory().toString() + "/DodamDodam"
    const val mode = "RELEASE"
    const val DEFAULT_HOST = "http://dodam.b1nd.com:80/api/v2/"
    const val SOCKET_HOST = "http://dodam.b1nd.com/"
    const val TEST_HOST = "http://110.80.163.92:3000/api/v2/"
    const val AUTH_HOST = "http://auth.dodam.b1nd.com/"
    const val AUTH_TEST_HOST = "http://10.80.163.141:8080/"
    const val IMAGE_HOST = "http://dodam.b1nd.com/api/image/"

    val SERVER_HOST = if(mode == "TEST") TEST_HOST else DEFAULT_HOST
    val AUTH_SERVER_HOST = if(mode == "TEST") AUTH_TEST_HOST else AUTH_HOST

    const val PLAY_STORE = "https://play.google.com/store/apps/details?id=kr.hs.dgsw.smartschool.dodamdodam"
    const val INFORMATION_AGREEMENT_URL = "http://dodam.b1nd.com/detailed-information/personal-information"
    const val SERVICE_AGREEMENT_URL = "http://dodam.b1nd.com/detailed-information/service-policy"
    const val RELEASE_NOTE_URL = "https://b1nd.com/#/dodam_and_rel_s"

    const val TEAM_NAME = "B1ND"
    const val TIME_OUT_MESSAGE = "시간초과 다시 한번 시도해주세요"
    const val SERVER_ERROR_MESSAGE = "이용에 불편을 드려 죄송합니다\n추후에 다시 시도해주세요"

    const val INFINITE_SCROLL_LIMIT = 20
}