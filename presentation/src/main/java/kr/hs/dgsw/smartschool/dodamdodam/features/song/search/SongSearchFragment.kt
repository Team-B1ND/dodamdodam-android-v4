package kr.hs.dgsw.smartschool.dodamdodam.features.song.search

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentSongSearchBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast

class SongSearchFragment : BaseFragment<FragmentSongSearchBinding, SongSearchViewModel>() {

    override val viewModel: SongSearchViewModel by viewModels()

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when(event) {
                SongSearchViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
                SongSearchViewModel.EVENT_ON_CLICK_SEARCH -> shortToast("노래를 검색합니다.")
            }
        }
    }

}