package com.sviridovamd.strava

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    fun hiMe(view: View) {
        // val myToast = Toast.makeText(this, message, duration);
        val hiMe = Toast.makeText(
            this,
            "Hello Toast!",
            Toast.LENGTH_LONG
        )
        hiMe.show()
    }




}
