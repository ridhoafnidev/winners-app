package com.dailyapps.score.ui.screen

import com.dailyapps.entity.Score

sealed class ScoreScreenState {
    class Success(val score: List<Score>): ScoreScreenState()
    class Error(val message: String): ScoreScreenState()
    object Loading: ScoreScreenState()
}