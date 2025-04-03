package fastcampus.aos.part2.part2_chapter1

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(private val mainActivity: MainActivity) :
    FragmentStateAdapter(mainActivity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                WebViewFragment(position, "https://comic.naver.com/webtoon?tab=mon").apply {
                    listener = mainActivity
                }
            }

            1 -> {
                WebViewFragment(position, "https://comic.naver.com/webtoon?tab=tue").apply {
                    listener = mainActivity
                }
            }

            else -> {
                WebViewFragment(position, "https://comic.naver.com/webtoon?tab=wed").apply {
                    listener = mainActivity
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}