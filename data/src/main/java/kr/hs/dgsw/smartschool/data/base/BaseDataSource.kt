package kr.hs.dgsw.smartschool.data.base

abstract class BaseDataSource<RT, CH> {
    abstract val remote: RT
    abstract val cache: CH
}