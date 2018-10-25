package io.revze.footballmatchschedule.view.match_list.next_match

import com.google.gson.Gson
import io.revze.footballmatchschedule.api.ApiRepository
import io.revze.footballmatchschedule.api.Endpoint
import io.revze.footballmatchschedule.api.response.NextMatchResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NextMatchPresenter(private val view: NextMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {
    fun getNextMatchList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(Endpoint().NEXT_EVENT_URL), NextMatchResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showNextMatchList(data.nextMatch)
            }
        }
    }
}