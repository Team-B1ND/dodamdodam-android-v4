package kr.hs.dgsw.smartschool.dodamdodam.features.schedule.container

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendar.view.ViewContainer
import kr.hs.dgsw.smartschool.dodamdodam.R

class DayViewContainer(view: View) : ViewContainer(view) {
    val textView = view.findViewById<TextView>(R.id.calendarDayText)
}