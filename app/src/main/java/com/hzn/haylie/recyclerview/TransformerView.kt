package com.hzn.haylie.recyclerview

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.hzn.haylie.R
import com.hzn.haylie.util.LogUtil
import kotlinx.android.synthetic.main.view_refresh.view.*
import java.text.SimpleDateFormat
import java.util.*

/**
 * Transformer View
 *
 * Created by huzenan on 2017/8/6.
 */
class TransformerView : LinearLayout {

    private var date: String? = context.getString(R.string.refreshing_date)

    /*var animatorHandler = Handler()
    private val showDecepticonsRunnable: Runnable = Runnable {
        showDecepticons()
    }
    private val showAutobotsRunnable: Runnable = Runnable {
        showAutobots()
    }*/

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        LayoutInflater.from(getContext()).inflate(R.layout.view_refresh, this, true)
    }

    fun idle() {
        tvDesc.text = context.getString(R.string.idle)
        tvDate.text = date
    }

    fun ready() {
        tvDesc.text = context.getString(R.string.ready)
    }

    fun triggered() {
        date = SimpleDateFormat("yyyy-MM-dd  hh:mm:ss", Locale.getDefault()).format(Date())
        tvDesc.text = context.getString(R.string.triggered)
        tvDate.text = date
        showDecepticons()
    }

    private fun showDecepticons() {
        LogUtil.d("showDecepticons")

        // ready + loading.. 시점
        lottie.setAnimation(R.raw.lottie_circle)
        lottie.playAnimation()

        /*epvAutobots.addOnAnimatorListener(object : EasyPathView.OnAnimatorListener() {
            override fun onAnimEnd(state: Int) {
                epvAutobots.visibility = View.GONE
                epvDecepticons.visibility = View.VISIBLE
                epvDecepticons.addOnAnimatorListener(object : EasyPathView.OnAnimatorListener() {
                    override fun onAnimEnd(state: Int) {
                        animatorHandler.postDelayed(showAutobotsRunnable, 500)
                    }
                })
                epvDecepticons.startDraw()
            }
        })
        epvAutobots.startErase()*/
    }


    fun setFraction(startFraction: Float?, currentFraction: Float?) {
        LogUtil.d("setFraction $startFraction : $currentFraction")

        if (null == startFraction || null == currentFraction)
            return

        lottie.progress = currentFraction
        lottie.scaleX = currentFraction
        lottie.scaleY = currentFraction
        //LogUtil.d("lottie.scale : ${lottie.scale}")
        LogUtil.d("lottie.scalex : ${lottie.scaleX} / lottie.scaley : ${lottie.scaleY}")

        /*if (null == startFraction || null == currentFraction)
            return

        val fraction = (currentFraction - startFraction) / (1 - startFraction)
        when (type) {
            TYPE_TOP -> {
                epvAutobots.setAnimProgress(fraction)
            }
            TYPE_BOTTOM -> {
                epvDecepticons.setAnimProgress(fraction)
            }
        }*/
    }

    fun stop() {
        LogUtil.d("stop")

        /*epvAutobots.addOnAnimatorListener(null)
        epvAutobots.reset()
        epvDecepticons.addOnAnimatorListener(null)
        epvDecepticons.reset()
        animatorHandler.removeCallbacks(showAutobotsRunnable)
        animatorHandler.removeCallbacks(showDecepticonsRunnable)

        when (type) {
            TYPE_TOP -> {
                epvAutobots.visibility = View.VISIBLE
                epvDecepticons.visibility = View.GONE
                epvAutobots.startErase()
            }
            TYPE_BOTTOM -> {
                epvAutobots.visibility = View.GONE
                epvDecepticons.visibility = View.VISIBLE
                epvDecepticons.startErase()
            }
        }*/
    }
}