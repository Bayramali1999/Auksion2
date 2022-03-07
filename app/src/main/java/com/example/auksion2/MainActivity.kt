package com.example.auksion2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import com.example.auksion2.adapter.LotsAdapter
import com.example.auksion2.data.RequestLotsBody
import com.example.auksion2.data.ShortLotBeans
import com.example.auksion2.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: LotsAdapter
    private val list = mutableListOf<ShortLotBeans>()
    private var currentPage = 1

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ini()
        loadLotsByPage(currentPage)
    }

    private fun ini() {
        adapter = LotsAdapter(list)
        recycler_view.adapter = adapter;
    }

    override fun onResume() {
        super.onResume()


        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v!!.getChildAt(0).measuredHeight - v.measuredHeight) {
                progress_bar.visibility = View.VISIBLE
                currentPage++
                loadLotsByPage(currentPage)
            }
        })
    }

    private fun loadLotsByPage(currentPage: Int) {
        val reqBody = RequestLotsBody(5, "1.3.7", "uz", currentPage = "$currentPage", 0)
        viewModel.refreshData(reqBody)
        viewModel.getLotsByLiveData.observe(this) {
            if (it != null) {
                this.list.addAll(it.shortLotBeans)
                adapter.updateData();
            }
        }
    }
}