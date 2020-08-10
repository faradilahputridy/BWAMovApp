package com.androfara.bwamovapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.androfara.bwamovapp.R
import com.androfara.bwamovapp.home.dashboard.DashboardFragment
import com.androfara.bwamovapp.home.setting.SettingFragment
import com.androfara.bwamovapp.home.tiket.TiketFragment
import kotlinx.android.synthetic.main.activity_home_screen.*

class HomeScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)

        val fragmentHome =
            DashboardFragment()
        val fragmentTiket = TiketFragment()
        val fragmentSetting =
            SettingFragment()

        setFragment(fragmentHome)

        iv_menu1.setOnClickListener{
            setFragment(fragmentHome)

            changeIcon(iv_menu1, R.drawable.icon_home_active)
            changeIcon(iv_menu2, R.drawable.icon_tiket)
            changeIcon(iv_menu3, R.drawable.icon_akun)
        }

        iv_menu2.setOnClickListener {
            setFragment(fragmentTiket)

            changeIcon(iv_menu1, R.drawable.icon_home)
            changeIcon(iv_menu2, R.drawable.icon_tiket_active)
            changeIcon(iv_menu3, R.drawable.icon_akun)
        }

        iv_menu3.setOnClickListener {
            setFragment(fragmentSetting)

            changeIcon(iv_menu1, R.drawable.icon_home)
            changeIcon(iv_menu2, R.drawable.icon_tiket)
            changeIcon(iv_menu3, R.drawable.icon_akun_active)
        }
    }

    private fun setFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int : Int){
        imageView.setImageResource(int)
    }
}