package kr.hs.dgsw.smartschool.dodamdodam.features.itmap.detail.adapter

import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemItmapUserBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.itmap.detail.adapter.callback.ItMapUserDiffUtilCallback
import kr.hs.dgsw.smartschool.domain.model.itmap.ItMapUser

class ItMapUserAdapter(val action: (link: String) -> Unit) : BaseListAdapter<ItMapUser, ItemItmapUserBinding>(R.layout.item_itmap_user, ItMapUserDiffUtilCallback) {

    override fun action(item: ItMapUser, binding: ItemItmapUserBinding) {
        binding.user = item
        binding.tvGitLink.setOnClickListener {
            action.invoke("https://github.com/${item.githubId}")
        }
    }
}
