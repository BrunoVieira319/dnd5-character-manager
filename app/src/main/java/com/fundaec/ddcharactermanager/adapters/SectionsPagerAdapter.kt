package com.fundaec.ddcharactermanager.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fundaec.ddcharactermanager.fragments.CharacterMainInfoFragment
import com.fundaec.ddcharactermanager.fragments.CharacterSkillsFragment

private val TAB_TITLES = arrayOf(
    "Info",
    "Skills"
)

class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, val characterId: String) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = CharacterMainInfoFragment.newInstance(characterId)
            1 -> fragment = CharacterSkillsFragment.newInstance("arg", "arg")
        }
        return fragment!!
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return TAB_TITLES[position]
    }

    override fun getCount(): Int {
        return 2
    }
}