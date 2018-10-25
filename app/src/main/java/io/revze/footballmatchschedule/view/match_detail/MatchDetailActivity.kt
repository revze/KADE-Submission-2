package io.revze.footballmatchschedule.view.match_detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.ScrollView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.revze.footballmatchschedule.R
import io.revze.footballmatchschedule.api.ApiRepository
import io.revze.footballmatchschedule.model.MatchDetail
import io.revze.footballmatchschedule.util.gone
import io.revze.footballmatchschedule.util.visible
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.toast
import java.text.ParseException
import java.text.SimpleDateFormat

class MatchDetailActivity : AppCompatActivity(), MatchDetailView {

    companion object {
        val EVENT_ID_KEY = "event_id"
    }

    private lateinit var matchDetailPresenter: MatchDetailPresenter
    private lateinit var svMatchDetail: ScrollView
    private lateinit var layoutLoading: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        val apiRepository = ApiRepository()
        val gson = Gson()
        matchDetailPresenter = MatchDetailPresenter(this, apiRepository, gson)
        svMatchDetail = sv_match_detail
        layoutLoading = layout_loading
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        matchDetailPresenter.getMatchDetail(intent.getStringExtra(EVENT_ID_KEY))
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        svMatchDetail.gone()
        layoutLoading.visible()
    }

    override fun hideLoading() {
        svMatchDetail.visible()
        layoutLoading.gone()
    }

    override fun showMatchDetail(data: MatchDetail) {
        matchDetailPresenter.getTeamDetail(data.homeTeamId, "home")
        matchDetailPresenter.getTeamDetail(data.awayTeamId, "away")
        tv_date.text = dateConverter(data.dateEvent)
        tv_home_team.text = data.homeTeam
        tv_away_team.text = data.awayTeam
        data.homeScore.let { tv_home_score.text = it }
        data.awayScore.let { tv_away_score.text = it }
        data.homeGoalDetails.let { tv_home_goal_details.text = it }
        data.awayGoalDetails.let { tv_away_goal_details.text = it }
        data.homeShots.let { tv_home_shots.text = it }
        data.awayShots.let { tv_away_shots.text = it }
        data.homeLineupGoalkeeper.let { tv_home_goal_keeper.text = it }
        data.awayLineupGoalkeeper.let { tv_away_goal_keeper.text = it }
        data.homeLineupDefense.let { tv_home_defender.text = it }
        data.awayLineupDefense.let { tv_away_defender.text = it }
        data.homeLineupMidfield.let { tv_home_midfield.text = it }
        data.awayLineupMidfield.let { tv_away_midfield.text = it }
        data.homeLineupForward.let { tv_home_forward.text = it }
        data.awayLineupForward.let { tv_away_forward.text = it }
        data.homeLineupSubstitutes.let { tv_home_substitutes.text = it }
        data.awayLineupSubstitutes.let { tv_away_substitutes.text = it }
    }

    fun dateConverter(date: String) : String {
        try {
            val sourceDF = SimpleDateFormat("yyyy-MM-dd")
            val parseSrcDate = sourceDF.parse(date)
            val finalDF = SimpleDateFormat("EEE, dd MMM yyyy")
            return finalDF.format(parseSrcDate)
        }
        catch (e: ParseException) {
            return date
        }
    }

    override fun showHomeTeamLogo(logo: String?) {
        Picasso.get().load(logo).into(iv_home_team)
    }

    override fun showAwayTeamLogo(logo: String?) {
        Picasso.get().load(logo).into(iv_away_team)
    }
}
