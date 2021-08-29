package com.thangdn6.weatherapp.extension

import android.app.Activity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.thangdn6.weatherapp.R

object UIHelper {

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager

        if(imm.isAcceptingText){
            imm.hideSoftInputFromWindow(
                activity.currentFocus?.windowToken,
                0
            );
        }
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }


    // Set up touch listener for non-text box views to hide keyboard.
    fun setupUI(view: View,activity:Activity) {

        if (view !is EditText) {
            view.setOnTouchListener(object : View.OnTouchListener{
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    hideKeyboard(activity)
                    return false
                }

            })
        }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                val innerView = view.getChildAt(i)
                setupUI(innerView,activity)
            }
        }
    }

    //

}
@BindingAdapter("set_img")
fun bindNewsImage(img: ImageView, link:String) {
    Glide.with(img).load(link)
        .error(R.drawable.icon)
        .into(img)
}