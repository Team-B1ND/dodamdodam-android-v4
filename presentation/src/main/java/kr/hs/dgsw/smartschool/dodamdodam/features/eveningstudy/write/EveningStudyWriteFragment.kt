package kr.hs.dgsw.smartschool.dodamdodam.features.eveningstudy.write

import android.app.DatePickerDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentEveningStudyWriteBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.out.write.OutWriteViewModel
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.dateFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.monthFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearFormat
import java.util.Calendar

@AndroidEntryPoint
class EveningStudyWriteFragment :
    BaseFragment<FragmentEveningStudyWriteBinding, EveningStudyWriteViewModel>() {

    override val viewModel: EveningStudyWriteViewModel by viewModels()
//    private val args: EveningStudyWriteFragmentArgs by navArgs()
    override fun observerViewModel() {
//        args.eveningStudyItem?.let {
//            setModifyEveningStudyData(it)
//        }

        collectEveningStudyState()

        bindingViewEvent { event ->
            when (event) {
                EveningStudyWriteViewModel.ON_CLICK_BACK -> findNavController().popBackStack()
                EveningStudyWriteViewModel.ON_CLICK_START_EVENING_STUDY_DATE -> openDatePicker(0)
                EveningStudyWriteViewModel.ON_CLICK_END_EVENING_STUDY_DATE -> openDatePicker(1)
                EveningStudyWriteViewModel.ON_CLICK_EVENING_STUDY_WRITE -> setApplyValue()
                EveningStudyWriteViewModel.ON_CLICK_STUDY_ROOM -> openStudyRoomList()
                EveningStudyWriteViewModel.ON_ERROR -> shortToast(OutWriteViewModel.EVENT_ON_ERROR)
            }
        }
    }

    private fun openStudyRoomList() {
        findNavController().navigate(
            EveningStudyWriteFragmentDirections.actionEveningStudyWriteFragmentToStudyRoomApplyFragment(isEveningStudyPlace = true)
        )
    }

    private fun collectEveningStudyState() {
        lifecycleScope.launchWhenStarted {
            viewModel.applyEveningStudyState.collect { state ->
                if (state.eveningStudyItem != null) {
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
            invalidEveningStudy(startDate = startEveningStudyDate.value, endDate = endEveningStudyDate.value)
        }
    }

    private fun openDatePicker(mode: Int) {
        val targetDate = when (mode) {
            0 -> viewModel.startEveningStudyDate
            1 -> viewModel.endEveningStudyDate
            else -> return
        }

        targetDate.value?.apply {
            val year = yearFormat().toInt()
            val month = monthFormat().toInt() - 1
            val date = dateFormat().toInt()

            val datePickerDialog = DatePickerDialog(
                this@EveningStudyWriteFragment.requireContext(),
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
    }

//    private fun setModifyEveningStudyData(eveningStudyItem: EveningStudyItem) {
//        with(viewModel) {
//            isModifyEveningStudy.value = true
//            id.value = eveningStudyItem.id
//            mBinding.tvOutWrite.text = "심자 수정"
//            mBinding.btnAddConfirm.text = "수정"
//
//            eveningStudyReason.value = eveningStudyItem.reason
//            eveningStudyPhoneReason.value = eveningStudyItem.phoneReason
//            startEveningStudyDate.value = eveningStudyItem.startDate.getYearDateDate()
//            endEveningStudyDate.value = eveningStudyItem.endDate.getYearDateDate()
//        }
//    }
}
