package org.noandish.library.loginpage

import android.content.res.Resources

/**
 * Created by salman on 9/16/2018 AD.
 */
object Utils {

    val screenWidth: Int
        get() = Resources.getSystem().displayMetrics.widthPixels

    val screenHeight: Int
        get() = Resources.getSystem().displayMetrics.heightPixels

}