package io.revze.footballmatchschedule.view.match_list.last_match

import com.google.gson.Gson
import io.revze.footballmatchschedule.api.ApiRepository
import io.revze.footballmatchschedule.api.Endpoint
import io.revze.footballmatchschedule.api.response.LastMatchResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LastMatchPresenter(private val view: LastMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson) {
    fun getLastMatchList() {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(Endpoint().LAST_EVENT_URL), LastMatchResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showLastMatchList(data.lastMatch)
            }
        }
    }
}