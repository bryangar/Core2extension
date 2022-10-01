package com.example.core2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    lateinit var colombia: Location
    lateinit var spain: Location
    lateinit var italy: Location
    lateinit var malta: Location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colombia = Location( "Armenia", "Colombia", "12-09-2001","4.5",1)
        spain = Location ("Madrid", "Spain", "25-03-2018", "5",2)
        italy = Location("Naples","Italy","12-12-2009","3.5",3)
        malta = Location("Valleta","Malta", "05-05-2016","4",4)

        val vcolombia = findViewById<ImageView>(R.id.col)
        val vspain = findViewById<ImageView>(R.id.spa)
        val vitaly = findViewById<ImageView>(R.id.it)
        val vmalta = findViewById<ImageView>(R.id.mal)

        vcolombia.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("location",colombia)
            intent.putExtra("resId",R.drawable.colombia)
            startForResult.launch(intent)
        }

        vspain.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("location",spain)
            intent.putExtra("resId",R.drawable.spain)
            startForResult.launch(intent)
        }

        vitaly.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("location",italy)
            intent.putExtra("resId",R.drawable.italy)
            // We need to change startActivity(intent) for startForResult.launch(intent)
            startForResult.launch(intent)
        }

        vmalta.setOnClickListener {
            val intent = Intent(this, ReviewActivity::class.java)
            intent.putExtra("location",malta)
            intent.putExtra("resId",R.drawable.valleta)
            startForResult.launch(intent)
        }


    }
    // Taking the result back from the previous activity

    val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            when(result.resultCode) {
                RESULT_OK -> {
                    val data = result.data
                    val place = data?.getParcelableExtra<Location>("place")



                    Log.i("RESULT","onCreation $place")
                    place?.let{
                        Log.i("RESULT","onCreation ${it.place}")
                        //Check which of the picture has been updated
                        if( it.id == 2) {
                            val vplace = findViewById<TextView>(R.id.madrid)
                            vplace.text = it.place
                            val vrating = findViewById<TextView>(R.id.rating1)
                            vrating.text = it.score.toString()
                            spain = place
                            Toast.makeText(this, "Updated ${vplace.text}", Toast.LENGTH_SHORT).show()
                        }
                        else if (it.id == 3)
                        {
                            val vplace = findViewById<TextView>(R.id.naples)
                            vplace.text = it.place
                            val vrating = findViewById<TextView>(R.id.rating3)
                            vrating.text = it.score.toString()
                            italy = place
                            Toast.makeText(this, "Updated ${vplace.text}", Toast.LENGTH_SHORT).show()
                        } else if (it.id == 1)
                        {
                            val vplace = findViewById<TextView>(R.id.armenia)
                            vplace.text = it.place
                            val vrating = findViewById<TextView>(R.id.rating4)
                            vrating.text = it.score.toString()
                            colombia = place
                            Toast.makeText(this, "Updated ${vplace.text}", Toast.LENGTH_SHORT).show()
                        } else if(it.id == 4){
                            val vplace = findViewById<TextView>(R.id.malta)
                            vplace.text = it.place
                            val vrating = findViewById<TextView>(R.id.rating2)
                            vrating.text = it.score.toString()
                            malta = place
                            Toast.makeText(this, "Updated ${vplace.text}", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }

        }
}