package kr.hs.dgsw.smartschool.data.util

object SongUtils {
    val CHART_RANGE: IntRange = IntRange(0, 49)
    const val SONG_CHART_URL = "https://www.melon.com/chart/"
    const val SONG_TITLE_CSS_QUERY = "#lst50 > td:nth-child(6) > div > div > div.ellipsis.rank01 > span > a"
    const val SONG_ARTIST_CSS_QUERY = "#lst50 > td:nth-child(6) > div > div > div.ellipsis.rank02 > a"
    const val SONG_THUMBNAIL_CSS_QUERY = "#lst50 > td:nth-child(4) > div > a > img"
    const val SONG_THUMBNAIL_ATTR_KEY = "src"
}
