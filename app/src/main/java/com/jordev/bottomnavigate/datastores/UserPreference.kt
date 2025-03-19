package com.jordev.bottomnavigate.datastores

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "tema")
object UserPreference {
    private val THEME_KEY = booleanPreferencesKey("user_theme")

    suspend fun salvarTema(context: Context, tema: Boolean){
        context.dataStore.edit { preferences ->
            preferences[THEME_KEY] = tema
        }
    }
    fun getTema(context: Context): Flow<Boolean>{
        return context.dataStore.data.map { preferences ->
            preferences[THEME_KEY] == true
        }

    }
}