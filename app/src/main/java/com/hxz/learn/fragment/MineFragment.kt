package com.hxz.learn.fragment

import androidx.fragment.app.Fragment
import com.hxz.learn.R
import com.hxz.learn.base.LazyFragment
import com.hxz.learn.config.DataBindingConfig

/**
 * @Author:andy
 * @CreateDate:2020/12/21 13:33
 * @Description:我的
 */
class MineFragment : LazyFragment(){
    override fun lazyInit() {

    }

    override fun getLayoutId() = R.layout.fragment_mine

    override fun getDataBindingConfig(): DataBindingConfig? {
        return null
    }
}