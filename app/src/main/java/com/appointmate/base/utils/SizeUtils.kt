package com.appointmate.base.utils

import android.content.Context
import android.util.TypedValue

@Suppress("unused")
object SizeUtils {

    fun dp2px(context: Context, dpValue: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.resources.displayMetrics).toInt()

    fun px2dp(context: Context, pxValue: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pxValue, context.resources.displayMetrics).toInt()

    fun sp2px(context: Context, spValue: Float) = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.resources.displayMetrics).toInt()

    fun px2sp(context: Context, pxValue: Float) = (pxValue / context.resources.displayMetrics.scaledDensity + 0.5f).toInt()

}
