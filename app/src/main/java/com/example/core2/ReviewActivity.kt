package com.example.core2

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.RatingBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.util.*
import java.util.regex.Pattern

class ReviewActivity : AppCompatActivity() {
    var location: Location? = null

    //val p: Regex = "\\s+(?:0[1-9]|[12][0-9]|3[01])[-/.](?:0[1-9]|1[012])[-/.](?:19\\d{2}|20[01][0-9]|2020)\\b".toRegex()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.location_main)

        location = intent.getParcelableExtra<Location>("location")
        val bundle: Bundle? = intent.extras
        val resId: Int = (bundle?.getInt("resId") ?: "resId") as Int




        location?.let {
            val vpicture = findViewById<ImageView>(R.id.currentimage)
            vpicture.setImageResource(resId)
            val vplace = findViewById<TextInputEditText>(R.id.place)
            vplace.setText(location!!.place)
            val vcountry: EditText = findViewById<TextInputEditText>(R.id.country)
            vcountry.setText(location!!.country)
            val vlastvisit: EditText = findViewById<TextInputEditText>(R.id.lastvisit)
            vlastvisit.setText(location!!.lastvisit)
            val vrating = findViewById<RatingBar>(R.id.ratingBar)
            vrating.rating = location!!.score.toFloat()
        }


    }

    override fun onBackPressed() {

        val ePlace: EditText = findViewById<TextInputEditText>(R.id.place)
        val eRating = findViewById<RatingBar>(R.id.ratingBar)
        val eCountry: EditText = findViewById<TextInputEditText>(R.id.country)
        val eLastVisit: EditText = findViewById<TextInputEditText>(R.id.lastvisit)
        Log.i("DATE:", "${eLastVisit.text}")
        // Checking error
        val b = Pattern.matches("(?:0[1-9]|[12][0-9]|3[01])[-/.](?:0[1-9]|1[012])[-/.](?:19\\\\d{2}|20[01][0-9]|2020)", eLastVisit.text)
        if (b) {
            location?.place = ePlace.getText().toString()
            location?.score = eRating.rating.toString()
            location?.country = eCountry.getText().toString()
            location?.lastvisit = eLastVisit.getText().toString()
            val i = intent.apply {
                putExtra("place", location)
            }
            setResult(Activity.RESULT_OK, i)
            super.onBackPressed()
        } else {
            eLastVisit.error = "Enter a valid date format (dd-mm-yyyy)"
        }
    }
}







