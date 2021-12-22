import android.content.Context
import android.content.res.Resources.getSystem
import android.util.DisplayMetrics

public class UiUtil {

    private val displayMetrics: DisplayMetrics by lazy { getSystem().displayMetrics }

    val Int.px2dp: Int get() = (this / getSystem().displayMetrics.density).toInt()
    val Int.dp2px: Int get() = (this * getSystem().displayMetrics.density).toInt()

    val screenWidthPx: Int get() = displayMetrics.widthPixels
    val screenHeightPx: Int get() = displayMetrics.heightPixels
    val screenWidthDp: Int  = displayMetrics.widthPixels.px2dp
    val screenHeightDp: Int = displayMetrics.heightPixels.px2dp

    public fun getScreenWidthInPx(context: Context): Int = displayMetrics.widthPixels
    public fun getScreenHeightInPx(context: Context): Int = displayMetrics.heightPixels
}