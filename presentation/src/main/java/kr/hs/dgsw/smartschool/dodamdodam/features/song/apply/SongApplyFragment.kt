package kr.hs.dgsw.smartschool.dodamdodam.features.song.apply

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentSongApplyBinding

@AndroidEntryPoint
class SongApplyFragment : BaseFragment<FragmentSongApplyBinding, SongApplyViewModel>() {
    override val viewModel: SongApplyViewModel by viewModels()

    override fun observerViewModel() {
        bindingViewEvent { event ->
            when (event) {
                SongApplyViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }
    }
}