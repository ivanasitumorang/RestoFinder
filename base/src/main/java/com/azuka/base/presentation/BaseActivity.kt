package com.azuka.base.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.azuka.base.di.component.ShareComponent
import com.azuka.base.presentation.widget.LoadingDialog
import java.lang.ref.WeakReference


/**
 * Created by ivanaazuka on 28/09/20.
 * Android Engineer
 */

abstract class BaseActivity : AppCompatActivity(), AbstractComponent {

    val loadingDialog: WeakReference<LoadingDialog> = WeakReference(LoadingDialog.newInstance())

    abstract fun getLayoutId(): Int

    abstract fun initDependencyInjection()

    abstract fun onActivityReady(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            ShareComponent.current = createComponent()
            initDependencyInjection()
        } catch (e: Exception) {
            throw IllegalStateException("Please provide valid component")
        }
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        onActivityReady(savedInstanceState)
    }
}