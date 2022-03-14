package com.example.auksion2

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.afdhal_fa.imageslider.`interface`.ItemClickListener
import com.afdhal_fa.imageslider.model.SlideUIModel
import com.example.auksion2.data.ShortLotBeans
import com.example.auksion2.data.lot.ConfiscantImages
import com.example.auksion2.data.lot.PostDetailReq
import com.example.auksion2.viewmodel.SharedViewModel
import kotlinx.android.synthetic.main.activity_lot_detail.*
import java.text.SimpleDateFormat


class LotDetailActivity : AppCompatActivity() {

    private var expandable = true
    private val vm: SharedViewModel by lazy {
        ViewModelProvider(this)[SharedViewModel::class.java]
    }

    @SuppressLint("UseCompatLoadingForDrawables", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lot_detail)


    }

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()
        val bean = intent.getParcelableExtra<ShortLotBeans>("beans")

        if (bean != null) {
            val reqData =
                PostDetailReq(15, "1.3.7", "uz", bean.lot_number, "true", "id", 0)
            vm.loadLotDetail(reqData)
            vm.getLotDetailByLiveData.observe(this) {
                if (it != null) {
                    val lotbean1 = it.lotBean ?: return@observe
                    setImagesToSlider(lotbean1.confiscantImagesList!!)
                    tv_lot_please.text = lotbean1.name
                    tv_lot_id.text = "№ ${lotbean1.lot_number}"
                    val zp = lotbean1.zaklad_summa.let { it1 ->
                        if (it1 == null) return@let "0.0UZS"
                        Math.round(it1).toInt().toString() + "UZS"
                    }
                    tv_lot_coast.text = zp
                    val sp = lotbean1.start_price.let { it2 ->
                        if (it2 == null) return@let "0.0UZS"
                        Math.round(it2).toInt().toString() + "UZS"
                    }
                    tv_lot_coast_starter.text = sp
                    auksion_favorit.text = lotbean1.count_favourite.toString()
                    auksion_eye.text = lotbean1.view_count.toString()
                    auksion_item_name.text = lotbean1.area_name
                    location_auksion_sasda.text = lotbean1.joylashgan_manzil
                    lot_deadline.text = "Oxirgi sana ${lotbean1.order_end_time_str}"
                    val endTime: String = lotbean1.end_time!!.split(" ")[1]
                    time_auksion_sasda.text = "Auksion ${lotbean1.start_time} kuni $endTime"
                    setDataTimerView(lotbean1.order_end_time!!)
                    val molMul = lotbean1.joylashgan_manzil!!.split(", ")
                    tv_mol_mul_vil.text = molMul[0]
                    tv_mol_mul_tuman.text = molMul[1]
                    tv_mol_mul_manzil.text = molMul[2]

                    tv_mol_mul_1kun.text = lotbean1.f_visit_time1
                    tv_mol_mul_2kun.text = lotbean1.f_visit_time2
                    tv_mol_mul_3kun.text = lotbean1.f_visit_time3

                    sotuvchi_xaqida.text = lotbean1.user!!.name
                    val sotuvchiAdr = lotbean1.user.full_address!!.split(", ")

                    sotuvchi_manzil_vil.text = sotuvchiAdr[0]
                    sotuvchi_manzil_tuman.text = sotuvchiAdr[1]
                    sotuvchi_manzil_manzil.text = sotuvchiAdr[2]

                    sotuvchi_elektron_manzil.text = lotbean1.user.email + ""
                    sotuvchi_tele.text = lotbean1.user.additional_phones + ""


                    mol_mul_item1.setOnClickListener {
                        checkViewExpandable(1)
                        expandable = !expandable
                    }
                    ijro.setOnClickListener {
                        checkViewExpandable(2)
                        expandable = !expandable
                    }

                    sotuvchi.setOnClickListener {
                        checkViewExpandable(3)
                        expandable = !expandable
                    }

                    lot_malumotlari.setOnClickListener {
                        checkViewExpandable(4)
                        expandable = !expandable
                    }

                }
            }
        }
    }
    @Suppress("DEPRECATION")
    private fun checkViewExpandable(i: Int) {
        mol_mul_item1.setBackgroundResource(R.drawable.btn_background)
        mol_mul_item1.setTextColor(resources.getColor(R.color.black))
        molmulk_hidden.visibility = View.GONE

        ikro_item_1.setBackgroundResource(R.drawable.btn_background)
        ikro_item_1.setTextColor(resources.getColor(R.color.black))
        ijro_hidden.visibility = View.GONE

        sotuvchi_item1.setBackgroundResource(R.drawable.btn_background)
        sotuvchi_item1.setTextColor(resources.getColor(R.color.black))
        sotuvchi_hidden.visibility = View.GONE

        lot_malumotlari_item_1.setBackgroundResource(R.drawable.btn_background)
        lot_malumotlari_item_1.setTextColor(
            resources.getColor(R.color.black)
        )
        lot_malumotlari_hidden.visibility = View.GONE

        if (expandable) {
            when (i) {
                1 -> {
                    mol_mul_item1.setBackgroundResource(R.drawable.selected_background)
                    mol_mul_item1.setTextColor(resources.getColor(R.color.white))
                    molmulk_hidden.visibility = View.VISIBLE

                }
                2 -> {
                    ikro_item_1.setBackgroundResource(R.drawable.selected_background)
                    ikro_item_1.setTextColor(resources.getColor(R.color.white))
                    ijro_hidden.visibility = View.VISIBLE

                }
                3 -> {
                    sotuvchi_item1.setBackgroundResource(R.drawable.selected_background)
                    sotuvchi_item1.setTextColor(resources.getColor(R.color.white))
                    sotuvchi_hidden.visibility = View.VISIBLE
                }
                4 -> {
                    lot_malumotlari_item_1.setBackgroundResource(R.drawable.selected_background)
                    lot_malumotlari_item_1.setTextColor(resources.getColor(R.color.white))
                    lot_malumotlari_hidden.visibility = View.VISIBLE
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun setDataTimerView(orderEndTime: String) {
        try {
            val sd = SimpleDateFormat("dd.MM.yyyy hh:mm:ss")
            val date = sd.parse(orderEndTime)
            val deadlineDate = date!!.time
            val dateNow = System.currentTimeMillis()
            val dataDifference: Long = deadlineDate - dateNow
            setCountDownTimer(dataDifference)
        } catch (e: Exception) {
            Log.d("TAG", "setDataTimerView: ")
        }

    }

    private fun setCountDownTimer(dataDifference: Long) {
        object : CountDownTimer(dataDifference, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(l: Long) {
                val l1 = l / 1000
                val days = (l1 / 86400).toInt()
                val hours = ((l1.toInt() % 86400) / 3600)
                val minutes = (((l1 % 86400) % 3600) / 60).toInt()
                val seconds = (l1 % 60).toInt()

                timer_day.text = getDateText(days)
                timer_hour.text = getDateText(hours)
                timer_minute.text = getDateText(minutes)
                timer_seconds.text = getDateText(seconds)
            }

            override fun onFinish() {

            }
        }.start()
    }

    private fun getDateText(days: Int): String {
        return if (days / 10 == 0) {
            "0$days"
        } else {
            days.toString()
        }
    }

    private fun setImagesToSlider(confiscantImagesList: List<ConfiscantImages>) {
        val slideList = arrayListOf<SlideUIModel>()
        confiscantImagesList.forEach {
            val imageUrl = getImageByHash(it.file_hash!!)
            slideList.add(SlideUIModel(imageUrl, it.image_positions_name))
        }
        imageSlide.setImageList(slideList)
        imageSlide.setItemClickListener(object : ItemClickListener {
            override fun onItemClick(model: SlideUIModel, position: Int) {
                Toast.makeText(this@LotDetailActivity, "${model.title}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getImageByHash(fileHash: String) =
        "https://files.e-auksion.uz/files-worker/api/v1/images?file_hash=$fileHash"
}