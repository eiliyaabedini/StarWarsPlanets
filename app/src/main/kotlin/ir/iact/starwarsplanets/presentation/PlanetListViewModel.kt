package ir.iact.starwarsplanets.presentation

import androidx.lifecycle.ViewModel
import ir.iact.starwarsplanets.presentation.model.PlanetListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PlanetListViewModel : ViewModel() {

    private var _uiState = MutableStateFlow(PlanetListUiState.Empty)

    val uiState = _uiState.asStateFlow()
}