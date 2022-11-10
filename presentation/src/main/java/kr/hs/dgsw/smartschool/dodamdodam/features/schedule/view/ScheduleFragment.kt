package kr.hs.dgsw.smartschool.dodamdodam.features.schedule.view

import android.graphics.Color
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.kizitonwose.calendar.core.CalendarDay
import com.kizitonwose.calendar.core.DayPosition
import com.kizitonwose.calendar.core.firstDayOfWeekFromLocale
import com.kizitonwose.calendar.view.MonthDayBinder
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseFragment
import kr.hs.dgsw.smartschool.dodamdodam.databinding.FragmentScheduleBinding
import kr.hs.dgsw.smartschool.dodamdodam.features.schedule.container.DayViewContainer
import kr.hs.dgsw.smartschool.dodamdodam.features.schedule.viewmodel.ScheduleViewModel
import java.time.YearMonth

class ScheduleFragment : BaseFragment<FragmentScheduleBinding, ScheduleViewModel>() {
    override val viewModel: ScheduleViewModel by viewModels()

    override fun observerViewModel() {

        bindingViewEvent { event ->
            when(event) {
                ScheduleViewModel.EVENT_ON_CLICK_BACK -> findNavController().popBackStack()
            }
        }

        mBinding.calendarView.dayBinder = object : MonthDayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)

            override fun bind(container: DayViewContainer, data: CalendarDay) {
                container.textView.text = data.date.dayOfMonth.toString()
                if (data.position == DayPosition.MonthDate) {
                    container.textView.setTextColor(Color.BLACK)
                } else {
                    container.textView.setTextColor(Color.GRAY)
                }
            }
        }

        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(100)
        val endMonth = currentMonth.plusMonths(100)
        val firstDayOfWeek = firstDayOfWeekFromLocale()
        mBinding.calendarView.setup(startMonth, endMonth, firstDayOfWeek)
        mBinding.calendarView.scrollToMonth(currentMonth)
    }

}