package com.example.auksion2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.auksion2.data.filter.FilterResponseItems
import com.example.auksion2.data.lot.PostDetailReq
import com.example.auksion2.listener.OnDialogItemSelected
import com.example.auksion2.viewmodel.SharedViewModel
import com.example.auksion2.widget.ActiveDialog
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity() {
    private val vm by lazy { ViewModelProvider(this)[SharedViewModel::class.java] }
    private val regionList = mutableListOf<FilterResponseItems>()
    private val areaList = mutableListOf<FilterResponseItems>()
    private val groupList = mutableListOf<FilterResponseItems>()
    private val categoryList = mutableListOf<FilterResponseItems>()
    private val dialog = ActiveDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val body = PostDetailReq(7, "1.3.7", "uz")
        vm.loadFilterDetail(body)
        vm.filterResponse.observe(this) {
            if (it != null) {
                regionList.clear()
                regionList.addAll(it.regions)
                areaList.clear()
                areaList.addAll(it.areas)
                groupList.clear()
                groupList.addAll(it.groups)
                categoryList.clear()
                categoryList.addAll(it.categories)
            }
        }
        bindView()
    }

    private fun bindView() {
        back_arrow.setOnClickListener {
            val intent = Intent(this@FilterActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onResume() {
        super.onResume()

        spinner_type.isClickable = false
        spinner_area.isClickable = false

        spinner_actives.setOnClickListener {
            val listener = object : OnDialogItemSelected {
                override fun itemSelected(id: Int) {

                }
            }
//            selectItemFilter("Mulk guruxlari", groupList, listener)


        }

        spinner_type.setOnClickListener {
            val listener = object : OnDialogItemSelected {
                override fun itemSelected(id: Int) {

                }
            }
//            selectItemFilter("Mul-mulk toifalari", categoryList, listener)
        }

        spinner_area.setOnClickListener {

            val listener = object : OnDialogItemSelected {
                override fun itemSelected(id: Int) {

                }
            }
//            selectItemFilter("Viloyat", regionList, listener)
        }

        spinner_province.setOnClickListener {
            val listener = object : OnDialogItemSelected {
                override fun itemSelected(id: Int) {

                }
            }
//            selectItemFilter("Tuman", areaList, listener)
        }


    }

    private fun selectItemFilter(
        s: String,
        list: MutableList<FilterResponseItems>,
        listener: OnDialogItemSelected
    ) {
        val actives: Array<String> = arrayOf()
        for (i in 0..list.size) {
            actives[i] = list[i].name!!
        }
        dialog.getData(s, actives, listener)
        dialog.show(supportFragmentManager, "")
    }
}