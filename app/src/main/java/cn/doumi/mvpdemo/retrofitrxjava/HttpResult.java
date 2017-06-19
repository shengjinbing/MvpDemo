package cn.doumi.mvpdemo.retrofitrxjava;

/**
 * Created by Administrator on 2017/4/6 0006.
 */

public class HttpResult<T> {

    public boolean error;
    //@SerializedName(value = "results", alternate = {"result"})
    public T results;
}
