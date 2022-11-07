package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.main

import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentLostFoundBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.adapter.LostFoundAdapter
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostFound
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

@AndroidEntryPoint
class LostFoundFragment : BaseFragment<FragmentLostFoundBinding, LostFoundViewModel>(), LostFoundAdapter.LostFoundCallBack {
    private lateinit var lostFoundAdapter: LostFoundAdapter
    override val viewModel: LostFoundViewModel by viewModels()

    var myId: String = ""
    private var list: List<LostInfo> = emptyList()

    override fun onResume() {
        super.onResume()
        viewModel.getMyInfo()
        viewModel.hasLostFound = true
        viewModel.getLostFoundList()
    }

    override fun onStop() {
        super.onStop()
        viewModel.hasLostFound = false
    }
    override fun observerViewModel() {

        lostFoundAdapter = LostFoundAdapter(requireContext(), this)
        mBinding.rvLostAndFound.adapter = lostFoundAdapter

        with(mBinding) {
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
            btnSearch.setOnClickListener {
                viewModel.searchLostFound()
            }

            // TODO INFINITE SCROLL 미구현, 구현해야함 ListAdapter 아이템 추가 방식

            /* rvLostAndFound.addOnScrollListener((object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    // 스크롤이 끝에 도달했는지 확인
                    if (rvLostAndFound.canScrollVertically(1)) {
                        viewModel.page.value = viewModel.page.value!! + 1
                        viewModel.getLostFoundList()
                    }

                    else if (rvLostAndFound.canScrollVertically(-1)) {
                        viewModel.page.value = viewModel.page.value!! - 1
                        viewModel.getLostFoundList()
                    }
                }
            }))*/
        }
        with(viewModel) {
            foundChecked.observe(
                viewLifecycleOwner,
                Observer<Boolean> {
                    Log.e("LostFoundFragment", it.toString())
                    getLostFoundList()
                }
            )
            mineChecked.observe(
                viewLifecycleOwner,
                Observer<Boolean> {
                    Log.e("LostFoundFragment", it.toString())
                    getLostFoundList()
                }
            )
        }
        with(viewModel) {
            lifecycleScope.launchWhenStarted {
                getLostFoundState.collect { state ->
                    val list = setLostInfo(state.list)
                    setRecyclerView(list)

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
            lifecycleScope.launchWhenStarted {
                getMyInfoState.collect { state ->
                    if (state.myId.isNotEmpty()) {
                        myId = state.myId
                    }

                    if (state.error.isNotBlank()) {
                        shortToast(state.error)
                    }
                }
            }
        }
    }

    override fun openComment(idx: Int) {
        val action = LostFoundFragmentDirections.actionLostFoundFragmentToLostFoundCommentFragment(idx)
        findNavController().navigate(action)
    }

    override fun modifyLostFound(idx: Int) {
        val action = LostFoundFragmentDirections.actionLostFoundFragmentToLostFoundUpdateFragment(idx)
        findNavController().navigate(action)
    }

    override fun deleteLostFound(idx: Int) {
        viewModel.deleteLostFound(idx)
    }

    private fun setLostInfo(lostFoundList: List<LostFound>): List<LostInfo> {
        Log.d("LostFoundFragment", "setRecyclerView()")
        Log.d("LostFoundFragment", lostFoundList.toString())
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
        Log.d("LostFoundFragment", "setRecyclerView()")
        lostFoundAdapter.submitList(list)
    }
}
