package kr.hs.dgsw.smartschool.dodamdodam.features.out.write

import android.app.DatePickerDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOutWriteBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.dateFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.getDate
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.monthFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.shortToast
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearDateFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearFormat
import java.util.*

@AndroidEntryPoint
class OutWriteFragment : BaseFragment<FragmentOutWriteBinding, OutWriteViewModel>() {
    override val viewModel: OutWriteViewModel by viewModels()

    override fun observerViewModel() {

        collectOutGoingState()
        collectOutSleepingState()

        bindingViewEvent { event ->
            when (event) {
                OutWriteViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
                OutWriteViewModel.EVENT_ON_CLICK_START_OUT_GOING_DATE -> openDatePicker(0)
                OutWriteViewModel.EVENT_ON_CLICK_START_OUT_SLEEPING_DATE -> openDatePicker(1)
                OutWriteViewModel.EVENT_ON_CLICK_END_OUT_SLEEPING_DATE -> openDatePicker(2)
                OutWriteViewModel.EVENT_ON_CLICK_APPLY -> setApplyValue()
                OutWriteViewModel.EVENT_ON_ERROR -> shortToast(OutWriteViewModel.EVENT_ON_ERROR)
            }
        }
    }

    private fun collectOutGoingState() {
        lifecycleScope.launchWhenStarted {
            viewModel.applyOutGoingState.collect { state ->
                if (state.outItem != null) {
                    findNavController().popBackStack()
                }

                if (state.error.isNotBlank()) {
                    shortToast(state.error)
                }
            }
        }
    }

    private fun collectOutSleepingState() {
        lifecycleScope.launchWhenStarted {
            viewModel.applyOutSleepingState.collect { state ->
                if (state.outItem != null) {
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
            if (isOutGoing.value == true) {
                if (checkEmptyOutGoingTime().not()) return

                val startDate = makeDateFormat(startOutGoingDate.value?.yearDateFormat(), mBinding.etStartOutGoingHour.text.toString(), mBinding.etStartOutGoingMinute.text.toString())
                val endDate = makeDateFormat(startOutGoingDate.value?.yearDateFormat(), mBinding.etEndOutGoingHour.text.toString(), mBinding.etEndOutGoingMinute.text.toString())

                invalidOutGoing(startDate, endDate)

            } else if (isOutSleeping.value == true) {
                if (checkEmptyOutSleepingTime().not()) return

                val startDate = makeDateFormat(startOutSleepingDate.value?.yearDateFormat(), mBinding.etStartOutSleepingHour.text.toString(), mBinding.etStartOutSleepingMinute.text.toString())
                val endDate = makeDateFormat(endOutSleepingDate.value?.yearDateFormat(), mBinding.etEndOutSleepingHour.text.toString(), mBinding.etEndOutSleepingMinute.text.toString())

                invalidOutSleeping(startDate, endDate)
            }
        }
    }

    private fun makeDateFormat(date: String?, startTime: String, endTime: String): Date {
        return "${date}T${startTime}:${endTime}:00.000Z".getDate()
    }

    private fun checkEmptyOutGoingTime(): Boolean {
        return if (mBinding.etStartOutGoingHour.text.isEmpty() or mBinding.etStartOutGoingMinute.text.isEmpty() or mBinding.etEndOutGoingHour.text.isEmpty() or mBinding.etEndOutGoingMinute.text.isEmpty()) {
            shortToast("시간을 입력해주세요.")
            false
        } else {
            true
        }
    }

    private fun checkEmptyOutSleepingTime(): Boolean {
        return if (mBinding.etStartOutSleepingHour.text.isEmpty() or mBinding.etStartOutSleepingMinute.text.isEmpty() or mBinding.etEndOutSleepingHour.text.isEmpty() or mBinding.etEndOutSleepingMinute.text.isEmpty()) {
            shortToast("시간을 입력해주세요.")
            false
        } else {
            true
        }
    }

    private fun openDatePicker(mode: Int) {
        val targetDate = when (mode) {
            0 -> viewModel.startOutGoingDate
            1 -> viewModel.startOutSleepingDate
            2 -> viewModel.endOutSleepingDate
            else -> return
        }

        targetDate.value?.apply {
            val year = yearFormat().toInt()
            val month = monthFormat().toInt() - 1
            val date = dateFormat().toInt()

            val datePickerDialog = DatePickerDialog(
                this@OutWriteFragment.requireContext(),
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
}
