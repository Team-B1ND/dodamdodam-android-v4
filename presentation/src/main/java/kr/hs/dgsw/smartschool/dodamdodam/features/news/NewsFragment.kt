package kr.hs.dgsw.smartschool.dodamdodam.features.news

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentNewsBinding

class NewsFragment : BaseFragment<FragmentNewsBinding, NewsViewModel>() {
    override val viewModel: NewsViewModel by viewModels()
    override val hasBottomNav: Boolean = true

    private val tabList = listOf<Fragment>(AllNewsFragment(), MyNewsFragment())

    override fun observerViewModel() {
        selectTab()
    }

    private fun selectTab() {
        mBinding.tabNews.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                transactionFragment(tab?.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun transactionFragment(position: Int?) {
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.fcb_news_tab_container, tabList[position ?: 0], tabList[position ?: 0].tag)
        transaction.commit()
    }

    override fun bindingViewEvent() {}
}