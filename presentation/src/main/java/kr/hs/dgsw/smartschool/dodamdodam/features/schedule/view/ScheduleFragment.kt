package kr.hs.dgsw.smartschool.dodamdodam.features.schedule.view

import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.view.MonthDayBinder
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentScheduleBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.schedule.container.DayViewContainer
import kr.hs.dgsw.smartschool.dodamdodam.features.schedule.viewmodel.ScheduleViewModel

class ScheduleFragment : BaseFragment<FragmentScheduleBinding, ScheduleViewModel>() {
    override val viewModel: ScheduleViewModel by viewModels()

    override fun observerViewModel() {

        bindingViewEvent { event ->
            when(event) {
                ScheduleViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }

        mBinding.calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            // Called only when a new container is needed.
            override fun create(view: View) = DayViewContainer(view)

            // Called every time we need to reuse a container.
            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.textView.text = data.date.dayOfMonth.toString()
            }
        }
    }

}