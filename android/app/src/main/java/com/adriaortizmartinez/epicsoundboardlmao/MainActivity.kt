package com.adriaortizmartinez.epicsoundboardlmao

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : AppCompatActivity(), OnItemClickListener {

    var mediaPlayer: MediaPlayer? = null
    var firebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firebaseAnalytics = FirebaseAnalytics.getInstance(this)

//        MobileAds.initialize(this, "ca-app-pub-5361617698168727~3272304132")
//
//        val adRequest = AdRequest.Builder().build()
//        adView.loadAd(adRequest)

        var jsonString = resources.openRawResource(R.raw.sound).bufferedReader().use{ it.readText() }

        var gson = Gson()
        val soundList = gson.fromJson(jsonString, SoundList::class.java)

        soundList.sounds?.let {
            soundsRecyclerview.layoutManager = GridLayoutManager(this, 3)

            var adapter = SoundsAdapter(it)
            adapter.onSoundClickListener = this

            soundsRecyclerview.adapter = adapter
        }

        //soundsRecyclerview.layoutManager = LinearLayoutManager(this)
        //soundsRecyclerview.adapter = SoundsAdapter()

//        button1.setOnClickListener{
//            mediaPlayer?.release()
//
//            var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.paper);
//            mediaPlayer?.start();
//        }
//
//        button2.setOnClickListener{
//            mediaPlayer?.release()
//
//            var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, R.raw.dorcas);
//            mediaPlayer?.start();
//        }
    }

    override fun onItemClick(sound: SoundModel, position: Int) {
        mediaPlayer?.release()

        var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, resources.getIdentifier(sound.file, "raw", packageName));
        mediaPlayer?.start();

        var bundle = Bundle()
        bundle.putString("sound_title", sound.title)
        firebaseAnalytics?.logEvent("sound_play", bundle)
    }

    override fun onStop() {
        super.onStop();
        mediaPlayer?.release();
        mediaPlayer = null;
    }
}



