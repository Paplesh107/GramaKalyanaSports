package com.example.gramakalyanasports.ui.scorer

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class ScorerViewModel : ViewModel() {

    var score = 0
    var wickets = 0
    var overs = 0.0

    val scoreHistory = mutableStateListOf<String>()

    fun addRuns(runs: Int) {

        score += runs

        scoreHistory.add("$runs runs added")
    }

    fun addWicket() {

        wickets++

        scoreHistory.add("Wicket")
    }

    fun addOver() {

        overs += 1

        scoreHistory.add("Over completed")
    }

    fun resetMatch() {

        score = 0
        wickets = 0
        overs = 0.0

        scoreHistory.clear()
    }
}