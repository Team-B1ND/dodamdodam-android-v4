package kr.hs.dgsw.smartschool.dodamdodam.features.itmap.adapter

import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemCompanyBinding
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemVpCompanyBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.itmap.adapter.callback.CompanyDiffUtilCallback
import kr.hs.dgsw.smartschool.domain.model.itmap.Company

class CompanyAdapter : BaseListAdapter<Company, ItemCompanyBinding>(R.layout.item_company, CompanyDiffUtilCallback) {

    override fun action(item: Company, binding: ItemCompanyBinding) {
        binding.company = item
    }

}