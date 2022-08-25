package kr.hs.dgsw.smartschool.domain.model.place

data class Place(
    val idx: Int,
    val name: String
) {
    constructor() : this(0, "")

    override fun toString(): String {
        return name
    }
}
