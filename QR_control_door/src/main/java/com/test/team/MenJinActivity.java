package com.test.team;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.connect.share.QQShare;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.test.team.qrscandoor.R;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

import com.test.team.utils.httputils.HttpCallbackListener;
import com.test.team.utils.httputils.HttpUtil;


public class MenJinActivity extends Activity implements View.OnClickListener {

    public final String BASE_URL = "http://192.168.42.228:8080/AndroidTest/code.txt";
    public static final int SHOW_RESPONSE = 0;
    public static final int SHOW_ERROR_TIP = 1;

    private Button btn_submit;
    private Button btn_transmit;
    private TextView responseText;
    private ImageView mImageView;
    //要分享的Bitmap实例
    private Bitmap mBitmap = null;

    //Tencent实例
    public static Tencent mTencent = null;
    public static final String APP_QQ_ID = "ID1105576726";//QQ客户端的APP_ID
    public static final String APP_WX_ID = "";//微信客户端的APP_ID
    //在Handler中更新UI
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            //处理接收到的消息
            switch (msg.what) {
                case SHOW_RESPONSE:
                    String response = (String) msg.obj;
                    responseText.setText(response);
                    //获取二维码的Bitmap对象   logo暂时未设置
                    //mImageView = (ImageView) findViewById(R.id.get_bitmap);
                    mBitmap = EncodingUtils.createQRCode(response,500,500,null);
                    mImageView.setImageBitmap(mBitmap);
                    break;
                case SHOW_ERROR_TIP:
                    Toast.makeText(MenJinActivity.this, "网络连接异常，请检查网络！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menjin);
        setTitle("申请二维码");
        //获取控件对象
        btn_submit = (Button) findViewById(R.id.submit);//申请
        btn_transmit = (Button) findViewById(R.id.transmit);//转发

   //     responseText = (TextView) findViewById(R.id.response_content);
        //设置监听事件处理
        btn_submit.setOnClickListener(this);
        btn_transmit.setOnClickListener(this);
        mImageView = (ImageView) findViewById(R.id.get_bitmap);
        //微信操作 初始化操作
        //mIwxApi = WXAPIFactory.createWXAPI(this, APP_ID);
        //返回操作
        /*Button titleBack = (Button) findViewById(R.id.title_back);
        titleBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               finish();
            }
        });*/
    }

    //申请和转发的事件处理代码
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.submit) {//申请
            HttpUtil.sendHttpRequest(BASE_URL, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    //根据返回内容执行具体的逻辑
                    Log.i("output>>>>>>", "网络连接成功！" + response.toString());
                    //创建Message对象，将返回数据发送到Handler处理
                    Message message = new Message();
                    message.what = SHOW_RESPONSE;
                    message.obj = response.toString();
                    handler.sendMessage(message);
                }

                @Override
                public void onError(Exception e) {
                    //对异常情况进行处理
                    Message message_error = new Message();
                    message_error.what = SHOW_ERROR_TIP;
                    handler.sendMessage(message_error);
                }
            });
        } else if (v.getId() == R.id.transmit) {//转发 将bitmap发送到QQ或者微信
            String MOBILE_QQ_PKG = "com.tencent.mobileqq";

            //(1)分享到QQ
            if(com.test.team.utils.IsInstalledUtil.IsInstalledApp(MenJinActivity.this,MOBILE_QQ_PKG)){
                Log.i("tencent>>>>>>>","已经安装qq客户端");
               if(mTencent == null){
                   initTencent();//初始化Tencent实例
                   //设置Bundle实例
                   Bundle params = new Bundle();
                   params.putString(QQShare.SHARE_TO_QQ_IMAGE_LOCAL_URL, "/storage/sdcard1/DCIM/Camera/1820851678.jpg");
                   params.putString(QQShare.SHARE_TO_QQ_APP_NAME, null);
                   params.putInt(QQShare.SHARE_TO_QQ_KEY_TYPE, QQShare.SHARE_TO_QQ_TYPE_IMAGE);
                   params.putInt(QQShare.SHARE_TO_QQ_EXT_INT, QQShare.SHARE_TO_QQ_FLAG_QZONE_AUTO_OPEN);

                   doShareToQQ(mTencent,params);
               }
            }else{
                Log.i("tencent>>>>>>>","未安装qq客户端");
            }

            //(2)分享到微信
            //判断是否已经安装微信客户端
            if(com.test.team.utils.IsInstalledUtil.isWXAppInstalledAndSupported(MenJinActivity.this)){
                Log.i("tencent>>>>>>>","已经安装微信客户端");
                //分享二维码到微信朋友

            }else{
                Log.i("tencent>>>>>>>","未安装微信客户端");
            }
        }
    }

    //根据服务器的编码生成二维码
    public Bitmap makeQR(String code) {
        if (!code.equals("")) {
            Bitmap bitmap = EncodingUtils.createQRCode(code, 800, 800, null);
            return bitmap;
        } else {
            Toast.makeText(MenJinActivity.this, "获取二维码信息失败！", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    //初始化Tencent实例
    public void initTencent(){
        mTencent = Tencent.createInstance(APP_QQ_ID,MenJinActivity.this);
    }

    public void doShareToQQ(final Tencent tencent,final Bundle params){
        if(tencent != null) {
            tencent.shareToQQ(MenJinActivity.this, params, qqShareListener);
        }
    }
    IUiListener qqShareListener = new IUiListener() {
        @Override
        public void onCancel() {

        }
        @Override
        public void onComplete(Object response) {

        }
        @Override
        public void onError(UiError e) {

        }
    };
}
