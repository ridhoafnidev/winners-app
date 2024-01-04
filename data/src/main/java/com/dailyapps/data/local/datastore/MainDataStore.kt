package com.dailyapps.data.local.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MainDataStore @Inject constructor(
     @ApplicationContext val context: Context,
     
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "isekolah_smp_yosef.pb")

    suspend fun <T> storeData(key: Preferences.Key<T>, value: T) {
        context.dataStore.edit { preference ->
            preference[key] = value
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.remove(USERNAME)
            preferences.remove(TOKEN)
        }
    }

    val username: Flow<String>
        get() = context.dataStore.data.map { preferences ->
            preferences[USERNAME] ?: ""
        }

    val token: Flow<String>
        get() = context.dataStore.data.map { preferences -> preferences[TOKEN] ?: "" }

    val tokenType: Flow<String>
        get() = context.dataStore.data.map { preferences -> preferences[TOKEN_TYPE] ?: "" }

    val id: Flow<Int>
        get() = context.dataStore.data.map { preferences -> preferences[ID] ?: 0 }

    companion object {
        val USERNAME = stringPreferencesKey("USERNAME")
        val TOKEN = stringPreferencesKey("TOKEN")
        val TOKEN_TYPE = stringPreferencesKey("TOKEN_TYPE")
        val NISN = stringPreferencesKey("NISN")
        val ID = intPreferencesKey("ID")
    }

}