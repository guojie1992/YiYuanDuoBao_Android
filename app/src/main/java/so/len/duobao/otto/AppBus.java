package so.len.duobao.otto;

import com.squareup.otto.Bus;

/**
 * Created by Chung on 2016/8/17.
 */
public class AppBus extends Bus {
    private static AppBus appBus = null;
    public synchronized static AppBus getInstance(){
        if(appBus == null){
            appBus = new AppBus();
        }
        return appBus;
    }
}
