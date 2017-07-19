package cn.doumi.mvpdemo.fragment;


import android.opengl.GLSurfaceView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.Toast;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import butterknife.BindView;
import cn.doumi.mvpdemo.R;
import cn.doumi.mvpdemo.base.BaseTitleFragment;
import cn.doumi.mvpdemo.intface.OnTabReselectListener;

import static android.opengl.GLES10.glMatrixMode;
import static android.opengl.GLSurfaceView.RENDERMODE_WHEN_DIRTY;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends BaseTitleFragment implements OnTabReselectListener {


    @BindView(R.id.GLSurfaceView)
    GLSurfaceView mGLSurfaceView;

    public ExploreFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_explore;
    }

    @Override
    protected int getTitleRes() {
        return R.string.main_tab_name_explore;
    }

    @Override
    public void onTabReselect() {
        Toast.makeText(mContext, "点击了发现", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        mGLSurfaceView.setRenderer(new GLSurfaceView.Renderer() {
            @Override
            public void onSurfaceCreated(GL10 gl, EGLConfig config) {

            }

            @Override
            public void onSurfaceChanged(GL10 gl, int width, int height) {
                  //它告诉了我们这张纸有多高多宽。
                gl.glViewport(0,0,width,height);
                Log.d("BBBBB",width+","+height+"qqqqqqqqqqqqqqqqq");
            }

            @Override
            public void onDrawFrame(GL10 gl) {

                ByteBuffer mBuffer = ByteBuffer.allocateDirect(1000*1000*4);
                mBuffer.order(ByteOrder.nativeOrder());
                FloatBuffer VertexBuffer = mBuffer.asFloatBuffer();
                IntBuffer colorBuffer = mBuffer.asIntBuffer();

                //画之前清理纸
                gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
                glMatrixMode(GL10.GL_MODELVIEW); //是说我们现在改变的是坐标系与Surface的映射关系（投影矩阵）。
                gl.glLoadIdentity(); //是将以前的改变都清掉
                gl.glFrustumf(-400, 400, -240, 240, 0.3f, 100);


                //启动数组
                gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
                gl.glEnableClientState(GL10.GL_COLOR_ARRAY);


                //我们前面已经构造好了我们的数据缓冲区，floatBuffer（或 IntBuffer）。
                // 现在我们只需要将这个数据缓冲区和对应的功能绑定起来就好了：
                gl.glVertexPointer(3, GL10.GL_FLOAT, 0, VertexBuffer);
                gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);


                //第一个参数指明了画图的类型——三角形（android 似乎只支持画三角形、点、线，不支持画多边形）。
                // 后面两个参数指明，从哪个顶点开始画，画多少个顶点。
                gl.glDrawArrays(GL10.GL_TRIANGLES,0,3);
            }
        });

        //第一种模式（RENDERMODE_CONTINUOUSLY）：
        //连续不断的刷，画完一幅图马上又画下一幅。这种模式很明显是用来画动画的；


        //第二种模式（RENDERMODE_WHEN_DIRTY）：
        //只有在需要重画的时候才画下一幅。这种模式就比较节约CPU和GPU一些，适合用来画不经常需要刷新的情况。多说一句，系统如何知道需要重画了呢？当然是你要告诉它……
        //调用GLSurfaceView的requestRender ()方法，告诉它，你脏了。

        mGLSurfaceView.setRenderMode(RENDERMODE_WHEN_DIRTY);


    }
}
