package cn.leeii.simple.pager;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import cn.leeii.lib.model.Screen;
import cn.leeii.lib.utils.UIUtil;
import cn.leeii.simple.pager.fragments.ExpandingFragment;


/**
 * Created by Qs on 16/6/20.
 */
public class ExpandingPagerFactory {
    public static ExpandingFragment getCurrentFragment(ViewPager viewPager) {
        if (viewPager.getAdapter() instanceof ExpandingViewPagerAdapter) {
            ExpandingViewPagerAdapter adapter = (ExpandingViewPagerAdapter) viewPager.getAdapter();
            Fragment fragment = adapter.getCurrentFragment();
            if (fragment instanceof ExpandingFragment) {
                return (ExpandingFragment) fragment;
            }
        }
        return null;
    }

    public static void setupViewPager(final ViewPager viewPager) {
        Screen screen = UIUtil.screenPx(viewPager.getContext());
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        layoutParams.width = screen.width / 7 * 5;
        layoutParams.height = (int) ((layoutParams.width / 0.75));

        viewPager.setOffscreenPageLimit(2);

        if (viewPager.getParent() instanceof ViewGroup) {
            ViewGroup viewParent = ((ViewGroup) viewPager.getParent());
            viewParent.setClipChildren(false);
            viewPager.setClipChildren(false);
        }

        viewPager.setPageTransformer(true, new ExpandingViewPagerTransformer());
    }

    public static boolean onBackPressed(ViewPager viewPager) {
        ExpandingFragment expandingFragment = getCurrentFragment(viewPager);
        if (expandingFragment != null && expandingFragment.isOpenend()) {
            expandingFragment.close();
            return true;
        }
        return false;
    }
}
