package io.revze.footballmatchschedule.view.match_list.next_match

import io.revze.footballmatchschedule.model.NextMatch

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showNextMatchList(data: List<NextMatch>)
}