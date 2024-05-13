package com.derandecker.superheroapp

import com.derandecker.superheroapp.model.SuperHero

sealed interface SuperHeroUiState {
    data class Success(val hero: SuperHero) : SuperHeroUiState
    data object Loading : SuperHeroUiState
    data object Error : SuperHeroUiState
}
