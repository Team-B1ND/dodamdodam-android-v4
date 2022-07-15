package kr.hs.dgsw.smartschool.dodamdodam.features.splash

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kr.hs.dgsw.smartschool.data.database.sharedpreferences.SharedPreferenceManager
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.features.sign.`in`.SignInActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinish

class SplashActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            setContentView(R.layout.activity_start)
            Thread.sleep(1000)
            startNextActivity()
        }

        val splashScreen = installSplashScreen()
        splashScreen.setKeepOnScreenCondition { true }
        startNextActivity()
    }

    private fun startNextActivity() {
        startActivityWithFinish(
            if (SharedPreferenceManager.getIsSignIn(this))
                MainActivity::class.java
            else SignInActivity::class.java
        )
    }
}