package hipad.com.latte.delegates;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import hipad.com.latte.activities.ProxyActivity;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * Created by tangshanqiang on 2017/12/4.
 */

public abstract class BaseDelegate extends SwipeBackFragment{
    private Unbinder mUnbinder=null;

    public abstract Object setLayout();//传入布局
    public abstract void onBinderView(@Nullable Bundle savedInstanceState,View rootView);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =null;
        if(setLayout() instanceof Integer){
            rootView =inflater.inflate((Integer)setLayout(),container,false);
        }else if(setLayout() instanceof View){
            rootView =(View)setLayout();
        }

        if(rootView != null){
            mUnbinder = ButterKnife.bind(this,rootView);
            onBinderView(savedInstanceState,rootView);
        }

        return rootView;
    }
    public final ProxyActivity getProxyActivity(){
        return (ProxyActivity)_mActivity;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mUnbinder != null){
            mUnbinder.unbind();
        }
    }
}
