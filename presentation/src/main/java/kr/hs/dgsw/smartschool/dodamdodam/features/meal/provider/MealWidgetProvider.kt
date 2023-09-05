package kr.hs.dgsw.smartschool.dodamdodam.features.meal.provider

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.dodamdodam.widget.extension.timeFormat
import kr.hs.dgsw.smartschool.domain.model.meal.Meal
import kr.hs.dgsw.smartschool.domain.repository.MealRepository
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject

@AndroidEntryPoint
class MealWidgetProvider : AppWidgetProvider() {
    @Inject
    lateinit var mealRepository: MealRepository

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)

        val date = LocalDate.now()

        CoroutineScope(Dispatchers.IO).launch {
            val meal = async {
                mealRepository.getMeal(date.year, date.monthValue, date.dayOfMonth)
            }
            launch(Dispatchers.Main) {
                updateWidget(context, appWidgetManager, appWidgetIds, meal.await())
            }
        }
    }

    private fun updateWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray,
        meal: Meal
    ) {
        val remoteViews = RemoteViews(context.packageName, R.layout.meal_widget).apply {
            val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                Intent(context, MainActivity::class.java),
                PendingIntent.FLAG_IMMUTABLE
            )
            setOnClickPendingIntent(R.id.meal_layout, pendingIntent)

            val currentTime = Date().timeFormat()

            if (currentTime < "09:00") {
                setTextViewText(R.id.tv_meal_name, "조식")
                setImageViewResource(R.id.iv_meal, R.drawable.ic_breakfast)
                setTextViewText(R.id.tv_meal, meal.safeBreakfast)
                setInt(R.id.tv_meal_name, "setBackgroundResource", R.drawable.background_meal_breakfast)
            } else if (currentTime < "13:20") {
                setTextViewText(R.id.tv_meal_name, "중식")
                setImageViewResource(R.id.iv_meal, R.drawable.ic_lunch)
                setTextViewText(R.id.tv_meal, meal.safeLunch)
                setInt(R.id.tv_meal_name, "setBackgroundResource", R.drawable.background_meal_lunch)
            } else {
                setTextViewText(R.id.tv_meal_name, "석식")
                setImageViewResource(R.id.iv_meal, R.drawable.ic_dinner)
                setInt(R.id.tv_meal_name, "setBackgroundResource", R.drawable.background_meal_dinner)
            }
        }

        appWidgetIds.forEach { appWidgetId ->
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews)
        }
    }
}
