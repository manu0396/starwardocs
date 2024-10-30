package com.example.starwarsdocs.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.starwarsdocs.domain.mappers.MainMapper.toData
import com.example.starwarsdocs.domain.mappers.MainMapper.toDataModel
import com.example.starwarsdocs.domain.mappers.MainMapper.toDomain
import com.example.starwarsdocs.domain.mappers.MainMapper.toDomainModel
import com.example.starwarsdocs.domain.models.PeopleDomain
import com.example.starwarsdocs.domain.models.PlanetsDomain
import com.example.starwarsdocs.domain.models.StarShipDomain
import com.example.starwarsdocs.domain.useCase.GetAllCharactersUseCase
import com.example.starwarsdocs.domain.useCase.GetAllLocalDataUseCase
import com.example.starwarsdocs.domain.useCase.GetAllPlanetsUseCase
import com.example.starwarsdocs.domain.useCase.GetAllStarShipUseCase
import com.example.starwarsdocs.domain.useCase.InsertLocalCharacterUseCase
import com.example.starwarsdocs.domain.useCase.InsertLocalPlanetUseCase
import com.example.starwarsdocs.domain.useCase.InsertLocalStarshipUseCase
import com.example.starwarsdocs.ui.navigation.Screen
import com.example.starwarsdocs.util.WrapperResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    @ApplicationContext context: Context
) : ViewModel() {

    @Inject
    lateinit var getAllCharactersUseCase: GetAllCharactersUseCase

    @Inject
    lateinit var getAllLocalDataUseCase: GetAllLocalDataUseCase

    @Inject
    lateinit var insertLocalCharacterUseCase: InsertLocalCharacterUseCase

    @Inject
    lateinit var getAllPlanetsUseCase: GetAllPlanetsUseCase

    @Inject
    lateinit var getAllStarShipUseCase: GetAllStarShipUseCase

    @Inject
    lateinit var insertLocalPlanetUseCase: InsertLocalPlanetUseCase

    @Inject
    lateinit var insertLocalStarshipUseCase: InsertLocalStarshipUseCase

    private val _showLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showLoading: StateFlow<Boolean> = _showLoading.asStateFlow()

    private val _showDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private val _messageError: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String> = _messageError.asStateFlow()

    private val _allLocalCharacters: MutableStateFlow<List<PeopleDomain>?> = MutableStateFlow(null)
    val allLocalCharacters: StateFlow<List<PeopleDomain>?> = _allLocalCharacters.asStateFlow()

    private val _allPlanets: MutableStateFlow<List<PlanetsDomain>?> = MutableStateFlow(null)
    val allPlanets: StateFlow<List<PlanetsDomain>?> = _allPlanets.asStateFlow()

    private val _allStarShips: MutableStateFlow<List<StarShipDomain>?> = MutableStateFlow(null)
    val allStarShips: StateFlow<List<StarShipDomain>?> = _allStarShips.asStateFlow()

    private val _selectedCharacter: MutableStateFlow<PeopleDomain?> = MutableStateFlow(null)
    val selectedCharacter: StateFlow<PeopleDomain?> = _selectedCharacter.asStateFlow()

    private val _selectedPlanet: MutableStateFlow<PlanetsDomain?> = MutableStateFlow(null)
    val selectedPlanet: StateFlow<PlanetsDomain?> = _selectedPlanet.asStateFlow()

    private val _selectedStarship: MutableStateFlow<StarShipDomain?> = MutableStateFlow(null)
    val selectedStarship: StateFlow<StarShipDomain?> = _selectedStarship.asStateFlow()


    val endReached: MutableState<Boolean> = mutableStateOf(false)
    private var curPage = 1


    fun getAllCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when (val resp = getAllCharactersUseCase.getAllCharacters(curPage)) {
                is WrapperResponse.Success -> {
                    endReached.value = curPage * 10 >= 82
                    curPage++
                    resp.data?.map {
                        insertLocalCharacter(it)
                    }
                    getLocalCharacters()
                    _showLoading.value = false
                }

                is WrapperResponse.Error -> {
                    _showLoading.value = false
                    _showDialog.value = true
                    _messageError.value = resp.message ?: "Se ha producido un error"
                }
            }
        }
    }

    fun getAllPlanets() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when (val resp = getAllPlanetsUseCase.getAllPlanets(curPage)) {
                is WrapperResponse.Success -> {
                    endReached.value = curPage * 10 >= 82
                    curPage++
                    resp.data?.map {
                        insertLocalPlanet(it)
                    }
                    getLocalPlanets()
                    _showLoading.value = false
                }

                is WrapperResponse.Error -> {
                    _showLoading.value = false
                    _showDialog.value = true
                    _messageError.value = resp.message ?: "Se ha producido un error"
                }
            }
        }
    }




    fun getAllStarships() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when(val resp = getAllStarShipUseCase.getAllStarShip(curPage)){
                is WrapperResponse.Success -> {
                    endReached.value = curPage * 10 >= 82
                    curPage++
                    resp.data?.map {
                        insertLocalStarShip(it)
                    }
                    getLocalStarships()
                    _showLoading.value = false
                }
                is WrapperResponse.Error -> {
                    _showLoading.value = false
                    _showDialog.value = true
                    _messageError.value = resp.message ?: "Se ha producido un error"
                }
            }
        }
    }

    private fun getLocalPlanets() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when(val resp = getAllLocalDataUseCase.getAllLocalPlanets()){
                is WrapperResponse.Success -> {
                    _allPlanets.value = resp.data?.map {
                        it.toDomainModel()
                    }
                }
                is WrapperResponse.Error -> {
                    _showLoading.value = false
                    _showDialog.value = true
                    _messageError.value = resp.message ?: "Se ha producido un error"
                }
            }
        }
    }

    private fun getLocalStarships() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when(val resp = getAllLocalDataUseCase.getAllLocalStarships()){
                is WrapperResponse.Success -> {
                    _allStarShips.value = resp.data?.map {
                        it.toDomainModel()
                    }
                    _showLoading.value = false
                }
                is WrapperResponse.Error -> {
                    _showLoading.value = false
                    _showDialog.value = true
                    _messageError.value = resp.message ?: "Se ha producido un error"
                }
            }
        }
    }

    fun getAllLocalCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when (val resp = getAllLocalDataUseCase.getAllLocalCharacter()) {
                is WrapperResponse.Success -> {
                    _allLocalCharacters.value = resp.data?.map {
                        it.toDomain()
                    }
                    _showLoading.value = false
                }

                is WrapperResponse.Error -> {
                    _showLoading.value = false
                    _showDialog.value = true
                    _messageError.value = resp.message ?: "Se ha producido un error"
                }
            }
        }
    }

    fun insertLocalCharacter(peopleDomain: PeopleDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when (val resp =
                insertLocalCharacterUseCase.insertLocalCharacter(peopleDomain.toData())) {
                is WrapperResponse.Success -> {
                    _showLoading.value = false
                }

                is WrapperResponse.Error -> {
                    _showLoading.value = false
                    _showDialog.value = true
                    _messageError.value = resp.message ?: "Se ha producido un error"
                }
            }
        }
    }

    private fun insertLocalPlanet(planet: PlanetsDomain) {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when(val resp = insertLocalPlanetUseCase.insertPlanet(planet.toDataModel())){
                is WrapperResponse.Success -> {
                    _showLoading.value = false
                }
                is WrapperResponse.Error -> {
                    _showLoading.value = false
                    _showDialog.value = true
                    _messageError.value = resp.message ?: "Se ha producido un error"
                }
            }
        }
    }

    private fun insertLocalStarShip(starShip: StarShipDomain){
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when(val resp = insertLocalStarshipUseCase.insertStarship(starShip.toDataModel())){
                is WrapperResponse.Success -> {
                    _showLoading.value = false
                }
                is WrapperResponse.Error -> {
                    _showLoading.value = false
                    _showDialog.value = true
                    _messageError.value = resp.message ?: "Se ha producido un error"
                }
            }
        }
    }

    fun onDialogConfirm() {
        _showDialog.value = false
    }

    fun onDialogDismiss() {
        _showDialog.value = false
    }

    fun setSelectedCharacter(character: PeopleDomain) {
        _selectedCharacter.value = character
    }

    fun getLocalCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when (val resp = getAllLocalDataUseCase.getAllLocalCharacter()) {
                is WrapperResponse.Success -> {
                    _allLocalCharacters.value = resp.data?.map { it.toDomain() }
                    _showLoading.value = false
                }

                is WrapperResponse.Error -> {
                    _showLoading.value = false
                    _showDialog.value = true
                    _messageError.value = resp.message ?: "Se ha producido un error"
                }
            }
        }
    }

    fun navigateToDetailCharacter(navController: NavController, character: PeopleDomain) {
        _selectedCharacter.value = character
        navController.navigate(Screen.Detail.route)
    }

    fun navigateToDetailPlanet(navController: NavController, planet: PlanetsDomain) {
        _selectedPlanet.value = planet
        navController.navigate(Screen.Detail.route)
    }
    fun navigateToDetailStarShip(navController: NavController, starship: StarShipDomain) {
        _selectedStarship.value = starship
        navController.navigate(Screen.Detail.route)
    }


}