package rk.enkidu.githubusers20.pager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import rk.enkidu.githubusers20.fragment.DetailFragment
import rk.enkidu.githubusers20.fragment.FollowerFragment
import rk.enkidu.githubusers20.fragment.FollowingFragment

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun createFragment(position: Int): Fragment {
        var fragment : Fragment? = null
        when(position){
            0 -> fragment = DetailFragment()
            1 -> fragment = FollowerFragment()
            2 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 3
    }
}