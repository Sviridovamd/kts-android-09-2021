package com.sviridovamd.strava

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.sviridovamd.strava.R.id.start_button

class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn = requireView().findViewById<Button>(start_button)
        btn.setOnClickListener {
            findNavController().navigate(R.id.action_onBoardingFragment_to_loginFragment)
        }

    }
}