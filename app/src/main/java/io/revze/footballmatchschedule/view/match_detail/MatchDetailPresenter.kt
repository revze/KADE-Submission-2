package io.revze.footballmatchschedule.view.match_detail

import com.google.gson.Gson
import io.revze.footballmatchschedule.api.ApiRepository
import io.revze.footballmatchschedule.api.Endpoint
import io.revze.footballmatchschedule.api.response.MatchDetailResponse
import io.revze.footballmatchschedule.api.response.TeamDetailResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(private val view: MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson) {
    fun getMatchDetail(id: String) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(Endpoint().MATCH_DETAIL_URL + id), MatchDetailResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showMatchDetail(data.matchDetail[0])
            }
        }
    }

    fun getTeamDetail(id: String, type: String) {
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(Endpoint().TEAM_DETAIL_URL + id), TeamDetailResponse::class.java)

            uiThread {
                if (type.equals("home")) view.showHomeTeamLogo(data.teamDetail[0].teamBadge)
                else view.showAwayTeamLogo(data.teamDetail[0].teamBadge)
            }
        }
    }
}