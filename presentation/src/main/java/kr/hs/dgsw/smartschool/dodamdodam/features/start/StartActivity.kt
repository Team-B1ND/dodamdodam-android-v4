package kr.hs.dgsw.smartschool.dodamdodam.features.start

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.auth.login.LoginActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll

class StartActivity : AppCompatActivity() {

    val btnStart: Button by lazy {
        findViewById(R.id.btn_start)
    }

    val motionLayoutGatherPlatform: MotionLayout by lazy {
        findViewById(R.id.motion_layout_gather_platform)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        motionLayoutGatherPlatform.transitionToStart()
        motionLayoutGatherPlatform.transitionToEnd()

        btnStart.setOnClickListener {

            startActivityWithFinishAll(LoginActivity::class.java)
        }
    }
}
