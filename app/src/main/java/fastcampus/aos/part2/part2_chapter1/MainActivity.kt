package fastcampus.aos.part2.part2_chapter1

import android.os.Bundle
import android.view.Gravity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import fastcampus.aos.part2.part2_chapter1.WebViewFragment.Companion.SHARED_PREFERENCE
import fastcampus.aos.part2.part2_chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnTabLayoutNameChanged {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE)
        val tab0 = sharedPreference.getString("tab0_name", "월요웹툰")
        val tab1 = sharedPreference.getString("tab1_name", "화요웹툰")
        val tab2 = sharedPreference.getString("tab2_name", "수요웹툰")

        binding.viewPager.adapter = ViewPagerAdapter(this@MainActivity)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
//                val textView = TextView(this@MainActivity).apply {
//                    text = when(position) {
//                        0 -> tab0
//                        1 -> tab1
//                        else -> tab2
//                    }
//                    gravity = Gravity.CENTER
//                }

                tab.text = when (position) {
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }
            }
        }.attach()
    }

    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
        if (currentFragment is WebViewFragment) {
            if (currentFragment.canGoBack()) {
                currentFragment.goBack()
            } else {
                super.onBackPressed()
            }
        } else {
            super.onBackPressed()
        }
    }

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name
    }
}
