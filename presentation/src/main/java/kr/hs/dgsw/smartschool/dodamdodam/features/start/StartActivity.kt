package kr.hs.dgsw.smartschool.dodamdodam.features.start

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.RepeatModeUtil
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.auth.login.LoginActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll

class StartActivity : AppCompatActivity() {

    private val btnStart: Button by lazy {
        findViewById(R.id.btn_start)
    }

    private val playerView: StyledPlayerView by lazy {
        findViewById(R.id.player_view)
    }

    private lateinit var exoPlayer: ExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        setExoPlayer()
        exoPlayer.play()

        btnStart.setOnClickListener {
            startActivity(LoginActivity::class.java)
        }
    }

    private fun setExoPlayer() {
        exoPlayer = ExoPlayer.Builder(this).build()
        playerView.player = exoPlayer

        val uri = RawResourceDataSource.buildRawResourceUri(R.raw.dodam_android_video)
        val mediaItem = MediaItem.fromUri(uri)

        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.repeatMode = Player.REPEAT_MODE_ALL
    }

    // Set Lifecycle!
    override fun onResume() {
        exoPlayer.prepare()
        super.onResume()
    }

    override fun onPause() {
        exoPlayer.pause()
        super.onPause()
    }

    override fun onDestroy() {
        exoPlayer.release()
        super.onDestroy()
    }

}
