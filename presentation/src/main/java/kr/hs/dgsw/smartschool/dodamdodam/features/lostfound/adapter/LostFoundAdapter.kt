package kr.hs.dgsw.smartschool.dodamdodam.features.lostfound.adapter

import android.content.Context
import android.util.Log
import android.widget.PopupMenu
import com.bumptech.glide.Glide
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.LostFoundDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemLostAndFoundBinding
import kr.hs.dgsw.smartschool.domain.model.lostfound.LostInfo

class LostFoundAdapter(val context: Context, val listener : LostFoundCallBack) : BaseListAdapter<LostInfo, ItemLostAndFoundBinding>(R.layout.item_lost_and_found,LostFoundDiffUtilCallback){

    interface LostFoundCallBack {
        fun openComment(idx: Int)
        fun modifyLostFound(idx : Int)
        fun deleteLostFound(idx:Int)
    }

    init {
        Log.d("LostFoundAdapter","생성됨")
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
        binding.ibBtnMore.setOnClickListener{
            val pm = PopupMenu(context, binding.ibBtnMore)
            pm.inflate(R.menu.lost_found_item_menu)
            pm.setOnMenuItemClickListener{ data ->
                    when (data.itemId) {
                        R.id.apply_bus ->
                        {
                            listener.modifyLostFound(item.idx)
                            true
                        }
                        R.id.cancel_bus ->
                        {
                            listener.deleteLostFound(item.idx)
                            true
                        }
                        else -> false
                    }
                }
            pm.show()
        }
    }
}               