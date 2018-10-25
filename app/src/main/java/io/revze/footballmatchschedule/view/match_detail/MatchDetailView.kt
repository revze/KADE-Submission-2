package io.revze.footballmatchschedule.view.match_detail

import io.revze.footballmatchschedule.model.MatchDetail

interface MatchDetailView {
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(data: MatchDetail)
    fun showHomeTeamLogo(logo: String?)
    fun showAwayTeamLogo(logo: String?)
}