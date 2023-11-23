package com.aks.shagra.utils

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.viewpager2.widget.ViewPager2
import com.aks.shagra.R
import com.google.gson.Gson
import com.stfalcon.imageviewer.StfalconImageViewer
import retrofit2.HttpException

fun <T : Any?> handleErrorBody(throwable: Throwable, aclass: Class<T>): T? {
    var data = MutableLiveData<T?>()
    try {
        val error: HttpException = throwable as HttpException
        val jObj = error.response()?.errorBody()!!.string()
        val gson = Gson()

        data.value = gson.fromJson(jObj, aclass)

    } catch (e: Exception) {
        Log.e("tag", "${aclass.simpleName} throwable: ${throwable.message}")
        data.value = null
    }

    return data.value

}


fun openFullScreenImages(context: Context, images: ArrayList<String>, firstToAppearPos: Int) {
    //   Log.e("TAG", "openFullScreenImages: $images pos $firstToAppearPos")

    try {
        val show = StfalconImageViewer.Builder<String>(context, images) { view, image ->
            view.load(image)

        }
        show.withStartPosition(firstToAppearPos )
        show.show()
    } catch (e: Exception) {
      context.showLongToast(context.getString(R.string.Issue_when_open_the_file))
    }

}

fun autoScrollToTheEnd(viewPager2: ViewPager2, delayTime: Long): ViewPager2.OnPageChangeCallback {
    val handler = Handler()
    return object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)

            val runnable = Runnable { viewPager2.setCurrentItem(position + 1) }
            if (position < viewPager2.adapter?.itemCount ?: 0) {
                handler.postDelayed(runnable, delayTime)
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
            super.onPageScrollStateChanged(state)

            /**
             * The user swiped forward or back and we need to
             * invalidate the previous handler.
             */
            if (state == ViewPager2.SCROLL_STATE_DRAGGING) handler.removeMessages(0)
        }
    }
}
