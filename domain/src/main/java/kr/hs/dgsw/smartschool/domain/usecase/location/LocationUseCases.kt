package kr.hs.dgsw.smartschool.domain.usecase.location

data class LocationUseCases(
    val getMyLocation: GetMyLocation,
    val postLocation: PostLocation,
    val putLocation: PutLocation,
    val deleteLocation: DeleteLocation
)
