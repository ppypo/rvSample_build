package com.hzn.haylie.recyclerview

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.widget.Toast
import com.hzn.haylie.R
import com.hzn.haylie.RvAdapter
import com.hzn.haylie.lib.EasyPullLayout
import kotlinx.android.synthetic.main.activity_recyclerview.*

class RecyclerViewActivity : AppCompatActivity() {

    companion object {
        const val START_FRACTION = 0.5f
        const val REFRESHING_TIME = 5000L
    }

    var list: List<String> = (1..30).map { "item_string $it" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recyclerview)
        initRecyclerView()
        initEasyPullLayout()
        epl.autoRefresh(EasyPullLayout.TYPE_EDGE_TOP)
    }

    private fun initRecyclerView() {
        rv.layoutManager = LinearLayoutManager(this)
            .apply {
            orientation = LinearLayoutManager.VERTICAL
        }
        rv.adapter = RvAdapter(this, list)
    }

    private fun initEasyPullLayout() {

        epl.setOnPullListener { type, fraction, changed ->

            // 초기화(ready + loading.. 시 변경처리)
            //lottie.setAnimation(R.raw.lottie_circle)

            topView.setFraction(START_FRACTION, fraction)
            if (fraction == 1f) {
                //LogUtil.d("initEasyPullLayout setOnPullListener ready : $type : $fraction : $changed")
                topView.ready()

            } else {
                //LogUtil.d("initEasyPullLayout setOnPullListener idle : $type : $fraction : $changed")
                topView.idle()
            }
        }

        // 최초 trigger 될 때 동작함
        epl.setOnTriggerListener {
            topView.triggered()
            simulateLoading()
        }
    }

    private fun simulateLoading() {
        Handler().postDelayed({
            Toast.makeText(this, getString(R.string.finish), Toast.LENGTH_SHORT).show()
            epl.stop()
            topView.stop()
        }, REFRESHING_TIME)
    }
}
