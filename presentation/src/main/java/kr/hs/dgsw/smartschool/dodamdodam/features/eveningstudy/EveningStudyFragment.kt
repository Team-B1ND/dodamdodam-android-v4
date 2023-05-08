package kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy

import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentEveningStudyBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy.adapter.EveningStudyListAdapter

@AndroidEntryPoint
class EveningStudyFragment : BaseFragment<FragmentEveningStudyBinding, EveningStudyViewModel>(), EveningStudyListAdapter.EveningStudyAction {

    override val viewModel: EveningStudyViewModel by viewModels()
    private lateinit var eveningStudyListAdapter: EveningStudyListAdapter
    override fun observerViewModel() {
        setSwipeRefresh()
        initEveningStudyListAdapter()
        bindingViewEvent { event ->
            when (event) {
                EveningStudyViewModel.ON_CLICK_BACK -> {
                    findNavController().popBackStack()
                }
                EveningStudyViewModel.ON_CLICK_EVENING_STUDY_WRITE -> {
                    val action = EveningStudyFragmentDirections.actionEveningStudyFragmentToEveningStudyDetailFragment()
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun initEveningStudyListAdapter() {
        eveningStudyListAdapter = EveningStudyListAdapter(this)
        mBinding.rvOutList.adapter = eveningStudyListAdapter
    }

    override fun onClickDelete(id: Int) {
        viewModel.deleteEveningStudy(id)
    }

    private fun setSwipeRefresh() {
        mBinding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getMyEveningStudy()
        }
    }

    override fun onClickItem(id: Int) {
        val action: NavDirections = EveningStudyFragmentDirections.actionEveningStudyFragmentToEveningStudyDetailFragment(id = id)
        findNavController().navigate(action)
    }
}
