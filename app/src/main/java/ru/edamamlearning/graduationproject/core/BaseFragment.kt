package ru.edamamlearning.graduationproject.core

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment(@LayoutRes layoutRes: Int) :
    Fragment(layoutRes),
    HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    protected fun initToolbar(
        toolbar: Toolbar?,
        titleInToolbar: String = "",
        showBackButton: Boolean = true,
        hasOptionMenu: Boolean = true
    ) {
        setHasOptionsMenu(hasOptionMenu)
        toolbar?.apply {
            val activity = activity as? AppCompatActivity
            activity?.setSupportActionBar(this)
            val actionBar = activity?.supportActionBar

            actionBar?.apply {
                setDisplayShowTitleEnabled(true)
                title = titleInToolbar
                setDisplayHomeAsUpEnabled(showBackButton)
                setHomeButtonEnabled(showBackButton)
                setNavigationOnClickListener { activity.onBackPressed() }
            }
        }
    }
}