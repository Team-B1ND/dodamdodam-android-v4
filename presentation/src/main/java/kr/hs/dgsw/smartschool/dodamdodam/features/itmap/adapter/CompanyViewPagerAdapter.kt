package kr.hs.dgsw.smartschool.dodamdodam.features.itmap.adapter

import android.view.View
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemCompanyBinding
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemVpCompanyBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.itmap.adapter.callback.CompanyDiffUtilCallback
import kr.hs.dgsw.smartschool.domain.model.itmap.Company

class CompanyViewPagerAdapter(val action: (id: Int) -> Unit) : BaseListAdapter<Company, ItemVpCompanyBinding>(R.layout.item_vp_company, CompanyDiffUtilCallback) {

    override fun action(item: Company, binding: ItemVpCompanyBinding) {

        if (item.symbolLogo != null) {
            setLogo(binding, item.symbolLogo!!)
        } else if (item.textLogo != null) {
            setLogo(binding, item.textLogo!!)
        } else {
            binding.layoutCompanyIcon.visibility = View.VISIBLE
            binding.ivCompanyLogo.visibility = View.GONE
        }

        binding.company = item
        binding.root.setOnClickListener {
            action.invoke(item.id)
        }
    }

    private fun setLogo(binding: ItemVpCompanyBinding, url: String) {
        binding.ivCompanyLogo.visibility = View.VISIBLE
        binding.layoutCompanyIcon.visibility = View.INVISIBLE
        Glide.with(binding.ivCompanyLogo.context)
            .load(url)
            .error(R.drawable.default_img)
            .into(binding.ivCompanyLogo)
    }
}
