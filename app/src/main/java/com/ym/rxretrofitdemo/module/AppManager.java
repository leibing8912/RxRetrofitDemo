package com.ym.rxretrofitdemo.module;

import android.app.Activity;
import java.util.Stack;

/**
 * @className: AppManager
 * @classDescription: Activity管理类
 * @author: leibing
 * @createTime: 2016/8/12
 */
public class AppManager {
	// 单例
    private static AppManager instance;
    // 构造函数
    private AppManager(){}
    // Activity管理栈
	private static Stack<Activity> activityStack;
	
	/**
	 * 单例
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param
	 * @return
	 */
	public static AppManager getInstance(){
		if(instance==null){
			instance=new AppManager();
		}
		return instance;
	}

	/**
	 * 添加Activity到堆栈
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param activity 活动实例
	 * @return
	 */
	public void addActivity(Activity activity){
		if(activityStack==null){
			activityStack=new Stack<Activity>();
		}
		activityStack.add(activity);
	}
	
	/**
	 * 获取当前Activity（堆栈中最后一个压入的）
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param
	 * @return
	 */
	public Activity currentActivity(){
		Activity activity = null;
		try{		
			if(activityStack.size()>0){
				activity=activityStack.lastElement();				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return activity;
	}

	/**
	 * 结束当前Activity（堆栈中最后一个压入的）
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param
	 * @return
	 */
	public void finishActivity(){
		try{
			Activity activity=activityStack.lastElement();
			finishActivity(activity);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 结束指定的Activity
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param activity 活动实例
	 * @return
	 */
	public void finishActivity(Activity activity){
		try{
			if(activity!=null){
				activityStack.remove(activity);
				activity.finish();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 移除当前Activity
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param
	 * @return
	 */
	public void removeCurrentActivity() {
		if (activityStack == null || activityStack.isEmpty())
			return;
		activityStack.remove(activityStack.lastElement());
	}

	/**
	 * 结束所有Activity,除了当前Activity
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param activityCls Activity类名
	 * @return
	 */
	public void finishAllActivityExceptOne(Class activityCls) {
		for (int i=0; i<activityCounts(); i++) {
			Activity activity = activityStack.get(i);
			if (!activity.getClass().equals(activityCls)) {
				finishActivity(activity);
			}
		}
	}

	/**
	 * 结束所有Activity,除了当前Activity
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param
	 * @return
	 */
	public void finishAllActivityExceptCurrent() {
		Activity currentActivity = currentActivity();
		finishAllActivityExceptOne(currentActivity.getClass());
	}

	/**
	 * 结束指定类名的Activity
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param cls Activity类名
	 * @return
	 */
	public void finishActivity(Class<?> cls){
		try{
			for (Activity activity : activityStack) {
				if(activity.getClass().equals(cls) ){
					finishActivity(activity);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 结束所有Activity
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param
	 * @return
	 */
	public void finishAllActivity(){
        try {
            for (int i = 0; i < activityStack.size(); i++) {
				if (null != activityStack.get(i)) {
                    activityStack.get(i).finish();
                }
            }
            activityStack.clear();
        }catch (Exception e){e.printStackTrace();}
	} 
	
	/**
	 * 拿到应用程序当前活跃Activity的个数
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param
	 * @return counts
	 */
    public int activityCounts(){
    	int counts = 0 ;
    	if(activityStack !=null && activityStack.size()>0){
    		counts = activityStack.size();
    	}
    	return counts ;
    }

	/**
	 * 判断某一个activity是否为当前activity
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param activity 活动实例
	 * @return
	 */
	public boolean isCurrent(Activity activity){
		if (activity == null || currentActivity() == null)
			return false;
		if (activity == currentActivity())
			return true;
		else
			return false;
	}

	/**
	 * 判断某一个activityCls是否为当前activity
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param activityCls Activity类名
	 * @return
	 */
	public boolean isCurrent(Class activityCls){
		if (currentActivity() == null)
			return false;
		Class currentActivityCls = currentActivity().getClass();
		if (currentActivityCls.equals(activityCls))
			return true;
		else
			return false;
	}

	/**
	 * 是否存在此activity
	 * @author leibing
	 * @createTime 2016/8/12
	 * @lastModify 2016/8/12
	 * @param activityCls 类名
	 * @return
	 */
	public boolean isHasActivity(Class activityCls){
		if (activityStack == null || activityStack.size() == 0)
			return false;
		try {
			for (int i = 0; i < activityStack.size(); i++) {
				if (null != activityStack.get(i)) {
					if (activityCls.equals(activityStack.get(i).getClass()))
						return true;
				}
			}
		}catch (Exception e){e.printStackTrace();}

		return false;
	}
}
