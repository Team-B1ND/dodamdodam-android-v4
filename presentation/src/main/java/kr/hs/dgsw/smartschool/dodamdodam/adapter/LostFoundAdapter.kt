package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.content.Context
import android.view.View
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.LostFoundDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemLostAndFoundBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.LostFoundFragment
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

class LostFoundAdapter(val context: Context, val listener : LostFoundCallBack) : BaseListAdapter<LostInfo, ItemLostAndFoundBinding>(R.layout.item_lost_and_found,LostFoundDiffUtilCallback){

    interface LostFoundCallBack {
        fun openComment(idx: Int)
    }

    override fun action(item: LostInfo, binding: ItemLostAndFoundBinding) {
        binding.lostInfo = item
        Glide.with(binding.ivProfileImage)
            .load(item.img)
            .centerCrop()
            .error(R.drawable.default_user)
            .into(binding.ivProfileImage)

        binding.tvComment.setOnClickListener{
            listener.openComment(item.idx)
        }
    }
}               