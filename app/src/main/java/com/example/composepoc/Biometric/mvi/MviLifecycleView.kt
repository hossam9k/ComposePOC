package com.example.composepoc.Biometric.mvi

import androidx.lifecycle.LifecycleOwner

interface MviLifecycleView<S : State, E : Event> : MviView<S, E>, LifecycleOwner
