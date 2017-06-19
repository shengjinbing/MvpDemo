package cn.doumi.mvpdemo.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String 处理工具类
 */
public class AssimilateUtils {

    // @thanatosx
    // http://my.oschina.net/u/user_id
    // http://my.oschina.net/user_ident
    public static final Pattern PatternAtUserWithHtml = Pattern.compile(
            "<a\\s+href=['\"]http[s]?://my\\.oschina\\.[a-z]+/([0-9a-zA-Z_]+" +
                    "|u/([0-9]+))['\"][^<>]*>(@([^@<>\\s]+))</a>"
    );
    public static final Pattern PatternAtUser = Pattern.compile(
            "@[^@\\s:]+"
    );

    // #Java#
    public static final Pattern PatternSoftwareTagWithHtml = Pattern.compile(
            "<a\\s+href=['\"]([^'\"]*)['\"][^<>]*>(#[^#@<>\\s]+#)</a>"
    );
    public static final Pattern PatternSoftwareTag = Pattern.compile(
            "#([^#@<>\\s]+)#"
    );

    // @user links
    @Deprecated
    public static final Pattern PatternAtUserAndLinks = Pattern.compile(
            "<a\\s+href=['\"]http://my\\.oschina\\.net/([0-9a-zA-Z_]+)['\"][^<>]*>(@[^@<>\\s]+)</a>" +
                    "|<a href=['\"]([^'\"]*)['\"][^<>]*>([^<>]*)</a>"
    );

    private static final Pattern PatternGit = Pattern.compile(
            "<a\\s+href=\'http[s]?://git\\.oschina\\.net/[^>]*\'[^>]*data-project=\'([0-9]*)\'[^>]*>([^<>]*)</a>"
    );

    // links
    public static final Pattern PatternLinks = Pattern.compile(
            "<a\\s+href=['\"]([^'\"]*)['\"][^<>]*>([^<>]*)</a>"
    );

    // team task
    public static final Pattern PatternTeamTask = Pattern.compile(
            "<a\\s+style=['\"][^'\"]*['\"]\\s+href=['\"]([^'\"]*)['\"][^<>]*>([^<>]*)</a>"
    );

    // html task
    public static final Pattern PatternHtml = Pattern.compile(
            "<[^<>]+>([^<>]+)</[^<>]+>"
    );

    private interface Action1 {
        void call(String str);
    }

    /**
     * 通常使用的过滤逻辑
     *
     * @param context {@link Context}
     * @param content Content String
     * @return String

    /**
     * 高亮@User
     *
     * @param context Context
     * @param content string
     * @return
     */
    public static Spannable highlightAtUser(Context context, CharSequence content) {
        return highlightAtUser(context, new SpannableString(content));
    }

    /**
     * @param context   Context
     * @param spannable string
     * @return
     * @see #highlightAtUser(Context, Spannable)
     */
    public static Spannable highlightAtUser(Context context, Spannable spannable) {
        String str = spannable.toString();
        Matcher matcher = PatternAtUser.matcher(str);
        while (matcher.find()) {
            ForegroundColorSpan span = new ForegroundColorSpan(0XFF6888AD);
            spannable.setSpan(span, matcher.start(), matcher.end(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        }
        return spannable;
    }

    /**
     * 与 {@link #highlightAtUser(Context, Spannable)} 不同的是这个方法是针对<a>标签包裹的@User
     *
     * @param context Context
     * @param content string
     * @return
     */
}


