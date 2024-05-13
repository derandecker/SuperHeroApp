package com.derandecker.superheroapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.derandecker.superheroapp.network.NetworkApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.HttpException
import retrofit2.Retrofit
import java.io.IOException

class MainViewModel : ViewModel() {

    var superHeroUiState: SuperHeroUiState by mutableStateOf(SuperHeroUiState.Loading)
        private set

    fun getSuperHero(id: String) {
        viewModelScope.launch {
            // sets the value of superHeroUiState to either the last expression in the
            // try block or the catch block, depending on if there is an error caught
            superHeroUiState =
                try {
                    val retrofit = Retrofit.Builder()
                        .baseUrl("https://superheroapi.com/api/")
                        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
                        .build()
                    val service: NetworkApi = retrofit.create(NetworkApi::class.java)
                    SuperHeroUiState.Success(service.getUser(BuildConfig.API_KEY, id = id))
                } catch (e: IOException) {
                    SuperHeroUiState.Error
                } catch (e: HttpException) {
                    SuperHeroUiState.Error
                }
        }
    }
}
