package ir.iact.starwarsplanets.presentation.planetdetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.iact.starwarsplanets.domain.usecase.PlanetUseCase
import ir.iact.starwarsplanets.presentation.PlanetDetailDestination
import ir.iact.starwarsplanets.presentation.planetdetail.PlanetDetailContract.Event
import ir.iact.starwarsplanets.presentation.planetdetail.PlanetDetailContract.UiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    private val planetUseCase: PlanetUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val planetName = savedStateHandle.toRoute<PlanetDetailDestination>().planetName

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
            try {
                val planet = planetUseCase.getPlanet(planetName)
                _uiState.value = UiState(planet = planet)
            } catch (e: Exception) {
                _event.send(Event.NavigateBack)
            }
        }
    }
}