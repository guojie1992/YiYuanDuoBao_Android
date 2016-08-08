package so.len.duobao.http;

public class Options {
     VolleyHttp.ImageProcessing imageProcessing;
     int width;
     int height;
     int errImage;
     int defImage;
     boolean cacheInMemory;

    public Options() {
        width = 0;
        height = 0;
        cacheInMemory=true;
    }

    public void cacheInMemory(boolean cacheInMemory) {
        this.cacheInMemory = cacheInMemory;
    }

    /**
     * image processing
     * @param imageProcessing
     */
    public Options imageProcessing(VolleyHttp.ImageProcessing imageProcessing) {
        this.imageProcessing = imageProcessing;
        return this;
    }

    public Options width(int width) {
        this.width = width;
        return this;
    }

    public Options height(int height) {
        this.height = height;
        return this;
    }

    public Options errImage(int resId) {
        this.errImage = resId;
        return this;
    }

    public Options defImage(int resId) {
        this.defImage = resId;
        return this;
    }
}
