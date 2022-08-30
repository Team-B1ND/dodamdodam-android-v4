package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound

import androidx.fragment.app.viewModels
import kr.hs.dgsw.smartschool.dodamdodam.adapter.LostFoundAdapter
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundBinding
import kr.hs.dgsw.smartschool.domain.model.bus.BusByDate
import kr.hs.dgsw.smartschool.domain.model.bus.BusInfo
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo
import java.time.LocalDate

class LostFoundFragment : BaseFragment<FragmentLostFoundBinding, LostFoundViewModel>(), LostFoundAdapter.LostFoundCallBack {
    override val viewModel: LostFoundViewModel by viewModels()
    private lateinit var lostFoundAdapter: LostFoundAdapter
    override fun observerViewModel() {
        lostFoundAdapter = LostFoundAdapter(requireContext(), this)
        mBinding.rvLostAndFound.adapter = lostFoundAdapter
    }

    override fun openComment(idx: Int) {
        viewModel.openComment(idx)
    }

    private fun setBusInfo(lostFoundList: List<LostFound>): List<LostInfo> {
        val list: MutableList<LostInfo> = mutableListOf()
        // val today = LocalDate.now()
        lostFoundList.forEach {
            list.add(
                LostInfo(
                    idx = it.idx,
                    img = it.profileImage,
                    name = it.name ?: "작성자 모름",
                    uploadTime = it.uploadTime.toString(),
                    title = it.title,
                    content = it.content,
                    place = it.place ?: ""
                )
            )
        }
        return list.toList()
    }
    private fun setRecyclerView(){
        lostFoundAdapter.submitList(setBusInfo(viewModel.getLostFoundState.value.list))
    }
}
