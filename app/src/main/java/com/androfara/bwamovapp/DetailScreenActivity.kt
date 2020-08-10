package com.androfara.bwamovapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androfara.bwamovapp.checkout.PilihBangkuActivity
import com.androfara.bwamovapp.home.dashboard.PlaysAdapter
import com.androfara.bwamovapp.model.Film
import com.androfara.bwamovapp.model.Plays
import com.bumptech.glide.Glide
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_detail_screen.*

class DetailScreenActivity : AppCompatActivity() {
    private lateinit var mDatabase : DatabaseReference
    private var dataList = ArrayList<Plays>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_screen)

        val data = intent.getParcelableExtra<Film>("data")

        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data.judul.toString())
            .child("play")

        tv_kursi.text = data.judul
        tv_genre.text = data.genre
        tv_desc.text = data.desc
        tv_rate.text = data.rating

        Glide.with(this)
            .load(data.poster)
            .into(iv_poster)

        rv_who_play.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        getData()

        btn_pilih_bangku.setOnClickListener{
            var intent = Intent(this@DetailScreenActivity, PilihBangkuActivity::class.java).putExtra("data", data)
            startActivity(intent)
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object: ValueEventListener{
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@DetailScreenActivity, ""+databaseError.message,
                    Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()

                for (getdataSnapshot in dataSnapshot.children){
                    var film = getdataSnapshot.getValue(Plays::class.java)
                    dataList.add(film!!)
                }

                rv_who_play.adapter = PlaysAdapter(dataList){

                }
            }

        })
    }
}