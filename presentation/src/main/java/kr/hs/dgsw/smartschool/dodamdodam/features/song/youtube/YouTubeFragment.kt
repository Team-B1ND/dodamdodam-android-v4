package kr.hs.dgsw.smartschool.dodamdodam.features.song.youtube

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentYoutubeBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.openVideoFromUrl
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.song.youtube.YoutubeVideo
import java.lang.IndexOutOfBoundsException

@AndroidEntryPoint
class YouTubeFragment : BaseFragment<FragmentYoutubeBinding, YouTubeViewModel>() {

    override val viewModel: YouTubeViewModel by viewModels()
    private val args: YouTubeFragmentArgs by navArgs()

    override fun observerViewModel() {
        collectYoutubeVideo()
        collectApplySongState()
        viewModel.getYouTubeVideo(args.title)

        bindingViewEvent { event ->
            when (event) {
                YouTubeViewModel.EVENT_ON_SEARCH_TITLE_ERROR -> shortToast(viewModel.errorMessage)
                YouTubeViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
                YouTubeViewModel.EVENT_ON_CLICK_THUMBNAIL -> this.openVideoFromUrl(viewModel.url.value ?: "https://www.youtube.com/watch?v=TqIAndOnd74")
                YouTubeViewModel.EVENT_ON_CLICK_COPY -> {
                    val clipboard: ClipboardManager = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    val clip: ClipData = ClipData.newPlainText("youtubeUrl", viewModel.url.value)
                    clipboard.setPrimaryClip(clip)
                    shortToast("클립보드에 복사되었습니다.")
                }
            }
        }
    }

    private fun collectYoutubeVideo() {
        lifecycleScope.launchWhenStarted {
            viewModel.getYouTubeVideoState.collect { state ->
                if (state.youtubeVideo != null) {
                    setVideoView(state.youtubeVideo)
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun collectApplySongState() {
        lifecycleScope.launchWhenStarted {
            viewModel.applySongState.collect { state ->
                if (state.message.isNotBlank()) {
                    shortToast(state.message)
                    findNavController().popBackStack()
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun setVideoView(youtubeVideo: YoutubeVideo) {
        try {
            val item = youtubeVideo.items[0]
            viewModel.url.value = "https://www.youtube.com/watch?v=${item.id.videoId}"

            Glide.with(mBinding.ivThumbnail.context)
                .load(item.snippet.thumbnails.high.url)
                .error(R.drawable.default_img)
                .centerCrop()
                .into(mBinding.ivThumbnail)

            mBinding.tvTitle.text = args.title
            mBinding.tvSubTitle.text = HtmlCompat.fromHtml(item.snippet.title, HtmlCompat.FROM_HTML_MODE_LEGACY)
        } catch (e: IndexOutOfBoundsException) {
            shortToast("검색 결과가 없습니다.")
        }
    }
}
