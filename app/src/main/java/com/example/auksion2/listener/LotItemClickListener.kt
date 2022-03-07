package com.example.auksion2.listener

import com.example.auksion2.data.ShortLotBeans

interface LotItemClickListener {
    fun itemClicked(beans: ShortLotBeans)
}