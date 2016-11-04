package com.dyc.test.tools;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.inputmethod.InputMethodManager;

import com.dyc.test.R;
import com.dyc.test.base.BaseFragment;

public class FragmentManager {
	public static final String TAG = "FragmentManager";

	public static final int MAINACTIVITY_RECOMMAD_FRAGMENT = 1;
	public static final int MAINACTIVITY_FIND_FRAGMENT = 2;
	public static final int MAINACTIVITY_MINE_FRAGMENT = 3;
	public static final int MAINACTIVITY_ASK_FRAGMENT = 4;

	/**
	 *  create framgnet by Object of Fragment
	 * @param fragmentActivity
	 * @param fragment
     */
	public static void replaceFragment(FragmentActivity fragmentActivity,
								   BaseFragment fragment) {
		if (null != fragmentActivity && null != fragment) {
			fragmentActivity
					.getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.fragmentlayout, fragment,
							fragment.getClass().getName()).commit();
		}
	}

	/**
	 * create fragment by name of fragement
	 * @param fragmentActivity
	 * @param fragmentName
     */
	public static void setFragmentWithStrName(FragmentActivity fragmentActivity,
											  String fragmentName) {
		fragmentActivity.getSupportFragmentManager().beginTransaction()
				.replace(R.id.fragmentlayout, BaseFragment.getInstance(fragmentActivity, fragmentName),fragmentName).commit();
	}
	/**
	 * add fragment to backstack
	 * @param fragmentActivity
	 * @param fragment
     */
	public static void addStackFragment(FragmentActivity fragmentActivity,
										BaseFragment fragment) {

		fragmentActivity
				.getSupportFragmentManager()
				.beginTransaction()
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
						// .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
						// .setCustomAnimations(
						// R.anim.anim_enter,
						// R.anim.anim_exit,
						// R.anim.back_enter,
						// R.anim.back_exit)
				.replace(R.id.fragmentlayout, fragment)
				.addToBackStack(fragment.getClass().getName())
				.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
				.commitAllowingStateLoss();
	}

	/**
	 * pop a fragment from stack
	 * @param fragmentActivity
     */
	public static void popFragment(FragmentActivity fragmentActivity) {

		fragmentActivity.getSupportFragmentManager().popBackStack();
		InputMethodManager m = (InputMethodManager) fragmentActivity
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		m.hideSoftInputFromWindow(fragmentActivity.getWindow().getDecorView()
				.getWindowToken(), 0);
	}

	public static void transDataFragment(FragmentActivity fragmentActivity,
										 String fragment, Bundle bundle) {

		fragmentActivity.getSupportFragmentManager()
				.findFragmentByTag(fragment).setArguments(bundle);
		fragmentActivity.getSupportFragmentManager().popBackStack();

	}

	/**
	 * clear the fragment stack
	 * @param fragmentActivity
     */
	public static void clearStack(FragmentActivity fragmentActivity) {
		InputMethodManager m = (InputMethodManager) fragmentActivity
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		m.hideSoftInputFromWindow(fragmentActivity.getWindow().getDecorView()
				.getWindowToken(), 0);
		int count = fragmentActivity.getSupportFragmentManager()
				.getBackStackEntryCount();
		if(count>0) {
			fragmentActivity
					.getSupportFragmentManager()
					.popBackStack(
							fragmentActivity.getSupportFragmentManager()
									.getBackStackEntryAt(0).getId(),
							android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
	}
/************************************************************************************************************************************** */

	/**
	 *
	 * Fragment 里面的跳转
	 * @param fragment
	 * @param newFragment
     */
	public static void setFragmentWithStrName(Fragment basefragment,Fragment newFragment) {
		String fragmentName =  newFragment.getClass().getName();
		basefragment.getChildFragmentManager().beginTransaction()
				.replace(R.id.fragmentlayout, newFragment,fragmentName).addToBackStack(null).commit();
	}

	public static void clearFragementStack(Fragment fragment) {

		int count =  fragment.getChildFragmentManager()
				.getBackStackEntryCount();
		if(count>0) {
			fragment.getChildFragmentManager()
					.popBackStack(
							fragment.getChildFragmentManager()
									.getBackStackEntryAt(0).getId(),
							android.support.v4.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
		}
	}


	public static int getFragmentBackCount(FragmentActivity fragmentActivity) {
       try{
		    return fragmentActivity.getSupportFragmentManager().findFragmentById(R.id.fragmentlayout).getChildFragmentManager().getBackStackEntryCount();
	   }catch (Exception e) {

	   }
		   return 0;

	}

}
