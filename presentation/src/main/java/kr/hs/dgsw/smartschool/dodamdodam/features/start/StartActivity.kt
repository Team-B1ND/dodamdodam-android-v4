package kr.hs.dgsw.smartschool.dodamdodam.features.start

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import kr.hs.dgsw.smartschool.data.database.sharedpreferences.SharedPreferenceManager
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.auth.login.LoginActivity
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll

class StartActivity: AppCompatActivity() {

    val btnStart: Button by lazy {
        findViewById(R.id.btn_start)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContentView(R.layout.activity_start)

        btnStart.setOnClickListener {
            startActivityWithFinishAll(
                if (SharedPreferenceManager.getIsLogin(this))
                    MainActivity::class.java
                else
                    LoginActivity::class.java
            )
        }
    }
}