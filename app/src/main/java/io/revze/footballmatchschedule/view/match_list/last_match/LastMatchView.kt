package io.revze.footballmatchschedule.view.match_list.last_match

import io.revze.footballmatchschedule.model.LastMatch

interface LastMatchView {
    fun showLoading()
    fun hideLoading()
    fun showLastMatchList(data: List<LastMatch>)
}