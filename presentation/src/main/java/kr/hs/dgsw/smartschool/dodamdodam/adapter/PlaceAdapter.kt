package kr.hs.dgsw.smartschool.dodamdodam.adapter

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.PlaceDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseListAdapter
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemPlaceBinding
import kr.hs.dgsw.smartschool.domain.model.place.Place

class PlaceAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val onClickCheckPlace: (place: Place) -> Unit
) : BaseListAdapter<Place, ItemPlaceBinding>(
        R.layout.item_place,
        PlaceDiffUtilCallback
) {
    companion object {
        val currentPlace = MutableLiveData<Place>()
    }

    override fun action(item: Place, binding: ItemPlaceBinding) {
        binding.checkboxPlace.text = item.name

        currentPlace.observe(lifecycleOwner) {
            binding.checkboxPlace.isChecked = item == it
        }

        binding.checkboxPlace.setOnClickListener {
            onClickCheckPlace(item)
        }
    }
}