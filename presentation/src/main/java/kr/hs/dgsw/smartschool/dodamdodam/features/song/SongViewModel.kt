package kr.hs.dgsw.smartschool.dodamdodam.features.song

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kr.hs.dgsw.smartschool.dodamdodam.base.BaseViewModel
import kr.hs.dgsw.smartschool.domain.usecase.account.AccountUseCases
import kr.hs.dgsw.smartschool.domain.usecase.member.MemberUseCases
import kr.hs.dgsw.smartschool.domain.usecase.song.GetAllowSong
import kr.hs.dgsw.smartschool.domain.usecase.song.SongUseCases
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class SongViewModel @Inject constructor(
    private val songUseCases: SongUseCases,
    private val accountUseCases: AccountUseCases
) : BaseViewModel() {
    
    private val _getAllowSongState = MutableStateFlow<GetAllowSongState>(GetAllowSongState())
    val getAllowSongState: StateFlow<GetAllowSongState> = _getAllowSongState

    private val _getPendingSongState = MutableStateFlow<GetPendingSongState>(GetPendingSongState())
    val getPendingSongState: StateFlow<GetPendingSongState> = _getPendingSongState

    private val _getMySongState = MutableStateFlow<GetMySongState>(GetMySongState())
    val getMySongState: StateFlow<GetMySongState> = _getMySongState

    val songType = MutableLiveData(true)

    private val isGetAllowSongLoading = MutableLiveData(false)
    private val isGetPendingSongLoading = MutableLiveData(false)
    private val isGetMySongLoading = MutableLiveData(false)

    init {
        combineLoadingVariable(isGetAllowSongLoading, isGetPendingSongLoading, isGetMySongLoading)
        getTomorrowSong()
        getApplySong()
    }
    
    private fun getTomorrowSong() {
        val today = LocalDate.now().plusDays(1)
        /*songUseCases.getAllowSong(
            GetAllowSong.Params(
            year = today.year,
            month = today.monthValue,
            date = today.dayOfMonth,
        )).divideResult(
            isGetAllowSongLoading,
            { songList -> _getAllowSongState.value = GetAllowSongState(songList = songList ?: emptyList()) },
            { message -> _getAllowSongState.value = GetAllowSongState(error = message ?: "기상송을 받아오지 못했습니다.")}
        ).launchIn(viewModelScope)*/

        songUseCases.getAllowSong(GetAllowSong.Params(
            year = 2022,
            month = 6,
            date = 13,
        )).divideResult(
            isGetAllowSongLoading,
            { songList -> _getAllowSongState.value = GetAllowSongState(songList = songList ?: emptyList()) },
            { error -> _getAllowSongState.value = GetAllowSongState(error = error ?: "기상송을 받아오지 못했습니다.")}
        ).launchIn(viewModelScope)
    }

    fun getApplySong() {
        songUseCases.getPendingSong(Unit).divideResult(
            isGetPendingSongLoading,
            { songList -> _getPendingSongState.value = GetPendingSongState(songList = songList ?: emptyList()) },
            { message -> _getPendingSongState.value = GetPendingSongState(error = message ?: "기상송을 받아오지 못했습니다.")}
        ).launchIn(viewModelScope)
    }

    fun getMySong() {
        accountUseCases.getAccount(Unit).divideResult(
            isGetMySongLoading,
            { account ->
                songUseCases.getMySong(account?.id ?: "").divideResult(
                    isGetMySongLoading,
                    { songList -> _getMySongState.value = GetMySongState(songList = songList ?: emptyList()) },
                    { error -> _getMySongState.value = GetMySongState(error = error ?: "기상송을 받아오지 못했습니다.") }
                ).launchIn(viewModelScope)
            },
            { error -> _getMySongState.value = GetMySongState(error = error ?: "계정을 받아올 수 없습니다.") }
        ).launchIn(viewModelScope)
    }

    fun onClickToggle() {
        songType.value = songType.value?.not()
        viewEvent(EVENT_ON_CLICK_TOGGLE)
    }

    companion object {
        const val EVENT_ON_CLICK_TOGGLE = 1
    }
}