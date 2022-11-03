package kr.hs.dgsw.smartschool.data.base

interface BaseDataSource<REMOTE, CACHE> {
    val remote: REMOTE
    val cache: CACHE
}
