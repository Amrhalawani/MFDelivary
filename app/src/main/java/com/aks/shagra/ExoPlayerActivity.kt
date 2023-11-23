package com.aks.shagra

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.aks.shagra.data.models.GalleryType.Companion.VIDEOS
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.MediaMetadata
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import com.google.common.collect.ImmutableList


class ExoPlayerActivity : AppCompatActivity(), Player.Listener {
    private lateinit var player: ExoPlayer
    private lateinit var playerView: PlayerView
    private lateinit var progressBar: ProgressBar
    private lateinit var titleTv: TextView

    var url: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo_player)
        progressBar = findViewById(R.id.progressBar)

        titleTv = findViewById(R.id.title)

        val extras = intent.extras
        if (extras != null) {
            url = extras.getString(VIDEOS)
        }
        setupPlayer()
        //addMP3()
        addMP4Files()


        // restore playstate on Rotation
        if (savedInstanceState != null) {
            if (savedInstanceState.getInt("mediaItem") != 0) {
                val restoredMediaItem = savedInstanceState.getInt("mediaItem")
                val seekTime = savedInstanceState.getLong("SeekTime")
                player.seekTo(restoredMediaItem, seekTime)
                player.play()
            }
        }
    }

    private fun addMP4Files() {

//        val s = "https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4"
//        val d = "https://download.blender.org/demo/movies/BBB/bbb_sunflower_1080p_30fps_normal.mp4"

        val mediaItem = MediaItem.fromUri(url!!)
        //     val mediaItem2 = MediaItem.fromUri(d)
        val newItems: List<MediaItem> = ImmutableList.of(
            mediaItem,
            //       mediaItem2
        )
        player.addMediaItems(newItems)
        player.prepare()
    }

    private fun setupPlayer() {
        player = ExoPlayer.Builder(this).build()
        playerView = findViewById(R.id.video_view)
        playerView.player = player
        player.addListener(this)
    }

    private fun addMP3() {
//        // Build the media item.
//        val mediaItem = MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/play.mp3")
//        player.setMediaItem(mediaItem)
//        // Set the media item to be played.
//        player.setMediaItem(mediaItem)
//        // Prepare the player.
//        player.prepare()
    }


    override fun onStop() {
        super.onStop()
        player.release()
    }

    override fun onResume() {
        super.onResume()
        setupPlayer()
        addMP3()
        addMP4Files()
    }

    // handle loading
    override fun onPlaybackStateChanged(state: Int) {
        when (state) {
            Player.STATE_BUFFERING -> {
                progressBar.visibility = View.VISIBLE

            }
            Player.STATE_READY -> {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }

    //get Title from metadata
    override fun onMediaMetadataChanged(mediaMetadata: MediaMetadata) {

        titleTv.text = mediaMetadata.title ?: mediaMetadata.displayTitle ?: "-"

    }

    // save details if Activity is destroyed
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState: " + player.currentPosition)
        // current play position
        outState.putLong("SeekTime", player.currentPosition)
        // current mediaItem
        outState.putInt("mediaItem", player.currentMediaItemIndex)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onSaveInstanceState: " + player.currentPosition)
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}

