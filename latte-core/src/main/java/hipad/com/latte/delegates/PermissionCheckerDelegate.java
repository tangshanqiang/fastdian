package hipad.com.latte.delegates;

/**
 * Created by tangshanqiang on 2017/12/4.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.ContentFrameLayout;

/**
 * 中间层 做权限检查
 */
public abstract class PermissionCheckerDelegate extends BaseDelegate{
    //public abstract LatteDelegate setRootDelegate();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
