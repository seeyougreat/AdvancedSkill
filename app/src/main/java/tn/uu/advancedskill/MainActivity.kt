package tn.uu.advancedskill

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.format.DateUtils
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
//import com.warrenstrange.googleauth.GoogleAuthenticator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import pub.devrel.easypermissions.AfterPermissionGranted
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions
import tn.uu.advancedskill.example.MyService
import tn.uu.advancedskill.example.coroutine.CoroutineExample
import tn.uu.advancedskill.flow.NewsRemoteDataSource
import tn.uu.advancedskill.util.AlertVoiceUtil
import tn.uu.baselibrary.utils.AlarmManagerUtils
import tn.uu.baselibrary.utils.GoogleAuthenticatorUtils
import tn.uu.baselibrary.utils.MyDateUtil
import tn.uu.baselibrary.utils.ToastUtil
import tn.uu.baselibrary.widget.PointProcessBar
import tn.uu.baselibrary.widget.SegmentTabLayout
import tn.uu.baselibrary.widget.SnrProgressBar
import tn.uu.baselibrary.widget.lampbackground.LampTextView
import tn.uu.baselibrary.widget.selectfile.SelectFileDialog
import java.math.BigDecimal
import java.util.Arrays
import java.util.Calendar


class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {
    private lateinit var webView: WebView
    private lateinit var testBtn: MaterialButton
    private lateinit var pointProgressBar: PointProcessBar

    companion object {
        private const val REQUEST_NEED_ALL = 4
    }

    private val PERMISSION_NEED_ALL = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//        Manifest.permission.WRITE_SETTINGS,
//        Manifest.permission.SYSTEM_ALERT_WINDOW
    )

    private val alertVoiceUtil by lazy { AlertVoiceUtil(this) }

    private var itemTitles: Array<String> = arrayOf()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        requireNeedAllPermission()

        webView = findViewById(R.id.web_view)
        testBtn = findViewById(R.id.btn_test2)

        val progressBar = findViewById<SnrProgressBar>(R.id.my_progressBar)
        //        progressBar.setShowPreTxt("");
//        progressBar.setProgress(20);

        ToastUtil.create("20G了allllalglagllallelekkgkgekegkkge").show()

//        BackToAppUtil.showBackButton()

        val btn = findViewById<Button>(R.id.btn_test)


        val lampView = findViewById<LampTextView>(R.id.myLampView)
//        lampView.lampBackgroundHelper.apply {
////            mRadius = 10f
//            setLampDurationTime(800)
//            hintColor = Color.GREEN
//            hintWidth = 20f
//        }

        btn.setOnClickListener {
            if (lampView.isShow()) {
                lampView.hideLamp()
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    lampView.showLamp()
                }
            }
        }


        Log.e("xxx", "MyApp: " + MyApp.instance)

//        Handler(Looper.getMainLooper()).postDelayed({
//            findViewById<SearchView>(R.id.serch_view).startSearch()
//        }, 3000)

        webView.settings.javaScriptEnabled = true

        webView.settings.domStorageEnabled = true

//        webView.loadUrl("https://blog.csdn.net/chekongfu/article /details/100047114")
        webView.loadUrl("https://www.baidu.com")
//        webView.loadUrl("https://juejin.cn/")

//        Log.e("xxx22", "Ready, Go Go")
        CoroutineExample.runExample()
//        Log.e("xxx22", "999->>>")


        val playBtn = findViewById<Button>(R.id.btn_start_play)

        val voiceSeekBar = findViewById<SeekBar>(R.id.seek_bar_voice)

        voiceSeekBar.progress = 50


        alertVoiceUtil.setValue(voiceSeekBar.progress)

        voiceSeekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Log.e("xxx111", "progress: $progress")
                alertVoiceUtil.setValue(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        var isPlay = false

        playBtn.setOnClickListener {
            isPlay = !isPlay
            alertVoiceUtil.isPlay = isPlay
        }

        itemTitles = arrayOf(
            "zhang",
            "phil",
            "zhang phil",
            "csdn",
            "zhang phil csdn",
            "zhang phil @ csdn",
            "blog.csdn.net/zhangphil",
            "android"
        )
//        itemTitles = arrayOf(
//            "100",
//            "200",
//            "500",
//            "800",
//            "1000"
//        )
        val titles = Arrays.asList<String>(*itemTitles)

        val segmentLayout = findViewById<SegmentTabLayout>(R.id.my_tablelayout)
        segmentLayout.setTitles(titles)

        val selectedIndex = 4
//        segmentLayout.getTabAt(selectedIndex)?.select()

        //用于移动到选中的位置
        segmentLayout.post {
            segmentLayout.smoothScrollTo(
                (getTabLayoutOffsetWidth(selectedIndex) * resources.displayMetrics.density).toInt(),
                0
            )
        }

        //这个方法可以矫正滑动不准备导致不在中间的问题
        var isChange = false
        segmentLayout.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            if (!isChange) {
                isChange = true
                segmentLayout.getTabAt(selectedIndex)?.select()
            }
        }

        segmentLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Log.e("xxx1", "${tab?.id}")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Log.e("xxx122", "${tab?.id}")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.e("xxx133", "${tab?.id}")
            }

        })

        //EditText的错误消息显示
        //有3中方法
        val editText = findViewById<EditText>(R.id.et_num)

        //1.设置自定义文字
//        editText.setError("输入框不能为空")

        //2.设置自定义文字和图标
//        val drawable = resources.getDrawable(R.drawable.baseline_contact_support_24)
//        drawable.setBounds(0, 0, 100, 100)
//        editText.setError("这个是错误的", drawable)
        //3.设置文字及颜色
//        editText.setError(Html.fromHtml("<font color=#00ffff>字体颜色设置</font>"))

        val ratingBar = findViewById<RatingBar>(R.id.rating_bar_2)
        ratingBar.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->
            Log.e("xxx", "rating: ${rating} fromUser: $fromUser")
        }

        alarmManagerUtils = AlarmManagerUtils.getInstance(this)

        pointProgressBar = findViewById<PointProcessBar>(R.id.node_progress_view)
//        pointProgressBar.addCheckIndex(1, 3, 5)
        pointProgressBar.titleSparseArray.put(0, "开始")
        pointProgressBar.titleSparseArray.put(5, "完成")

        val isToday = DateUtils.isToday(1716331695000)
        Log.e("xxx", "isToday: $isToday")

        val isToday2 = MyDateUtil.isToday(1716418095000)
        Log.e("xxx", "isToday2: $isToday2")


        val t2 = DateUtils.formatElapsedTime(66)
        Log.e("xxx", "t2: $t2")


    }

    /**
     * 测试kotlin的FLow数据流
     *
     * @param view
     */
    fun testFlow(view: View) {
        CoroutineScope(Dispatchers.IO).launch {
            NewsRemoteDataSource().lastNews
                .map { news -> news.filter { isTrue(it, "9")} }
                .collect {
                    Log.e("xxx", "$it")
                }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun testGoogleAuthenticator(view: View) {
        val code2 = GoogleAuthenticatorUtils.generatedDefaultCode()
        ToastUtil.create("$code2").show()
    }

    private fun big2(d: Double): String {
        val d1 = BigDecimal.valueOf(d)
        val d2 = BigDecimal.valueOf(1);
        // 四舍五入,保留2位小数
        return d1.divide(d2,9, BigDecimal.ROUND_DOWN).toString();
    }

    private fun isTrue(a: String, b: String): Boolean {
        return a == b
    }

    fun testPointProgressBar(view: View) {
//        pointProgressBar.addCheckIndex(3, 5)
        pointProgressBar.setProgress(5)

    }

    fun testPointProgressBarCancel(view: View) {
//        pointProgressBar.uncheckIndex(3, 5)
        pointProgressBar.setProgress(-1)
    }


    private lateinit var alarmManagerUtils: AlarmManagerUtils

//   private val startForResult =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            if (it.resultCode == SelectFileDialog.INTENT_RESULT_OK) {
//                val filePath = it.data?.getStringExtra(SelectFileDialog.INTENT_SELECTED_FILE_PATH)
//                Log.e("xxx", "选择的文件： $filePath")
//            }
//        }

    fun testSelectFile(view: View) {

        val dialog = SelectFileDialog(
            this, "选择文件",
            "/sdcard/test", null, {
                Log.e("xxx00", it)
            }, {
                val list = mutableListOf<String>()
                list.add("xxx")
                list.add("test")
                list.add("test")
                list.add("test")
                list.add("test")
                list.add("testxxxxxxxxxxxxxxxxxxxxxxxx")
                return@SelectFileDialog list
            }
        )

        dialog.show()
    }

    private var testN = 40

    fun testAlarmClock(view: View) {

        val calendar = Calendar.getInstance()
//        calendar.set(Calendar.HOUR_OF_DAY, 10)
//        calendar.set(Calendar.MINUTE, 20)
        calendar.set(Calendar.SECOND, 25)


        val pend1 = alarmManagerUtils.createPendingIntent(MyService::class.java)
        alarmManagerUtils.startRepeatWork(pend1, calendar, 60 * 1000L)
//        alarmManagerUtils.startWork(pend1, calendar)

        testN += 13


        Toast.makeText(this, "设置成功", Toast.LENGTH_SHORT).show();
    }

    fun testCancel(view: View) {
        val pend1 = alarmManagerUtils.createPendingIntent(MyService::class.java)
        alarmManagerUtils.cancel(pend1)
    }


    /**
     * 根据字符个数计算偏移量
     *
     * @param index 选中tab的下标
     * @return 需要移动的长度
     */

    private fun getTabLayoutOffsetWidth(index: Int): Int {
        if (index < 1) {
            return 0
        }
        var str = "";
        for (i in 0 until index - 1) {
            //channelNameList是一个List<String>的对象，里面转载的是30个词条
            //取出直到index的tab的文字，计算长度
            str = "$str${itemTitles[i]}"
        }
        return str.length * 12 + (index - 1) * 10
    }


    /**
     * @afterPermissionGranted标识methodRequiresTwoPermission()方法，
     * 在成功获取所需权限后会再次回调该方法，此时代码会走到处理业务的相关代码
     */

    @AfterPermissionGranted(REQUEST_NEED_ALL)
    private fun requireNeedAllPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) { //悬浮窗权限
                startActivityForResult(
                    Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    ), 0
                )
            }

            //修改系统权限
            if (!Settings.System.canWrite(this)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_WRITE_SETTINGS,
                    Uri.parse("package:$packageName")
                )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivityForResult(intent, 200)
            }
        }

        if (EasyPermissions.hasPermissions(this, *PERMISSION_NEED_ALL)) {
            Log.e("xxx", "获取到权限了")

        } else {
            Log.e("xxx", "还未获取到")
            EasyPermissions.requestPermissions(
                this, "请求权限存储权限",
                REQUEST_NEED_ALL,
                *PERMISSION_NEED_ALL
            )
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            }
        }
    }


    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.d("xxx", "Granted: " + requestCode)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.d("xxx", "Denied: " + requestCode + ": " + perms)
        //1.首先调用EasyPermissions.somePermissionPermanentlyDenied()确认是否有权限被用于拒绝且选择"不再提示"永久拒绝
        //2.若有权限被永久拒绝，调用new AppSettingsDialog.Builder(this).build().show()弹出一个对话框引导用户到设置页面开启
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog.Builder(this).build().show()
            /**
             * new AppSettingsDialog.Builder(this)
             * .setPositiveButton(R.string.authorize_msg)
             * .setTitle(R.string.authorize_title_msg)
             * .setRationale(R.string.modify_permission_msg) .build() .show();
             *
             * 原文链接：https://blog.csdn.net/HEAVEN1Q/article/details/122442325
             */
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.e("xxx", "2222")
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("xxx", "ondestroy : ")
        alertVoiceUtil.destroy()
    }

}