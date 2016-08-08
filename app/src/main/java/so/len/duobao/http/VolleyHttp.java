package so.len.duobao.http;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

public class VolleyHttp {
    private static final String TAG = "VolleyHttp";
    private static final int MAX_SIZE = 1024 * 1024 * 40;
    private static final int MAX_CACHE_SIZE = 1024 * 1024 * 10;
    private static VolleyHttp mVolley;
    private RequestQueue queue;
    private BitmapCache mCache;

    private VolleyHttp() {

    }

    public static VolleyHttp getInstance() {
        if (mVolley == null)
            synchronized (VolleyHttp.class) {
                if (mVolley == null)
                    mVolley = new VolleyHttp();
            }
        return mVolley;
    }

    public void init(Context context) {
        this.init(context, MAX_SIZE, MAX_CACHE_SIZE);
    }

    public void init(Context context, int maxSize, int maxCacheSize) {
        queue = Volley.newRequestQueue(context, maxSize);
        mCache = new BitmapCache(maxCacheSize);
    }

    private boolean isInit() {
        if (queue == null || mCache == null) {
            throw new RuntimeException("Not init,please init first!");
        }
        return true;
    }

    /**
     * get volley request queue
     *
     * @return null if not init
     */
    public RequestQueue getRequestQueue() {
        return queue;
    }

    /**
     * JsonResponseListener
     */
    public interface JsonResponseListener {
        void getJson(String json, boolean isConnectSuccess);
    }

    /**
     * Method.GET without params
     *
     * @param url      url
     * @param listener JsonResponseListener
     */
    public void getJson(String url, JsonResponseListener listener) {
        json(url, listener, Request.Method.GET, null);
    }

    /**
     * Method.POST without params
     *
     * @param url      url
     * @param listener JsonResponseListener
     */
    public void postJson(String url, JsonResponseListener listener) {
        json(url, listener, Request.Method.POST, null);
    }

    /**
     * Method.POST with params
     *
     * @param url      url
     * @param listener JsonResponseListener
     * @param params   params
     */
    public void postParamsJson(String url, JsonResponseListener listener, Map<String, String> params) {
        json(url, listener, Request.Method.POST, params);
    }

    private void json(final String url, final JsonResponseListener listener, int method, final Map<String, String> params) {
        if (!isInit())
            return;
        if (listener == null) {
            Log.e(TAG, "JsonResponseListener is null!");
            return;
        }
        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                listener.getJson(s, true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Cache cache = queue.getCache();
                Cache.Entry entry = null;
                if (cache != null && (entry = cache.get(url)) != null) {
                    listener.getJson(new String(entry.data), false);
                }else{
                    listener.getJson(null, false);
                }
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        request.setTag(url);
        queue.add(request);
    }

    public void cancalRequest(String url) {
        queue.cancelAll(url);
    }

    public interface ImageProcessing {
        Bitmap getBitmap(Bitmap bitmap);
    }

    public void imageLoader(String url, final ImageView view, Options opt) {
        isInit();
        final Options options;
        if(opt==null) {
            options = new Options();
        }else{
            options=opt;
        }
        ImageLoader imageLoader = new ImageLoader(queue, mCache);
        ImageLoader.ImageListener listener = new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if (options.errImage != 0) {
                    view.setImageResource(options.errImage);
                }
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                if (imageContainer.getBitmap() != null) {
                    if (options.imageProcessing != null) {
                        view.setImageBitmap(options.imageProcessing.getBitmap(imageContainer.getBitmap()));
                    } else {
                        view.setImageBitmap(imageContainer.getBitmap());
                    }
                } else if (options.defImage != 0) {
                    view.setImageResource(options.defImage);
                }
            }
        };
        if (options.width == 0 || options.height == 0)
            imageLoader.get(url, listener);
        else
            imageLoader.get(url, listener, options.width, options.height);
    }



    private class BitmapCache implements ImageLoader.ImageCache {
        private LruCache<String, Bitmap> cache;

        public BitmapCache(int maxSize) {
            cache = new LruCache<String, Bitmap>(maxSize) {
                @Override
                protected int sizeOf(String key, Bitmap value) {
                    return value.getHeight() * value.getWidth();
                }
            };
        }

        @Override
        public Bitmap getBitmap(String s) {
            if (cache == null || s == null || s.equals(""))
                return null;
            Bitmap bitmap = cache.get(s);
            if (bitmap==null || bitmap.isRecycled())
                bitmap = null;
            return bitmap;
        }

        @Override
        public void putBitmap(String s, Bitmap bitmap) {
            if (cache == null || s == null || s.equals("") )
                return;
            cache.put(s, bitmap);
        }
    }
}
