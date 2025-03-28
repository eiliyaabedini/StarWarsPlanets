package ir.iact.starwarsplanets.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.iact.starwarsplanets.domain.usecase.PlanetUseCase
import ir.iact.starwarsplanets.presentation.model.PlanetListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlanetListViewModel(
    private val planetUseCase: PlanetUseCase
) : ViewModel() {

    private var _uiState = MutableStateFlow(PlanetListUiState.Empty)

    val uiState = _uiState
        .onStart { initiateData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = PlanetListUiState.Empty
        )

    private fun initiateData() {
        viewModelScope.launch {
            _uiState.value = PlanetListUiState(isLoading = true, planets = emptyList())
            try {
                val planets = planetUseCase.getPlanets()
                _uiState.value = PlanetListUiState(isLoading = false, planets = planets)
            } catch (e: Exception) {
                _uiState.value = PlanetListUiState(
                    isLoading = false,
                    hasError = e.message,
                    planets = emptyList()
                )
            }
        }
    }
}