package com.example.auksion2

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import com.example.auksion2.data.FilterMap
import com.example.auksion2.data.RequestLotsBody
import com.example.auksion2.data.ShortLotBeans
import com.example.auksion2.listener.LotItemClickListener
import com.example.auksion2.listener.OnDialogItemSelected
import com.example.auksion2.viewmodel.SharedViewModel
import com.example.auksion2.widget.ActiveDialog
import com.example.auksion2.widget.LotsAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: LotsAdapter
    private val list = mutableListOf<ShortLotBeans>()
    private var currentPage = 1
    private var isFiltered = false
    private var filterMap: FilterMap? = null
    private lateinit var pd: ProgressDialog

    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ini()
    }

    private fun ini() {
        pd = ProgressDialog(this)
        btnSortByField.setOnClickListener {
            val intent = Intent(this, FilterActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnArrange.setOnClickListener {
            filterByOrder()
        }
        val listener = object : LotItemClickListener {
            override fun itemClicked(beans: ShortLotBeans) {
                val intent = Intent(this@MainActivity, LotDetailActivity::class.java)
                intent.putExtra("beans", beans)
                startActivity(intent)
            }
        }
        adapter = LotsAdapter(list, listener)
        recycler_view.adapter = adapter
    }

    private fun filterByOrder() {
        val listener = object : OnDialogItemSelected {
            override fun itemSelected(id: Int) {
                var orderBy = ""
                var orderType = ""
                when (id) {
                    0 -> {
                        orderBy = "start_time"
                        orderType = "0"
                    }
                    1 -> {
                        orderBy = "start_time"
                        orderType = "1"
                    }
                    2 -> {
                        orderBy = "start_price"
                        orderType = "0"
                    }
                    3 -> {
                        orderBy = "start_price"
                        orderType = "1"
                    }
                    4 -> {
                        orderBy = "land_area"
                        orderType = "0"
                    }
                    5 -> {
                        orderBy = "land_area"
                        orderType = "1"
                    }
                    6 -> {
                        orderBy = "view_count"
                        orderType = "0"
                    }
                    7 -> {
                        orderBy = "view_count"
                        orderType = "1"
                    }
                }

                if (filterMap == null) {
                    filterMap = FilterMap(orderby_ = orderBy, order_type = orderType)
                } else {
                    filterMap!!.order_type = orderType
                    filterMap!!.orderby_ = orderBy
                }
                pd.setTitle("Loading....")
                pd.show()
                this@MainActivity.list.clear()
                currentPage = 1
                loadLotsByPage()
            }
        }
        val dialog = ActiveDialog()
        val list: Array<String> = arrayOf(
            "Yangisidan boshlab",
            "Eskisidan boshlab",
            "Qimmatidan boshlab",
            "Arzonidan boshlab",
            "Maydoni kattasidan",
            "Maydoni kichigidan",
            "Ko'p ko'rilganlar",
            "Kam ko'rilgan"
        )
        dialog.getData("Tartiblash", list, listener)
        dialog.show(supportFragmentManager, "tag")
    }

    override fun onResume() {
        super.onResume()


        scrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener { v, _, scrollY, _, _ ->
            if (scrollY == v!!.getChildAt(0).measuredHeight - v.measuredHeight) {
                progress_bar.visibility = View.VISIBLE
                currentPage++
                loadLotsByPage()
            }
        })

        intent.extras.let {
            if (it != null) {
                val myItem = it.getParcelable<FilterMap>("filter_map")
                if (myItem != null && (myItem.confiscant_groups_id != null ||
                            myItem.areas_id != null || myItem.regions_id != null
                            || myItem.lot_number != null ||
                            myItem.confiscant_categories_id != null ||
                            myItem.orderby_ != null || myItem.order_type != null)
                ) {
                    Log.d("myItem", "onResume: $myItem")
                    isFiltered = true
                    filterMap = myItem
                    loadLotsByPage()
                }
            }
        }

        if (!isFiltered) {
            loadLotsByPage()
        }
    }

    private fun loadLotsByPage() {
        val reqBody = RequestLotsBody(5, "1.3.7", "uz", currentPage = "$currentPage", 0, filterMap)
        viewModel.refreshData(reqBody)
        viewModel.getLotsByLiveData.observe(this) {
            if (it != null) {
                this.list.addAll(it.shortLotBeans)
                adapter.updateData()
            }
            progress_bar.visibility = View.GONE
            pd.dismiss()
        }
    }
}