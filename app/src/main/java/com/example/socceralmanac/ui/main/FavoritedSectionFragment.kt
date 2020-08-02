package com.example.socceralmanac.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.socceralmanac.R

/**
 * A simple [Fragment] subclass.
 */
class FavoritedSectionFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorited_section, container, false)
    }

    companion object {
        fun newInstance(): FavoritedSectionFragment{
            val fragment = FavoritedSectionFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

    }

}
