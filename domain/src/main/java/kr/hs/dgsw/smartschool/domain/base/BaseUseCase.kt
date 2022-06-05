package kr.hs.dgsw.smartschool.domain.base

abstract class BaseUseCase<RE> {
    abstract val repository: RE
}