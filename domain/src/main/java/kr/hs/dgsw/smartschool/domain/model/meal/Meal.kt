package kr.hs.dgsw.smartschool.domain.model.meal

import java.util.regex.Matcher
import java.util.regex.Pattern

data class Meal(
    val breakfast: String,
    val date: String,
    val dinner: String,
    val exists: Boolean,
    val lunch: String
) {

    val safeBreakfast: String
        get() = if (breakfast == "null") "조식이 없습니다." else trimMealInfo(breakfast)

    val safeLunch: String
        get() = if (lunch == "null") "중식이 없습니다." else trimMealInfo(lunch)

    val safeDinner: String
        get() = if (dinner == "null") "석식이 없습니다." else trimMealInfo(dinner)

    private fun trimMealInfo(meal: String): String =
        deleteBracket(meal).replace(" \n", ", ")

    private fun deleteBracket(text: String): String {
        val pattern: Pattern = Pattern.compile("\\([^\\(\\)]+\\)")
        val void = ""

        var matcher: Matcher = pattern.matcher(text)
        var pureText = text
        var removeTextArea = ""

        while (matcher.find()) {
            val startIndex: Int = matcher.start()
            val endIndex: Int = matcher.end()
            removeTextArea = pureText.substring(startIndex, endIndex)
            pureText = pureText.replace(removeTextArea, void)
            matcher = pattern.matcher(pureText)
        }

        return pureText
    }
}
