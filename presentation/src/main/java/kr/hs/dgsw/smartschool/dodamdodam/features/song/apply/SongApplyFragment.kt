package kr.hs.dgsw.smartschool.dodamdodam.features.song.apply

import android.app.Dialog
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.song.adapter.RecommendSongAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentSongApplyBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.song.melon.MelonChart
import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo

@AndroidEntryPoint
class SongApplyFragment : BaseFragment<FragmentSongApplyBinding, SongApplyViewModel>() {
    override val viewModel: SongApplyViewModel by viewModels()

    lateinit var recommendSongAdapter: RecommendSongAdapter
    lateinit var dialog: Dialog

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when (event) {
                SongApplyViewModel.EVENT_ON_CLICK_BACK -> popBackStack()
                SongApplyViewModel.EVENT_ON_URL_ERROR -> shortToast(viewModel.errorMessage)
                SongApplyViewModel.EVENT_ON_SUCCESS_APPLY -> successApplySong()
            }
        }
        setDialog()
        setRecommendSongAdapter()
        collectMelonChart()
        collectYoutubeVideo()
    }

    private fun collectMelonChart() {
        lifecycleScope.launchWhenStarted {
            viewModel.getMelonChartState.collect { state ->
                if (state.melonChartList.isNotEmpty()) {
                    updateRecommendSongRecyclerView(state.melonChartList)
                }

                if (state.error.isNotBlank())
                    shortToast(state.error)
            }
        }
    }

    private fun collectYoutubeVideo() {
        lifecycleScope.launchWhenStarted {
            viewModel.getYouTubeVideoState.collect { state ->
                if (state.youtubeVideo != null) {
                    showDialog(state.youtubeVideo)
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun setDialog() {
        dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_youtube_video)
    }

    private fun setRecommendSongAdapter() {
        recommendSongAdapter = RecommendSongAdapter { title ->
            viewModel.getYouTubeVideo(title)
        }
        mBinding.recyclerRecommendSong.adapter = recommendSongAdapter
    }

    private fun showDialog(youtubeVideo: YoutubeVideo) {
        dialog.show()

        val thumbnail = dialog.findViewById<ImageView>(R.id.iv_thumbnail)
        Glide.with(thumbnail.context)
            .load(youtubeVideo.items[0].snippet.thumbnails.high.url)
            .error(R.drawable.default_img)
            .centerCrop()
            .into(thumbnail)

        val videoId = youtubeVideo.items[0].snippet.thumbnails.high.url.split("/").toTypedArray()[4]
        val videoUrl = "https://www.youtube.com/watch?v=$videoId"

        dialog.findViewById<TextView>(R.id.tv_link).text = videoUrl
        dialog.findViewById<TextView>(R.id.tv_title).text = youtubeVideo.items[0].snippet.title

        dialog.findViewById<Button>(R.id.btn_no).setOnClickListener {
            dialog.dismiss()
        }

        dialog.findViewById<Button>(R.id.btn_apply).setOnClickListener {
            viewModel.applyWakeUpSong(url = videoUrl)
            dialog.dismiss()
        }

    }

    private fun updateRecommendSongRecyclerView(list: List<MelonChart>) {
        recommendSongAdapter.submitList(list)
    }

    private fun successApplySong() {
        shortToast("기상송 신청에 성공했습니다.")
        popBackStack()
    }

    private fun popBackStack() {
        findNavController().popBackStack()
    }
}
