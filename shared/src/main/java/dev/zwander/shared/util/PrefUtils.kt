package dev.zwander.shared.util

import android.content.SharedPreferences
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import dev.zwander.shared.model.LocalAppModel

@Composable
fun <T> rememberPreferenceState(
    key: String,
    value: () -> T,
    onChanged: (T) -> Unit
): MutableState<T> {
    val state = remember(key) {
        mutableStateOf(value())
    }
    val appModel = LocalAppModel.current
    val prefs = appModel.prefs

    LaunchedEffect(key1 = state.value) {
        onChanged(state.value)
    }

    DisposableEffect(key1 = key) {
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, k ->
            if (key == k) {
                state.value = value()
            }
        }

        prefs.preferences.registerOnSharedPreferenceChangeListener(listener)

        onDispose {
            prefs.preferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }

    return state
}
