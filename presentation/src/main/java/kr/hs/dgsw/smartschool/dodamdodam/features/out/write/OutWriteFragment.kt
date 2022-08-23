package kr.hs.dgsw.smartschool.dodamdodam.features.out.write

import android.app.DatePickerDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentOutWriteBinding
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.dateFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.monthFormat
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.yearFormat
import java.util.*

class OutWriteFragment : BaseFragment<FragmentOutWriteBinding, OutWriteViewModel>() {
    override val viewModel: OutWriteViewModel by viewModels()

    override fun observerViewModel() {

        bindingViewEvent { event ->
            when (event) {
                OutWriteViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
                OutWriteViewModel.EVENT_ON_CLICK_START_OUT_GOING_DATE -> openDatePicker(0)
                OutWriteViewModel.EVENT_ON_CLICK_START_OUT_SLEEPING_DATE -> openDatePicker(1)
                OutWriteViewModel.EVENT_ON_CLICK_END_OUT_SLEEPING_DATE -> openDatePicker(2)
            }
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

            DatePickerDialog(this@OutWriteFragment.requireContext(), { _, y, m, d ->
                val cal = Calendar.getInstance()
                cal.set(y, m, d)
                targetDate.value = cal.time
            }, year, month, date).show()
        }
    }
}
