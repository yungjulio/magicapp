package com.example.magicapp

import android.app.Application
import androidx.lifecycle.*
import com.example.magicapp.data.local.AppDatabase.Companion.getDatabase
import com.example.magicapp.data.local.CardEntity
import com.example.magicapp.data.remote.Card
import com.example.magicapp.data.remote.MagicApiService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CardViewModel(application: Application) : AndroidViewModel(application) {

    private val cardDao = getDatabase(application).cardDao()
    val allCards: LiveData<List<CardEntity>> = cardDao.getAllCards().asLiveData()
    private val _selectedCard = MutableLiveData<Card?>()
    val selectedCard: LiveData<Card?> = _selectedCard

    private val magicApiService: MagicApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.magicthegathering.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        magicApiService = retrofit.create(MagicApiService::class.java)
    }

    fun searchCard(name: String) {
        viewModelScope.launch {
            try {
                val response = magicApiService.getCardByName(name)
                if (response.isSuccessful) {
                    val card = response.body()?.cards?.firstOrNull()
                    if (card != null) {
                        _selectedCard.value = card
                        cardDao.insert(card.toEntity())
                    }
                }
            } catch (e: Exception) {
                // Handle exceptions
            }
        }
    }

    fun selectCard(cardEntity: CardEntity) {
        _selectedCard.value = cardEntity.toCard()
    }
}

fun Card.toEntity(): CardEntity {
    return CardEntity(name, imageUrl, type, rarity, text, power, toughness, legality)
}

fun CardEntity.toCard(): Card {
    return Card(name, imageUrl, type, rarity, text, power, toughness, legality)
}
