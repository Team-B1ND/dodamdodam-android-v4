package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ActivitySignUpBinding
import java.util.ArrayList

class SignUpPagerAdapter (
    fragmentActivity: FragmentActivity
): FragmentStateAdapter(fragmentActivity) {
    val fragmentList = listOf<Fragment>()
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}