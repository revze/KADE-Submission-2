package io.revze.footballmatchschedule.view.match_list

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import io.revze.footballmatchschedule.R
import io.revze.footballmatchschedule.view.match_list.last_match.LastMatchFragment
import io.revze.footballmatchschedule.view.match_list.next_match.NextMatchFragment

class MatchFragmentPagerAdapter(private var context: Context, private var fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    override fun getCount() = 2

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return LastMatchFragment()
            else -> return NextMatchFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> return context.getString(R.string.last_match_fragment_title)
            else -> return context.getString(R.string.next_match_fragment_title)
        }
    }
}