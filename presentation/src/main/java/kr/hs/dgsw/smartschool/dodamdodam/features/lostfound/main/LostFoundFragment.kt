package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.main

import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundBinding
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemLostAndFoundBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.adapter.LostFoundAdapter
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

@AndroidEntryPoint
class LostFoundFragment : BaseFragment<FragmentLostFoundBinding, LostFoundViewModel>(), LostFoundAdapter.LostFoundCallBack {

    private lateinit var lostFoundAdapter: LostFoundAdapter
    override val viewModel: LostFoundViewModel by viewModels()

    var myId: String = ""
    override fun onResume() {
        super.onResume()
        viewModel.hasLostFound = true
    }

    override fun onStop() {
        super.onStop()
        viewModel.hasLostFound = false
    }
    override fun observerViewModel() {
        viewModel.getMyInfo()

        lostFoundAdapter = LostFoundAdapter(requireContext(), this)
        mBinding.rvLostAndFound.adapter = lostFoundAdapter

        listenViewEvent()

        with(viewModel) {
            foundChecked.observe(viewLifecycleOwner) {
                getLostFoundList()
            }
            mineChecked.observe(viewLifecycleOwner) {
                getLostFoundList()
            }
        }

        collectGetLostFoundState()
        collectGetMyInfoState()
    }

    private fun collectGetLostFoundState() = lifecycleScope.launchWhenStarted {
        viewModel.getLostFoundState.collect { state ->
            val list = setLostInfo(state.list)
            setRecyclerView(list)

            if (state.error.isNotBlank()) {
                shortToast(state.error)
            }
        }
    }

    private fun collectGetMyInfoState() = lifecycleScope.launchWhenStarted {
        viewModel.getMyInfoState.collect { state ->
            if (state.myId.isNotEmpty()) {
                myId = state.myId
                viewModel.getLostFoundList()
            }

            if (state.error.isNotBlank()) {
                shortToast(state.error)
            }
        }
    }

    private fun listenViewEvent() = with(mBinding) {
        btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        fbAddLostfound.setOnClickListener {
            findNavController().navigate(R.id.action_lostFoundFragment_to_lostFoundWriteFragment)
        }
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getLostFoundList()
            mBinding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun openComment(idx: Int) {
        val action = LostFoundFragmentDirections.actionLostFoundFragmentToLostFoundCommentFragment(idx)
        findNavController().navigate(action)
    }

    override fun onClickMore(item: LostInfo, binding: ItemLostAndFoundBinding) {
        if (item.member.id == item.myId) {
            val pm = PopupMenu(context, binding.ibBtnMore)
            pm.inflate(R.menu.lost_found_item_menu)
            pm.setOnMenuItemClickListener { data ->
                when (data.itemId) {
                    R.id.modify -> {
                        val action = LostFoundFragmentDirections.actionLostFoundFragmentToLostFoundUpdateFragment(item.idx)
                        findNavController().navigate(action)
                        true
                    }
                    R.id.delete -> {
                        viewModel.deleteLostFound(item.idx)
                        true
                    }
                    else -> false
                }
            }
            pm.show()
        }
    }

    private fun setLostInfo(lostFoundList: List<LostFound>): List<LostInfo> {
        val list: MutableList<LostInfo> = mutableListOf()
        if (lostFoundList.isEmpty()) return emptyList()
        lostFoundList.forEach {
            list.add(
                LostInfo(
                    idx = it.idx,
                    img = it.image ?: "",
                    name = it.member.name,
                    uploadTime = it.createAt,
                    title = it.title,
                    content = it.content,
                    place = it.place,
                    member = it.member,
                    myId = myId
                )
            )
        }
        return list.toList()
    }

    private fun setRecyclerView(list: List<LostInfo>) {
        lostFoundAdapter.submitList(list)
    }
}
