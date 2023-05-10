package kr.hs.dgsw.smartschool.dodamdodam.features.nightstudy.write

import android.app.DatePickerDialog
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentNightStudyWriteBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.dateFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.monthFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearFormat
import java.util.Calendar

@AndroidEntryPoint
class NightStudyWriteFragment :
    BaseFragment<FragmentNightStudyWriteBinding, NightStudyWriteViewModel>() {
    override val viewModel: NightStudyWriteViewModel by viewModels()
    private lateinit var nightStudyAdapter: ArrayAdapter<String>

    override fun observerViewModel() {
        collectNightStudyState()
        openStudyRoomList()
        bindingViewEvent { event ->
            when (event) {
                NightStudyWriteViewModel.ON_CLICK_BACK -> findNavController().popBackStack()
                NightStudyWriteViewModel.ON_CLICK_START_NIGHT_STUDY_DATE -> openDatePicker(0)
                NightStudyWriteViewModel.ON_CLICK_END_NIGHT_STUDY_DATE -> openDatePicker(1)
                NightStudyWriteViewModel.ON_CLICK_NIGHT_STUDY_WRITE -> setApplyValue()
                NightStudyWriteViewModel.ON_CLICK_IS_NEED_PHONE -> setIsNeedPhone()
                NightStudyWriteViewModel.ON_ERROR -> shortToast(NightStudyWriteViewModel.ON_ERROR)
            }
        }
    }

    private fun openStudyRoomList() {
        val items = resources.getStringArray(R.array.night_study_room_array)

        nightStudyAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, items)

        mBinding.spinner.adapter = nightStudyAdapter

        mBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                setPlace(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun setPlace(index: Int) {
        lifecycleScope.launchWhenStarted {
            viewModel.getPlaceState.collectLatest {
                viewModel.placeId = it.place[index].id
                viewModel.placeName = it.place[index].name
            }
        }
    }

    private fun setIsNeedPhone() {
        viewModel.isNeedPhone.value = !viewModel.isNeedPhone.value!!
    }

    private fun collectNightStudyState() {
        lifecycleScope.launchWhenStarted {
            viewModel.nightStudyWriteState.collect { state ->
                if (state.nightStudyItem != null) {
                    findNavController().popBackStack()
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun setApplyValue() {
        with(viewModel) {
            invalidNightStudy(
                startDate = startNightStudyDate.value,
                endDate = endNightStudyDate.value,
                id = placeId
            )
        }
    }

    private fun openDatePicker(mode: Int) {
        val targetDate = when (mode) {
            0 -> viewModel.startNightStudyDate
            1 -> viewModel.endNightStudyDate
            else -> return
        }

        targetDate.value?.apply {
            val year = yearFormat().toInt()
            val month = monthFormat().toInt() - 1
            val date = dateFormat().toInt()

            val datePickerDialog = DatePickerDialog(
                this@NightStudyWriteFragment.requireContext(),
                R.style.MyDatePickerDialogTheme,
                { _, y, m, d ->
                    val cal = Calendar.getInstance()
                    cal.set(y, m, d)
                    targetDate.value = cal.time
                }, year, month, date
            )
            datePickerDialog.show()
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.main))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.main))
        }
        Log.d(
            "NightStudy222",
            viewModel.startNightStudyDate.value.toString() + " " + viewModel.endNightStudyDate.value.toString()
        )
    }
}
