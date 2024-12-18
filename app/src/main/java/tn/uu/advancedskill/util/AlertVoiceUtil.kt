package tn.uu.advancedskill.util

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tn.uu.advancedskill.R
import tn.uu.baselibrary.utils.MediaPlayerUtil

class AlertVoiceUtil(private val context: Context) {

    private val scope by lazy { CoroutineScope(Dispatchers.IO) }

    private var intervalTime: Long = 100L

    private val playerUtil by lazy {
        val playerUtil = MediaPlayerUtil()

        playerUtil.init(context, R.raw.drip)

        playerUtil.setOnMediaStateListener(object : MediaPlayerUtil.OnMediaStateListener {
            override fun onPrepared() {

            }

            override fun onSeekUpdate(curTimeInt: Int) {
                //更新进度条
            }

            override fun onCompletion() {
                 scope.launch {
                    //播放完成
                    if (isPlay) {
                        delay(intervalTime)
                        if (isPlay) {
                            playerUtil.start()
                        }
                    }
                }
            }

            override fun onError(): Boolean {
                playerUtil.reset()
                //重置UI状态
                return true
            }

        })

        playerUtil
    }


    //Start or Pause play voice
    @Volatile
    var isPlay = false
        set(value) {
            field = value
            if (value) {
                playerUtil.start()
            } else {
                playerUtil.pause()
            }
        }

    //The value affects the playback speed and volume
    fun setValue(value: Int) {
        val shouldValue = if (value <= 0) {
            10
        } else if (value > 100) {
            100
        } else {
            value
        }
        intervalTime = 1000L - shouldValue * 10
        val volume = shouldValue / 100F
        playerUtil.setVolume(volume, volume)
    }

    fun destroy() {
        playerUtil.onDestroy()
    }

}