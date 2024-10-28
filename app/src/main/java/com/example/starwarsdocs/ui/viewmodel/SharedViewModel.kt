package com.example.starwarsdocs.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.starwarsdocs.domain.mappers.MainMapper.toData
import com.example.starwarsdocs.domain.mappers.MainMapper.toDomain
import com.example.starwarsdocs.domain.models.ApiPeopleResponseDomain
import com.example.starwarsdocs.domain.models.PeopleDomain
import com.example.starwarsdocs.domain.useCase.GetAllCharactersUseCase
import com.example.starwarsdocs.domain.useCase.GetAllLocalDataUseCase
import com.example.starwarsdocs.domain.useCase.InsertLocalCharacterUseCase
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

    private val _showLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showLoading: StateFlow<Boolean> = _showLoading.asStateFlow()

    private val _showDialog: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private val _messageError: MutableStateFlow<String> = MutableStateFlow("")
    val message: StateFlow<String> = _messageError.asStateFlow()

    private val _allCharacters: MutableStateFlow<List<PeopleDomain>?> = MutableStateFlow(null)
    val allCharacters: StateFlow<List<PeopleDomain>?> = _allCharacters.asStateFlow()

    private val _allLocalCharacters: MutableStateFlow<List<PeopleDomain>?> = MutableStateFlow(null)
    val allLocalCharacters: StateFlow<List<PeopleDomain>?> = _allLocalCharacters.asStateFlow()

    private val _selectedCharacter: MutableStateFlow<PeopleDomain?> = MutableStateFlow(null)
    val selectedCharacter: StateFlow<PeopleDomain?> = _selectedCharacter.asStateFlow()

    val endReached: MutableState<Boolean> = mutableStateOf(false)
    private var curPage = 1


    fun getAllCharacter() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when (val resp = getAllCharactersUseCase.getAllCharacters(curPage)) {
                is WrapperResponse.Success -> {
                    endReached.value = curPage * 10 >= 82
                    curPage++
                    _allCharacters.value = resp.data
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

    fun getAllLocalCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when(val resp = getAllLocalDataUseCase.getAllLocalCharacter()){
                is WrapperResponse.Success -> {
                    _allCharacters.value = resp.data?.map {
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

    fun insertLocalCharacter(peopleDomain: PeopleDomain){
        viewModelScope.launch(Dispatchers.IO) {
            _showLoading.value = true
            when(val resp = insertLocalCharacterUseCase.insertLocalCharacter(peopleDomain.toData())){
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
            when(val resp = getAllLocalDataUseCase.getAllLocalCharacter()){
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

    fun navigateToDetail(navController: NavController, character: PeopleDomain) {
        _selectedCharacter.value = character
        navController.navigate(Screen.Detail.route)
    }

}