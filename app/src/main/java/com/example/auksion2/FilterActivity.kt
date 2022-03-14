package com.example.auksion2

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.auksion2.data.FilterMap
import com.example.auksion2.data.filter.FilterResponse
import com.example.auksion2.data.filter.FilterResponseItems
import com.example.auksion2.data.lot.PostDetailReq
import com.example.auksion2.listener.OnDialogItemSelected
import com.example.auksion2.viewmodel.SharedViewModel
import com.example.auksion2.widget.ActiveDialog
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {
    private val vm by lazy { ViewModelProvider(this)[SharedViewModel::class.java] }
    private val dialog = ActiveDialog()
    private lateinit var filterResponse: FilterResponse
    private var filterMap: FilterMap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        filterMap = FilterMap()
        val body = PostDetailReq(7, "1.3.7", "uz")
        vm.loadFilterDetail(body)
        vm.filterResponse.observe(this) {
            if (it != null) {
                this.filterResponse = it
            }
        }
        bindView()

    }

    private fun bindView() {
        back_arrow.setOnClickListener {
            onBackPressed()
        }

        btn_search_item.setOnClickListener {
            if (!TextUtils.isEmpty(search_by_id.text)) {
                filterMap!!.lot_number = search_by_id.text.toString()
            }
            val intent = Intent(this@FilterActivity, MainActivity::class.java)
            intent.putExtra("filter_map", filterMap)
            startActivity(intent)
            finish()
        }

        btn_clear.setOnClickListener {
            filterMap = null
            spinner_actives.text = "Mol mulk guruxlari"
            spinner_type.text = "Mulk toifasi"
            spinner_type.isEnabled = false
            spinner_province.text = "Viloyat"
            spinner_area.text = "Tuman"
            spinner_area.isEnabled = false
            search_by_id.setText("")
        }

    }

    override fun onResume() {
        super.onResume()
        spinner_type.isEnabled = false
        spinner_area.isEnabled = false

        spinner_actives.setOnClickListener {
            val groups = filterResponse.groups
            val listener = object : OnDialogItemSelected {
                override fun itemSelected(id: Int) {
                    var groupId = -1
                    if (id != -1) {
                        groupId = groups[id].id!!
                        spinner_actives.text = groups[id].name
                        spinner_type.isEnabled = true
                        filterMap!!.confiscant_groups_id = groupId
                    }
                }
            }
            selectItemFilter("Mulk guruxlari", groups, listener)
        }

        spinner_type.setOnClickListener {
            val categories = filterResponse.categories
            val list = mutableListOf<FilterResponseItems>()

            val listener = object : OnDialogItemSelected {
                override fun itemSelected(id: Int) {
                    if (id != -1) {
                        spinner_type.text = list[id].name
                        filterMap!!.confiscant_categories_id = list[id].id
                    }
                }
            }
            if (this.filterMap!!.confiscant_groups_id != -1) {
                categories.forEach {
                    if (it.confiscant_groups_id == filterMap!!.confiscant_groups_id) {
                        list.add(it)
                    }
                }
                selectItemFilter("Mul-mulk toifalari", list, listener)
            }
        }

        spinner_area.setOnClickListener {
            val areas = filterResponse.areas
            val list = mutableListOf<FilterResponseItems>()

            val listener = object : OnDialogItemSelected {
                override fun itemSelected(id: Int) {
                    if (id != -1) {
                        spinner_area.text = list[id].name
                        filterMap!!.areas_id = list[id].id
                    }
                }
            }
            if (filterMap!!.regions_id != -1) {
                areas.forEach {
                    if (it.regions_id == filterMap!!.regions_id) {
                        list.add(it)
                    }
                }
                selectItemFilter("Viloyat", list, listener)
            }
        }

        spinner_province.setOnClickListener {
            val regions = filterResponse.regions
            val listener = object : OnDialogItemSelected {
                override fun itemSelected(id: Int) {
                    var regionId = -1
                    if (id != -1) {
                        regionId = id + 1
                        spinner_province.text = regions[id].name
                        spinner_area.isEnabled = true
                        filterMap!!.regions_id = regionId
                    }
                }
            }
            selectItemFilter("Tuman", regions, listener)
        }
    }

    private fun selectItemFilter(
        s: String,
        list: MutableList<FilterResponseItems>?,
        listener: OnDialogItemSelected
    ) {
        if (this::filterResponse.isInitialized) {
            val regions = Array<String>(list!!.size) { i -> "" }
            for (i in 0 until list.size) {
                val it = list[i].name
                regions[i] = it + ""
            }
            dialog.getData(s, regions, listener)
            dialog.show(supportFragmentManager, "dasdas")
        }
    }
}