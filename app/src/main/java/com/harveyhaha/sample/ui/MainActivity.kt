package com.harveyhaha.sample.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import com.google.gson.Gson
import com.harveyhaha.sample.R
import com.harveyhaha.sample.busevent.DrawerEvent
import com.harveyhaha.sample.busevent.RecreateEvent
import com.harveyhaha.sample.databinding.ActivityMainBinding
import com.harveyhaha.sample.db.entity.UserEntity
import com.harveyhaha.sample.ui.login.LoginActivity
import com.harveyhaha.sample.ui.setting.SettingActivity
import com.harveyhaha.sample.viewmodels.MainViewModel
import com.harveyhaha.yggd.base.BaseActivity
import com.harveyhaha.yggd.base.GlideApp
import de.hdodenhof.circleimageview.CircleImageView
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber
import javax.inject.Inject

/**
 *
 * @Description:
 * @Author:         harveyhaha
 * @CreateDate:     20-1-6 下午4:53
 */
open class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    @Inject
    lateinit var gson: Gson
    private var userEntity: UserEntity? = null
    override fun initContentView(savedInstanceState: Bundle?): Int {
        return R.layout.activity_main
    }

    override fun initParam() {
        super.initParam()
        userEntity = intent.getParcelableExtra("user")
        userEntity?.let {
            val args = NewsFragmentArgs(it.name)
            Navigation.findNavController(this, R.id.main_nav_fragment)
                .setGraph(R.navigation.nav_activity_main, args.toBundle())
            viewModel?.loginedUser?.value = userEntity
            Timber.i("LoginUser %s", gson.toJson(userEntity))
        }
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        EventBus.getDefault().register(this)
        initDrawerLayout()
        viewModel?.loginedUser?.observe(this, Observer {
            if (it == null) {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        })
    }

    override fun initData() {
        super.initData()
    }

    private fun initDrawerLayout() {
        initStartDrawerView()
        updateStartDrawerContent(R.menu.activity_main_drawer)
    }

    private fun initStartDrawerView() {
        val actionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerLayout,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        actionBarDrawerToggle.syncState()
        binding.startNav.setNavigationItemSelectedListener { item ->
            Timber.i("NavigationItemSelected %d", item.itemId)
            navigationSelected(item)
            closeDrawer()
            true
        }
        userEntity?.let {
            val avatarCircleImageView =
                binding.startNav.getHeaderView(0).findViewById<CircleImageView>(R.id.avatar)
            val nameTv =
                binding.startNav.getHeaderView(0).findViewById<TextView>(R.id.name_tv)
            val createTime =
                binding.startNav.getHeaderView(0).findViewById<TextView>(R.id.create_time_tv)
            GlideApp.with(this).load(userEntity?.avatar_url).into(avatarCircleImageView)
            nameTv.text = userEntity?.name
            createTime.text = userEntity?.created_at
        }
    }

    private fun openDrawer(isStartDrawer: Boolean) {
        binding.drawerLayout.openDrawer(if (isStartDrawer) GravityCompat.START else GravityCompat.END)
    }

    private fun closeDrawer() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START))
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.END))
            binding.drawerLayout.closeDrawer(GravityCompat.END)
    }

    private fun navigationSelected(item: MenuItem) {
        when (item.itemId) {
            R.id.setting -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
            R.id.setting_logout -> {
                viewModel?.logout()
            }
        }
    }

    private fun updateStartDrawerContent(menuId: Int) {
        updateDrawerContent(binding.startNav, menuId)
    }

    private fun updateDrawerContent(navView: NavigationView, menuId: Int) {
        navView.menu.clear()
        navView.inflateMenu(menuId)
        if (binding.drawerLayout.indexOfChild(navView) == -1)
            binding.drawerLayout.addView(navView)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: DrawerEvent) {
        if (event.isOpen) {
            openDrawer(true)
        } else
            closeDrawer()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: RecreateEvent) {
        recreate()
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
