package com.example.winter.uiUtils;
import com.example.winter.ddesignan.DesignFragment;
import com.example.winter.ddesignan.HomeFragment;
import com.example.winter.ddesignan.PicFragment;
import com.example.winter.ddesignan.ShowImgFragment;

/**
 * 统一管理fragment 实例类   因为MainActivity 的Adapter 集成的FragmentStatePagerAdapter  所以此类不能用
 * Created by ZHANGHENGMING on 2016/9/28.
 */
public class MyFragmentManager {
    public HomeFragment getHomeFragment(android.support.v4.app.FragmentManager fM) {
        HomeFragment homeFragment = (HomeFragment) fM.findFragmentByTag("HOME_FRAGMENT_TAG");
        return homeFragment;
    }

    public DesignFragment getDesignFragment(android.support.v4.app.FragmentManager fM) {
        DesignFragment designFragment = (DesignFragment) fM.findFragmentByTag("DESIGN_FRAGMENT_TAG");
        return designFragment;
    }

    public ShowImgFragment getImgFragment(android.support.v4.app.FragmentManager fM) {
        ShowImgFragment sImgFragment = (ShowImgFragment) fM.findFragmentByTag("SHOWIMG_FRAGMENT_TAG");
        return sImgFragment;
    }

    public PicFragment getPicFragment(android.support.v4.app.FragmentManager fM) {
        PicFragment picFragment = (PicFragment) fM.findFragmentByTag("PIC_FRAGMENT_TAG");
        return picFragment;
    }

}
