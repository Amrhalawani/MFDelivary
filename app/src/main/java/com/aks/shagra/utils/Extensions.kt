package com.aks.shagra.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.*
import android.content.res.Resources
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.PorterDuff
import android.graphics.Shader
import android.graphics.Shader.TileMode
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.text.Editable
import android.text.Spanned
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.util.TypedValue
import android.view.*
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.math.RoundingMode
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import android.text.TextUtils
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aks.shagra.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.bottom_sheet_y_n.view.*

import java.text.*


val dateTimeFormat = "yyyy-MM-dd'T'H:mm:ss"
fun getDate(startDatetime: String, context: Context): String? {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        var formatter = DateTimeFormatter.ofPattern(dateTimeFormat)
        var date = LocalDateTime.parse(startDatetime, formatter)

        val currentDate = (Calendar.getInstance() as GregorianCalendar).toZonedDateTime()
        val year = date?.year?.toString()
        val month = date?.month?.toString()
        val day: String
//        if (DateUtils.isToday(System.currentTimeMillis())) {
        if (date.dayOfWeek == currentDate.dayOfWeek) {
            day = context.getString(R.string.TODAY)
            return "${day}-${
                (date.month.toString().substring(0, 3)).toCamelCase()
            }-${year?.substring(year?.length!! - 2, year?.length!!)}"
        } else {
//            day = getDayByName("${date.dayOfWeek}".toUpperCase(), context).toString()
//            return "${day}-${date.month.toString().substring(0,3)}-${date.year}"
            return "${date?.dayOfMonth} ${
                date.month.toString().substring(0, 3)?.toCamelCase()
            }-${year?.substring(year?.length!! - 2, year?.length!!)}"
        }
    }
//    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
//    parser.timeZone = TimeZone.getTimeZone("UTC")
//    val parsed: Date? = parser.parse(startDatetime)
//    val currentDay:Int = parsed?.day!!
    return ""
}

fun getDate2(startDatetime: String, context: Context): String? {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        var formatter = DateTimeFormatter.ofPattern(dateTimeFormat)
        var date = LocalDateTime.parse(startDatetime, formatter)

        return "${date?.dayOfMonth} ${
            date.month.toString().substring(0, 3)?.toCamelCase()
        }-${
            date?.year?.toString()
                ?.substring(date?.year?.toString()?.length!! - 2, date?.year?.toString()?.length!!)
        }"
    }
//    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
//    parser.timeZone = TimeZone.getTimeZone("UTC")
//    val parsed: Date? = parser.parse(startDatetime)
//    val currentDay:Int = parsed?.day!!
    return ""
}

fun getDate(stringDate: String): Date {
    val date: Date = SimpleDateFormat("dd-MM-yyyy").parse(stringDate)
    return date
}

fun getHistoryDate(stringDate: String): String {
    try {
        val date: Date = SimpleDateFormat(
            "yyyy-MM-dd'T'H:mm:ss",
            Locale.ENGLISH
        ).parse(stringDate)


        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(date.time)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH)
        val year = calendar.get(Calendar.YEAR)
        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)
        var AM_PM = if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
            "AM"
        } else {
            "PM"
        }
        return "${dayOfMonth} ${month} ${year} ${hours}:${minutes} ${AM_PM}"
    } catch (e: Exception) {
        return ""
    }
}

//fun getDay(startDatetime: String, context: Context): String? {
//    try {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            // Format Parsed Date
//            var formatter = DateTimeFormatter.ofPattern(dateTimeFormat)
//            var date = LocalDateTime.parse(startDatetime, formatter)
//            // Get Current Date
//            val currentDate = (Calendar.getInstance() as GregorianCalendar).toZonedDateTime()
//            val day: String
//            if (date.dayOfWeek == currentDate.dayOfWeek)
//                day = context.getString(R.string.TODAY)
//            else
//                day = getDayByName("${date.dayOfWeek}".toUpperCase(), context)?.toCamelCase()
//                    .toString()
//            return day
//        }
//        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
//        parser.timeZone = TimeZone.getTimeZone("UTC")
//        val parsed: Date? = parser.parse(startDatetime)
//        val currentDay: Int = parsed?.day!!
//        return getDayByIndex(currentDay, context)
//    } catch (e: Exception) {
//        Log.e("getDay", "getDay: ${e.message.toString()}")
//    }
//    return ""
//}

fun getTodaysDate(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
    val dateText: String = sdf.format((Calendar.getInstance() as GregorianCalendar).time)
    Log.e("TAG", "uCantSpinIfYouSpinnedToday: $dateText")
    return dateText
}

fun getDateFormatted(inputDate: String): String {

    val input = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val output = SimpleDateFormat("dd-MM-yyyy")
    try {
        val getAbbreviate = input.parse(inputDate)    // parse input
        return output.format(getAbbreviate)    // format output
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}

fun getTime(startDatetime: String, context: Context): String? {
    try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            var formatter = DateTimeFormatter.ofPattern(dateTimeFormat)
            var date = LocalDateTime.parse(startDatetime, formatter)

            val currentDate = (Calendar.getInstance() as GregorianCalendar).toZonedDateTime()
            val year = date?.year?.toString()
            val month = date?.month?.toString()
            val day: String
            val time = String.format(Locale.ENGLISH, "%02d : %02d", date.hour, date.minute)
            if (date.dayOfWeek == currentDate.dayOfWeek) {
//                return convertFrom24To12Format(date.hour, date.minute)
                return time
            } else {
                return "${getDate(startDatetime, context)} ${time}"
//                return "${getDate(startDatetime, context)} (${convertFrom24To12Format(date.hour, date.minute)})"?.toCamelCase()
            }
        }
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        parser.timeZone = TimeZone.getTimeZone("UTC")
        val parsed: Date? = parser.parse(startDatetime)
        val time = convertFrom24To12Format(parsed?.hours!!, parsed?.minutes!!)
        return time.toString()
    } catch (e: Exception) {
        Log.e("getTime", "getTime: ${e.message.toString()}")
    }
    return ""
}

fun getTimeLeft(date: String? /*context: Context*/): String? {

    val sdf = SimpleDateFormat("yyyy-MM-dd'T'H:mm:ss")
    //  sdf.timeZone = TimeZone.getTimeZone("GMT")
    if (date != null) {
        try {
            val time = sdf.parse(date).time
            val now = System.currentTimeMillis()

            val diff: Long = time - now
            val diffSeconds = diff / 1000
            val diffMinutes = diff / (60 * 1000)
            val diffHours = diff / (60 * 60 * 1000)
            val diffDays = diff / (24 * 60 * 60 * 1000)

            var relativeTime = ""
            if (diffDays > 1) {
                relativeTime = "$diffDays days"
            } else if (diffDays > 0) {
                relativeTime = "$diffDays days $diffHours hours"
            } else if (diffHours > 1) {
                relativeTime = "$diffHours hours"
            } else if (diffMinutes > 0) {
                relativeTime = "$diffMinutes minutes."
            } else {
                relativeTime = diffSeconds.toString()
            }

            Log.e(
                "TAG",
                "getTimeLeft: diffDays:${diffDays}, diffHours:${diffHours}, relativeTime:${relativeTime}, "
            )

            return relativeTime.toString()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }
    return ""

}

fun convertUnixTimeToTimeDate(unixTime: Long /*context: Context*/): String? {

    try {
        val sdf = SimpleDateFormat("Y-m-d")
        val netDate = Date(unixTime *1000)
        return sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
    return ""
}



fun convertUnixTimeToTimeDate2(unixTime: Long /*context: Context*/): String? {

    try {
        val sdf = SimpleDateFormat("Y-m-d",Locale.ENGLISH)
        val netDate = Date(unixTime.toLong() * 1000)
        return sdf.format(netDate)
    } catch (e: Exception) {
        return e.toString()
    }
    return ""
}

fun convertTimeDateToUnixTime(date: String): Long? {
    return try {
        val date = SimpleDateFormat("yyyy-MM-dd'T'H:mm:ss").parse(date)
        date.time
    } catch (e: Exception) {
        0
    }
}

/*
* hour in 24Format -> AM/PM Format
*/
fun convertFrom24To12Format(hour: Int, minutes: Int): String? {
    var hour = hour
    val meridiem = if (hour > 11) "PM" else "AM"
    // trim "0-23 hour" to "0-11 hour", then replace "0" with "12"
    hour = if (12.also { hour %= it } == 0) 12 else hour
    // Apply desired format "HH:MM AM/PM"
//            return String.format("%02d:%02d %s", hour, minutes, meridiem)
//    return String.format("%02d : %02d",
//        persianToEnglish((hour+2).toString()).toInt(),
//        persianToEnglish(minutes.toString()).toInt())
    return String.format(Locale.ENGLISH, "%02d : %02d", (hour + 2), minutes)
}

//fun getDayByIndex(dayIndex: Int, context: Context): String? {
//    val day =
//        when (dayIndex) {
//            1 -> context.getString(R.string.Saturday)
//            2 -> context.getString(R.string.Sunday)
//            3 -> context.getString(R.string.Monday)
//            4 -> context.getString(R.string.Tuesday)
//            5 -> context.getString(R.string.Wednesday)
//            6 -> context.getString(R.string.Thursday)
//            7 -> context.getString(R.string.Friday)
//            else -> ""
//        }
//    return day
//}

//fun getDayByName(dayName: String, context: Context): String? {
//    val day =
//        when (dayName) {
//            "SATURDAY" -> context.getString(R.string.Saturday)
//            "SUNDAY" -> context.getString(R.string.Sunday)
//            "MONDAY" -> context.getString(R.string.Monday)
//            "TUESDAY" -> context.getString(R.string.Tuesday)
//            "WEDNESDAY" -> context.getString(R.string.Wednesday)
//            "THURSDAY" -> context.getString(R.string.Thursday)
//            "FRIDAY" -> context.getString(R.string.Friday)
//            else -> ""
//        }
//    return day
//}

fun getCurrentDateTime(): String {
    val dateTime: String = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        .format(Calendar.getInstance().getTime())
    return dateTime
}

fun Double.roundOneDecimal(): Double? {
    try {
        val value = persianToEnglish(this.toString()).toDouble()
        val df = DecimalFormat("#.#", DecimalFormatSymbols(Locale.ENGLISH)).apply {
            roundingMode = RoundingMode.HALF_UP
        }
        return df.format(value).toDouble()

//        val df = DecimalFormat("#.#")
//        df.roundingMode = RoundingMode.CEILING
//        return df.format(value).toDouble()
    } catch (e: Exception) {
        return this
    }

}

fun Double.roundTo(n: Int): Double {
    return "%.${n}f".format(this).toDouble()
}

fun persianToEnglish(persianStr: String): String {
    var result = ""
    var en = '0'
    for (ch in persianStr) {
        en = ch
        when (ch) {
            '۰' -> en = '0'
            '۱' -> en = '1'
            '۲' -> en = '2'
            '۳' -> en = '3'
            '۴' -> en = '4'
            '۵' -> en = '5'
            '۶' -> en = '6'
            '۷' -> en = '7'
            '۸' -> en = '8'
            '۹' -> en = '9'
        }
        result = "${result}$en"
    }
    return result
}

//fun Context.openWebViewWiththeLink(url: String) {
//    val intent = Intent(this, WebViewActivity::class.java)
//    intent.putExtra(WebViewActivity::class.java.simpleName, url)
//    startActivity(intent)
//}

fun String.toCamelCase(): String? {
    return if (TextUtils.isEmpty(this)) {
        ""
    } else Character.toUpperCase(this[0]).toString() +
            this.substring(1).toLowerCase()
}

fun String.toCamelCaseSentence(): String? {
    val strArr = this.split(" ")
    strArr.forEach { it.toCamelCase() }
    return strArr.toString()
}

fun String.toCamelCaseSentence(delimiter: String): String? {
    val strArr = this.split(delimiter)
    strArr.forEach { it.toCamelCase() }
    return strArr.toString()
}

fun AppCompatActivity.setUpActionBar(toolbar: Toolbar) {
    this.setSupportActionBar(toolbar)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)
    toolbar.setNavigationOnClickListener { finish() }
}

inline fun <reified T : Activity> Context.openActivity(block: Intent.() -> Unit = {}) {
    val intent = Intent(this, T::class.java)
    block(intent)
    startActivity(intent)
}

// to handle click Listener for Views
fun View.click(block: () -> Unit) {
    setOnClickListener {
        block()
    }
}

// to handle longClick Listener for Views
fun View.longClick(block: () -> Unit) {
    setOnLongClickListener {
        block()
        return@setOnLongClickListener true
    }
}

//
//fun View.rotateInfinity() {
//    this.startAnimation(
//        AnimationUtils.loadAnimation(
//            this.context, R.anim.rotate_indefinitely
//        )
//    )
//}
//
//fun View.rotateInfinityAntiClockWise() {
//    this.startAnimation(
//        AnimationUtils.loadAnimation(
//            this.context, R.anim.rotate_indefinitely_anti_clockwise
//        )
//    )
//}

fun Context.hasNavBar(): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
        // navigation bar was introduced in Android 4.0 (API level 14)
        val resources: Resources = this.getResources()
        val id = resources.getIdentifier("config_showNavigationBar", "bool", "android")
        if (id > 0) {
            resources.getBoolean(id)
        } else {    // Check for keys
            val hasMenuKey = ViewConfiguration.get(this).hasPermanentMenuKey()
            val hasBackKey = KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
            !hasMenuKey && !hasBackKey
        }
    } else {
        false
    }
}

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Window.decorateStatusBar() {
    this.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
//    this.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

}

fun getHardwareAndSoftwareInfo(): String? {
//    "PLATFORM" = "Android or IOS"
    Build.SERIAL
    Build.MODEL
    Build.ID
    Build.MANUFACTURER
    Build.BRAND
    Build.TYPE
    Build.USER
    Build.VERSION_CODES.BASE
    Build.VERSION.INCREMENTAL
    Build.VERSION.SDK
    Build.BOARD
    Build.HOST
    Build.FINGERPRINT
    Build.VERSION.RELEASE
    Build.VERSION.BASE_OS
    return null
}

//fun ImageView.setTintColor(isYellow: Boolean, contextCompat: Activity) {
//    if (isYellow) {
//        this.setColorFilter(
//            ContextCompat.getColor(
//                contextCompat,
//                R.color.colorBackgroundPrimary
//            ), PorterDuff.Mode.SRC_IN
//        )
//    } else {
//        this.setColorFilter(
//            ContextCompat.getColor(
//                contextCompat,
//                R.color.iconCircle
//            ), PorterDuff.Mode.SRC_IN
//        )
//    }
//}

fun Window.hideSystemUI2() {
    this.decorView.setSystemUiVisibility(
        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
//                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                or View.SYSTEM_UI_FLAG_IMMERSIVE
    )
//    WindowCompat.setDecorFitsSystemWindows(this, false)
//    WindowInsetsControllerCompat(this, this.decorView)
//        .let { controller ->
//            controller.hide(WindowInsetsCompat.Type.systemBars())
//            controller.systemBarsBehavior = WindowInsetsControllerCompat
//                    .BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//            controller.show(WindowInsetsCompat.Type.statusBars())
//        }
    /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
         WindowInsetsController.OnControllableInsetsChangedListener(*//*this, WindowInsetsCompat.Type.navigationBars()*//*)
            .onControllableInsetsChanged(this, WindowInsetsCompat.Type.navigationBars())
    }*/
}

fun Window.showSystemUI2() {
    WindowCompat.setDecorFitsSystemWindows(this, true)
    WindowInsetsControllerCompat(this, this.decorView)
        .show(WindowInsetsCompat.Type.systemBars())
}

//fun Context.getDeviceId(): String? {
//    return Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)
//}

@Suppress("DEPRECATION")
fun Window.toggleFullScreen() {
    this.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    if (this.decorView.systemUiVisibility == View.SYSTEM_UI_FLAG_VISIBLE) {
        this.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        this.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or View.SYSTEM_UI_FLAG_FULLSCREEN
    } else {
        this.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        this.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
    }
}

@RequiresApi(Build.VERSION_CODES.R)
fun Window.hideStatusBars() {
    this.insetsController?.hide(WindowInsets.Type.statusBars())
}

@RequiresApi(Build.VERSION_CODES.R)
fun Window.showStatusBars() {
    this.insetsController?.show(WindowInsets.Type.statusBars())
}

fun Window.decorateStatusBar2() {
    this.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

}

fun Window.hideBottomNavigation() {
    this.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN)
}

fun Window.showBottomNavigation() {
    this.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_FULLSCREEN)
}

fun Window.hideSystemUI() {
    this.decorView.systemUiVisibility = (
//            View.SYSTEM_UI_FLAG_IMMERSIVE
//            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
}

fun Window.showSystemUI() {
    this.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
}

fun View.inVisible() {
    visibility = View.INVISIBLE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

// to load images url into Images
fun ImageView.load(url: String?) {
    Glide.with(this)
        .load(url)
        .into(this)
}
fun ImageView.loadCenterCrop(url: String?) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .into(this)
}

fun ImageView.load(url: String?, placeholder: Int) {
    Glide.with(this)
        .load(url)
        .error(placeholder)
        .into(this)
}

fun ImageView.load(resId: Int?) {
    Glide.with(this)
        .load(resId)
        .into(this)
}
fun ImageView.load(resId: Drawable?) {
    Glide.with(this)
        .load(resId)
        .into(this)
}

fun ShapeableImageView.load(url: String?) {
    Glide.with(this)
        .load(url)
//            .error(R.drawable.ic_pi)
        .into(this)
}


fun ShapeableImageView.load(resId: Int?) {
    Glide.with(this)
        .load(resId)
        .into(this)
}

// to load images url into Images
//fun CircleImageView.load(url: String?) {
//    Glide.with(this)
//        .load(url)
//        .into(this)
//}
//
//fun CircleImageView.load(resId: Int?) {
//    Glide.with(this)
//        .load(resId)
//        .into(this)
//}

fun String.safeInt(fallback: Int): Int {
    return this.toIntOrNull() ?: fallback
}

fun View.showConnectionSnackBar(message: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, message, duration).show()
}

fun Context.showToast(message: String?) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.showLongToast(message: String?) =
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()

fun Context.logi(message: String) =
    Log.i(Context::class.simpleName, message)

fun Context.loge(message: String) =
    Log.e(Context::class.simpleName, message)

fun Activity.getHeight(): Float {
    return resources.displayMetrics.heightPixels.toFloat()
//    return this.getWindowManager().defaultDisplay.height.toFloat()
}

fun Activity.getWidth(): Float {
    return resources.displayMetrics.widthPixels.toFloat()
//    return this.getWindowManager().defaultDisplay.width.toFloat()
}


//fun View.zoom_out() {
//    AnimationUtils.loadAnimation(this.context, R.anim.zoom_out)
//}
//
//fun View.zoom_in() {
//    AnimationUtils.loadAnimation(this.context, R.anim.zoom_in)
//}
//
//fun View.scale_in() {
//    AnimationUtils.loadAnimation(this.context, R.anim.scale_in)
//}
//
//fun View.scale_out() {
//    AnimationUtils.loadAnimation(this.context, R.anim.scale_out)
//}
//
//fun View.infinite_rotation_animation() {
//    AnimationUtils.loadAnimation(this.context, R.anim.infinite_rotation_animation)
//}
//
//fun View.rotate_indefinitely() {
//    AnimationUtils.loadAnimation(this.context, R.anim.rotate_indefinitely)
//}
//
//fun View.rotate_indefinitely_anti_clockwise() {
//    AnimationUtils.loadAnimation(this.context, R.anim.rotate_indefinitely_anti_clockwise)
//}

/*fun Context.isNetWorkAvailable(): Boolean {
    (this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo.also {
        return it != null && it.isConnected && it.isConnectedOrConnecting
    }
}*/

fun Context.isNetworkConnected(): Boolean {
    var result = false
    val connectivityManager =
        this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val networkCapabilities = connectivityManager?.activeNetwork ?: return false
        val activeNetwork =
            connectivityManager?.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager?.run {
            connectivityManager?.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
    }
    return result
}


fun Context.copyToClipboard(referalCode: String) {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("label", referalCode)
    clipboard.setPrimaryClip(clip)
}

fun Context.copyFromClipboard(): String? {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val txt = clipboard.primaryClip?.getItemAt(0)?.text
    return txt?.toString()
}

fun EditText.hideKeyboard(
) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as
            InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}


fun String.isValidEmail(): Boolean =
    this.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()

fun String.isValidPhone(): Boolean {
    return this.isNotEmpty() && length in 9..13
}

fun String.fromHtml(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}

fun String.isValidPassword(): Boolean {
    return this.length > 4
}

fun String.isValidName(): Boolean {
    return this.length > 3
}

fun String.isValidUserNameLength(): Boolean {
    return this.length in 3..25
}

fun String.showToast(errorString: Int, mContext: Context) {
    Toast.makeText(mContext, errorString, Toast.LENGTH_SHORT).show()
}

fun EditText.validateEmail(): Boolean {
    return this.text.toString().isValidEmail()
}


fun EditText.validateMobileNumber(): Boolean {
    return this.text.toString().isValidPhone()
}

fun EditText.validatePassword(): Boolean {
    return this.text.toString().isValidPassword()
}

fun EditText.validatePasswordLength(): Boolean {
    var pass: String = this.text.toString()
    return (pass.isNotEmpty() && pass.trim().length >= 8)
}

fun TextView.setGradientText0() {
    val textShader: Shader = LinearGradient(
        0f,
        0f,
        0f,
        20f,
        intArrayOf(Color.GREEN, Color.BLUE),
        floatArrayOf(0f, 1f),
        TileMode.REPEAT
    )
    this.paint.shader = textShader
}

fun TextView.setGradientText1() {
    val textShader: Shader = LinearGradient(
        0f, 0f, width.toFloat(), this.textSize,
        intArrayOf(
            Color.parseColor("#F97C3C"),
            Color.parseColor("#FDB54E"),
            Color.parseColor("#64B678"),
            Color.parseColor("#478AEA"),
            Color.parseColor("#8446CC")
        ), null, TileMode.CLAMP
    )
    this.paint.shader = textShader
}

fun EditText.validateName(): Boolean {
    return this.text.toString().isValidName()
}

fun View.margin(
    left: Float? = null,
    top: Float? = null,
    right: Float? = null,
    bottom: Float? = null
) {
    layoutParams<ViewGroup.MarginLayoutParams> {
        left?.run { leftMargin = dpToPx(this) }
        top?.run { topMargin = dpToPx(this) }
        right?.run { rightMargin = dpToPx(this) }
        bottom?.run { bottomMargin = dpToPx(this) }
    }
}

inline fun <reified T : ViewGroup.LayoutParams> View.layoutParams(block: T.() -> Unit) {
    if (layoutParams is T) block(layoutParams as T)
}

fun View.dpToPx(dp: Float): Int = context.dpToPx(dp)
fun Context.dpToPx(dp: Float): Int = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp,
    resources.displayMetrics
).toInt()

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

fun String.toRequestBody(): RequestBody =
    this.toRequestBody("text/plain".toMediaTypeOrNull())

fun File.toMultiPart(keyName: String): MultipartBody.Part {
    val requestFile: RequestBody =
        this.asRequestBody("application/octet-stream".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData(keyName, this.name, requestFile)
}

fun convertListToRequestBodies(list: ArrayList<String>): HashMap<String, RequestBody> {
    val requestBodies: HashMap<String, RequestBody> = HashMap()

    var position = 0 // alternative way to get position cuz hashmap didnt have positioning concept
    for (item in list) {
        requestBodies.put("ids[$position]", createPartFromString(item))
        position++
    }
    return requestBodies
}


fun createPartFromString(string: String): RequestBody {
    return RequestBody.create("multipart/form-data".toMediaTypeOrNull(), string.toString())
}

//shared Pref ext

//fun SharedPreferences.getCurrentLanguage(): String {
//    return getString(Constants.LANG_KEY, Locale.getDefault().language)!!
//}


fun SharedPreferences.getString(key: String): String? {
    return getString(key, null)
}

fun SharedPreferences.Editor.put(key: String, value: String) {
    putString(key, value).commit()
}

fun SharedPreferences.Editor.put(key: String, value: Long) {
    putLong(key, value).commit()
}

fun SharedPreferences.Editor.delete(key: String) {
    remove(key).commit()
}

fun SharedPreferences.Editor.clear() {
    clear().commit()
}

tailrec fun Context.activity(): Activity? = when {
    this is Activity -> this
    else -> (this as? ContextWrapper)?.baseContext?.activity()
}

fun View.syncVisibility(imageView: ImageView) {
    if (isVisible) {
        animate().alpha(0.0f)
            .setDuration(200)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    visibility = View.GONE
                    imageView.rotation = 0f
                }
            })
    } else {
        animate().alpha(1.0f)
            .setDuration(200)
            .setListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    visibility = View.VISIBLE
                    imageView.rotation = 90f
                }
            })
    }
}


fun setupConfirmBottomSheet(context: Activity, title: String, c: (view: View) -> Unit) {
    val view = context.layoutInflater.inflate(R.layout.bottom_sheet_y_n, null)
    val userDataDialog = BottomSheetDialog(context, R.style.AppBottomSheetDialogTheme)

    view.text_title_y_n.text = title
    view.text_yes.setOnClickListener {
        c(view)
        userDataDialog.cancel()
    }

    view.text_no.setOnClickListener {
        userDataDialog.cancel()
    }

    userDataDialog.setCancelable(true)
    userDataDialog.setContentView(view)
    userDataDialog.show()
}

fun openLink(context: Context, link: String?) {
    try {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        context.startActivity(browserIntent)
    } catch (e: Exception) {
        Toast.makeText(
            context,
            context.getString(R.string.error_when_opening_the_link),
            Toast.LENGTH_SHORT
        ).show()
    }
}

fun setAppDarkModeEnabled(isEnabled:Boolean)
{
    //sometimes make issues like Fragment not attached to a context
    if (isEnabled) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    } else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}

fun watchYoutubeVideo(context: Context, link: String) {

    if (!link.isEmpty()) {
        val sp: Array<String> = link.split("v=").toTypedArray()
        val id = sp[1]
        val appIntent = Intent(
            Intent.ACTION_VIEW, Uri.parse(
                "vnd.youtube:$id"
            )
        )
        val webIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(link)
        )
        try {
            context.startActivity(appIntent)
        } catch (ex: ActivityNotFoundException) {
            context.startActivity(webIntent)
        }
    } else {
        Toast.makeText(context, context.getString(R.string.notAvailable), Toast.LENGTH_LONG).show()
    }


}

fun RecyclerView.addOnScrolledToEnd(onScrolledToEnd: () -> Unit) {

    this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        // The minimum amount of items to have below your current scroll position
        // before loading more.
        private val VISIBLE_THRESHOLD = 5

        private var loading = true
        private var previousTotal = 0

        override fun onScrollStateChanged(
            recyclerView: RecyclerView,
            newState: Int
        ) {

            with(layoutManager as LinearLayoutManager) {

                val visibleItemCount = childCount
                val totalItemCount = itemCount
                val firstVisibleItem = findFirstVisibleItemPosition()

                if (loading && totalItemCount > previousTotal) {

                    loading = false
                    previousTotal = totalItemCount
                }

                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {

                    onScrolledToEnd()
                    loading = true
                }
            }
        }
    })
}



fun callPhone(number: String, activity: Activity) {
    val dialIntent = Intent(Intent.ACTION_DIAL)
    dialIntent.data = Uri.parse("tel:$number")
    activity.startActivity(dialIntent)
}

//fun composeEmail(addresses: Array<String?>?, subject: String?, activity: Activity) {
//    val intent = Intent(Intent.ACTION_SENDTO)
//    intent.data = Uri.parse("mailto:") // only email apps should handle this
//    intent.putExtra(Intent.EXTRA_EMAIL, addresses)
//    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
//    if (intent.resolveActivity(activity?.packageManager!!) != null) {
//        activity.startActivity(intent)
//    }
//}


