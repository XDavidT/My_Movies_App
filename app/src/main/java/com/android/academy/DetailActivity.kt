package com.android.academy

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.academy.fragments.SimplePagerAdapter
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object{
        const val CURR_IDX = "idx"
        fun open(context: Context, currentIdx: Int){
            val activityDetails = Intent(context,DetailActivity::class.java)
            activityDetails.putExtra(CURR_IDX,currentIdx)
            context.startActivity(activityDetails)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val currentIdx = intent.getIntExtra(CURR_IDX,0)
        details_pager.adapter = SimplePagerAdapter(supportFragmentManager)
        details_pager.setCurrentItem(currentIdx,false)
    }
}
