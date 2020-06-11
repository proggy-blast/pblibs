package com.proggyblast.pblibrary

import android.os.Bundle
import com.pblibs.ftue.PBSplashActivity
import com.pblibs.utility.PBSessionManager

class MainActivity : PBSplashActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setSplashContent(getText(R.string.app_name).toString(), R.drawable.fallback_logo)
  //      setRedirectActivity("view.auth.SignInActivity", true)
        //setContentView(R.layout.activity_main)
    }

    override fun getSplashInterval(): Long {
        return 2000L
    }

    override fun getContentView(): Int {
        return R.layout.activity_main
    }

    override fun getScreenName(): String {
        return MainActivity::class.java.simpleName
    }
}
