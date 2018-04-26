package cn.tianyu.weatherapp.ui.interfaces

import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import cn.tianyu.weatherapp.R
import cn.tianyu.weatherapp.bean.toast
import cn.tianyu.weatherapp.common.extensions.ctx
import cn.tianyu.weatherapp.common.extensions.slideEnter
import cn.tianyu.weatherapp.common.extensions.slideExit
import cn.tianyu.weatherapp.ui.activity.SettingsActivity
import org.jetbrains.anko.startActivity

interface IToolbarManager {

    val toolbar: Toolbar

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_settings -> toolbar.ctx.startActivity<SettingsActivity>()
                else -> toast(message = "Unknown option")
            }
            true
        }
    }

    fun enableHomeAsUp(up: () -> Unit) {
        toolbar.navigationIcon = createUpDrawable()
        toolbar.setNavigationOnClickListener { up() }
    }

    private fun createUpDrawable() = DrawerArrowDrawable(toolbar.ctx).apply {
        progress = 1f
    }

    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }
}