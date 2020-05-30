package com.proggyblast.pblibrary

import com.pblibs.model.GalleryModel
import com.pblibs.uiwidgets.GalleryViewPager

/**
 * Created by Proggy Blast on 30/5/20 4:52 PM
 */

class GalleryActivity : GalleryViewPager() {

    override fun getSelectedItem(): Int {
        return 5;
    }

    override fun getLayoutList(): List<GalleryModel> {
        return emptyList()
    }

    override fun getScreenName(): String {
        val name = GalleryActivity::class.java.simpleName
        return name
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
    }

}