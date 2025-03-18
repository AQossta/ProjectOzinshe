package com.example.ozinsheexample.presentation.about

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.updateLayoutParams
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.ozinsheexample.R
import com.example.ozinsheexample.databinding.FragmentVideoBinding
import com.example.ozinsheexample.provideNavigationHost
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.DefaultPlayerUiController
import com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBar
import kotlinx.coroutines.launch

class VideoFragment : Fragment() {
    private lateinit var binding: FragmentVideoBinding
    private val args: VideoFragmentArgs by navArgs()

    override fun onResume() {
        super.onResume()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    override fun onStart() {
        super.onStart()
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVideoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        provideNavigationHost()?.apply {
            setNavigationVisibility(false)
        }
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        lifecycleScope.launch {
            moviePlay(args.videoLink)
        }
    }
    fun moviePlay(link: String){
        val youTubePlayerView = binding.youtubePlayerView

        lifecycle.addObserver(youTubePlayerView)

        val listener: YouTubePlayerListener = object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                val defaultPlayerUiController =
                    DefaultPlayerUiController(youTubePlayerView, youTubePlayer)
                defaultPlayerUiController.rootView.findViewById<View>(com.pierfrancescosoffritti.androidyoutubeplayer.R.id.drop_shadow_top)
                    .apply {
                    setBackgroundColor(R.drawable.ic_exit_button)
                    setPadding(24,24,24,24)
                    updateLayoutParams {
                        width = 170
                        height = 170
                    }
                    setOnClickListener {
                        findNavController().popBackStack()
                    }
                }
                youTubePlayerView.setCustomPlayerUi(defaultPlayerUiController.rootView)
                defaultPlayerUiController.showYouTubeButton(false)
                defaultPlayerUiController.showFullscreenButton(false)
                defaultPlayerUiController.rootView.findViewById<com.pierfrancescosoffritti.androidyoutubeplayer.core.ui.views.YouTubePlayerSeekBar>(
                    com.pierfrancescosoffritti.androidyoutubeplayer.R.id.youtube_player_seekbar
                ).setColor(resources.getColor(R.color.primary_500, null))
                youTubePlayer.loadOrCueVideo(lifecycle,link, 0f)
            }
        }
            val options:IFramePlayerOptions = IFramePlayerOptions.Builder()
                .controls(0)
                .build()

            youTubePlayerView.initialize(listener, options)
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }
}
