package kr.hs.dgsw.smartschool.dodamdodam.features.start

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.RawResourceDataSource
import com.google.android.exoplayer2.util.Util
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.auth.login.LoginActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.startActivityWithFinishAll

class StartActivity : AppCompatActivity() {

    private val btnStart: Button by lazy {
        findViewById(R.id.btn_start)
    }

    private val playerView: StyledPlayerView by lazy {
        findViewById(R.id.player_view)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val exoPlayer = ExoPlayer.Builder(this).build()
        playerView.player = exoPlayer

        val uri = RawResourceDataSource.buildRawResourceUri(R.raw.dodam_android_video)
        val mediaItem = MediaItem.fromUri(uri)

        exoPlayer.setMediaItem(mediaItem)

        exoPlayer.prepare()
        exoPlayer.play()


        btnStart.setOnClickListener {
            startActivityWithFinishAll(LoginActivity::class.java)
        }
    }
}
