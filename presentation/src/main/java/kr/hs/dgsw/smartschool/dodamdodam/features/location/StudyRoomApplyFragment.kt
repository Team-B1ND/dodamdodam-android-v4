package kr.hs.dgsw.smartschool.dodamdodam.features.location

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentStudyRoomApplyBinding

class StudyRoomApplyFragment : BaseFragment<FragmentStudyRoomApplyBinding, StudyRoomApplyViewModel>() {
    override val viewModel: StudyRoomApplyViewModel by viewModels()

    override fun observerViewModel() {
    }
}