package cn.doumi.mvpdemo.retrofitrxjava;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import okhttp3.Cookie;

/**
 * Created by Administrator on 2017/4/5 0005.
 */

public class OkHttpCookies {
    //当对象被序列化时（写入字节序列到目标文件）时，
    // transient阻止实例中那些用此关键字声明的变量持久化；
    // 当对象被反序列化时（从源文件读取字节序列进行重构），
    // 这样的实例变量值不会被持久化和恢复。
    // 例如，当反序列化对象——数据流（例如，文件）可能不存在时，
    // 原因是你的对象中存在类型为java.io.InputStream的变量，
    // 序列化时这些变量引用的输入流无法被打开。
    private transient final Cookie cookies;
    private transient Cookie clientCookies;

    public OkHttpCookies(Cookie cookies) {
        this.cookies = cookies;
    }

    public Cookie getCookies() {
        Cookie bestCookies = cookies;
        if (clientCookies != null) {
            bestCookies = clientCookies;
        }
        return bestCookies;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(cookies.name());
        out.writeObject(cookies.value());
        out.writeLong(cookies.expiresAt());
        out.writeObject(cookies.domain());
        out.writeObject(cookies.path());
        out.writeBoolean(cookies.secure());
        out.writeBoolean(cookies.httpOnly());
        out.writeBoolean(cookies.hostOnly());
        out.writeBoolean(cookies.persistent());
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        String name = (String) in.readObject();
        String value = (String) in.readObject();
        long expiresAt = in.readLong();
        String domain = (String) in.readObject();
        String path = (String) in.readObject();
        boolean secure = in.readBoolean();
        boolean httpOnly = in.readBoolean();
        boolean hostOnly = in.readBoolean();
        boolean persistent = in.readBoolean();
        Cookie.Builder builder = new Cookie.Builder();
        builder = builder.name(name);
        builder = builder.value(value);
        builder = builder.expiresAt(expiresAt);
        builder = hostOnly ? builder.hostOnlyDomain(domain) : builder.domain(domain);
        builder = builder.path(path);
        builder = secure ? builder.secure() : builder;
        builder = httpOnly ? builder.httpOnly() : builder;
        clientCookies = builder.build();
    }
}
