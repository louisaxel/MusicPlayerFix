package com.example.musicplayer

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ImageButton
import android.widget.SeekBar


class MainActivity : AppCompatActivity() {

    lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mediaplayer = MediaPlayer.create(this, R.raw.hatihatidijalan)
        val playButton: ImageButton = findViewById(R.id.play)
        val seekbar: SeekBar = findViewById(R.id.seekBar)

        seekbar.progress = 0
        seekbar.max = mediaplayer.duration

        playButton.setOnClickListener {
            if(!mediaplayer.isPlaying) {
                mediaplayer.start()

                playButton.setImageResource(R.drawable.ic_baseline_pause_circle_filled_24)
            } else {
                mediaplayer.pause()
                playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
            }
        }

        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{

            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if (changed) {
                    mediaplayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        runnable = Runnable {
            seekbar.progress = mediaplayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
        mediaplayer.setOnCompletionListener {
            playButton.setImageResource(R.drawable.ic_baseline_play_circle_filled_24)
            seekbar.progress = 0
        }
    }
}