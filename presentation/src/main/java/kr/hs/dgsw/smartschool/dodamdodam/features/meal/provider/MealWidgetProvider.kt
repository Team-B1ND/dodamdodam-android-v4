package kr.hs.dgsw.smartschool.dodamdodam.features.meal.provider

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kr.hs.dgsw.smartschool.dodamdodam.R
import kr.hs.dgsw.smartschool.dodamdodam.features.main.MainActivity
import kr.hs.dgsw.smartschool.domain.usecase.meal.GetMeal
import kr.hs.dgsw.smartschool.domain.usecase.meal.MealUseCases
import kr.hs.dgsw.smartschool.domain.util.Resource
import java.time.LocalDate
import javax.inject.Inject

@AndroidEntryPoint
class MealWidgetProvider: AppWidgetProvider() {

    @Inject
    lateinit var mealUseCases: MealUseCases

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        Log.e("TestTest", "onUpdate: 아니 이건 아니지??")
        appWidgetIds.forEach {
            updateAppWidget(context, appWidgetManager, it)
            updateMealInfo(context, appWidgetManager, it)
        }
    }

    internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val pendingIntent: PendingIntent = Intent(context, MainActivity::class.java)
            .let { intent ->
                PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
            }

        val remoteView = RemoteViews(context.packageName, R.layout.widget_meal)

        remoteView.setOnClickPendingIntent(R.id.layout_meal, pendingIntent)
        remoteView.setTextViewText(R.id.tv_lunch, "ㅋㅋㅋㅋ")

        appWidgetManager.updateAppWidget(appWidgetId, remoteView)
    }

    internal fun updateMealInfo(context: Context, appWidgetManager: AppWidgetManager, appWidgetId: Int) {
        val today = LocalDate.now()
        mealUseCases.getMeal(GetMeal.Params(
            today.year, today.monthValue, today.dayOfMonth
        )).onEach { resource ->
            when (resource) {
                is Resource.Success -> {
                    val remoteView = RemoteViews(context.packageName, R.layout.widget_meal)
                    remoteView.setTextViewText(R.id.tv_breakfast, resource.data?.safeBreakfast)
                    remoteView.setTextViewText(R.id.tv_lunch, resource.data?.safeLunch)
                    remoteView.setTextViewText(R.id.tv_dinner, resource.data?.safeDinner)

                    appWidgetManager.updateAppWidget(appWidgetId, remoteView)
                }
                else -> {}
            }
        }

    }

}