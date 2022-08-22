package kr.hs.dgsw.smartschool.dodamdodam.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.adapter.callback.PlaceDiffUtilCallback
import kr.hs.dgsw.smartschool.dodamdodam.databinding.ItemPlaceBinding
import kr.hs.dgsw.smartschool.domain.model.place.Place

class PlaceAdapter(
    private val lifecycleOwner: LifecycleOwner,
    private val onClickCheckPlace: (place: Place) -> Unit
) :
    ListAdapter<Place, PlaceAdapter.PlaceViewHolder>(PlaceDiffUtilCallback) {

    inner class PlaceViewHolder(private val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(place: Place) {
            binding.checkboxPlace.text = place.name

            currentPlace.observe(lifecycleOwner) {
                binding.checkboxPlace.isChecked = place == it
            }

            binding.checkboxPlace.setOnClickListener {
                onClickCheckPlace(place)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_place,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        val currentPlace = MutableLiveData<Place>()
    }
}
