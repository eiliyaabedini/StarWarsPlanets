package ir.iact.starwarsplanets.presentation.planetlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.iact.starwarsplanets.domain.usecase.PlanetUseCase
import ir.iact.starwarsplanets.presentation.PlanetDetailDestination
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListContract.Event
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListContract.UiInteraction
import ir.iact.starwarsplanets.presentation.planetlist.PlanetListContract.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetListViewModel @Inject constructor(
    private val planetUseCase: PlanetUseCase
) : ViewModel() {

    private var _event = Channel<Event>()

    val event = _event.receiveAsFlow()

    private var _uiState = MutableStateFlow(UiState.Empty)

    val uiState = _uiState
        .onStart { initiateData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = UiState.Empty
        )

    private fun initiateData() {
        viewModelScope.launch {
            _uiState.value = UiState(isLoading = true, planets = emptyList())
            try {
                val planets = planetUseCase.getPlanets()
                _uiState.value = UiState(isLoading = false, planets = planets)
            } catch (e: Exception) {
                _uiState.value = UiState(
                    isLoading = false,
                    hasError = e.message,
                    planets = emptyList()
                )
            }
        }
    }

    fun onUiInteraction(uiInteraction: UiInteraction) {
        when (uiInteraction) {
            is UiInteraction.OnPlanetClicked -> {
                viewModelScope.launch {
                    _event.send(Event.NavigateToPlanetDetail(PlanetDetailDestination(uiInteraction.planet.name)))
                }
            }

            UiInteraction.OnRetryClicked -> initiateData()
        }
    }
}