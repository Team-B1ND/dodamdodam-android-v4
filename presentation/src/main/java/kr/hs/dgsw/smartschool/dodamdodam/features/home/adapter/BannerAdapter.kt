package kr.hs.dgsw.smartschool.dodamdodam.features.home.adapter

import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemBannerBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.home.adapter.callback.BannerDiffUtilCallback
import kr.hs.dgsw.smartschool.domain.model.banner.Banner

class BannerAdapter(private val action: (url: String) -> Unit) : BaseListAdapter<Banner, ItemBannerBinding>(R.layout.item_banner, BannerDiffUtilCallback) {
    override fun action(item: Banner, binding: ItemBannerBinding) {
        binding.banner = item

        binding.root.setOnClickListener {
            action.invoke(item.redirectUrl)
        }
    }
}
