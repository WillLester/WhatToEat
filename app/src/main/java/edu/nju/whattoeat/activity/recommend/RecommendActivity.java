package edu.nju.whattoeat.activity.recommend;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.nju.whattoeat.R;
import edu.nju.whattoeat.Util.HttpCallbackListener;
import edu.nju.whattoeat.Util.HttpUtil;
import edu.nju.whattoeat.activity.recommend.adapter.MessageAdapter;
import edu.nju.whattoeat.activity.recommend.utility.AudioRecordUtils;
import edu.nju.whattoeat.vo.Message;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static edu.nju.whattoeat.Util.HttpUtil.JSON;

public class RecommendActivity extends AppCompatActivity {

    private ListView messageListView;
    private EditText editText;
    private Button nextTransaction;
    private Button tape;
    private Button send;
    private MessageAdapter adapter;
    private List<Message> messageList = new ArrayList<>();

    private AudioRecordUtils audioRecordUtils;

    private String transaction_no = " ";
    private int voinceNo = 0;
    public RecommendActivity(){
        this.transaction_start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_recommend);
        adapter = new MessageAdapter(RecommendActivity.this, R.layout.message_item, messageList);
        editText = (EditText) findViewById(R.id.input_text);
        tape = (Button) findViewById(R.id.send_voice);
        send = (Button) findViewById(R.id.send_msg);
        messageListView = (ListView) findViewById(R.id.msg_list_view);
        messageListView.setAdapter(adapter);
        audioRecordUtils = new AudioRecordUtils(getApplicationContext().getFilesDir().getAbsolutePath()+"/files/records/");
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = editText.getText().toString();
                if (!"".equals(content)){
                    Message message = new Message(content, true);
                    messageList.add(message);
                    adapter.notifyDataSetChanged();
                    messageListView.setSelection(messageList.size());
                    editText.setText("");
                    transmit_message(content);
                } else {
                    Snackbar.make(messageListView, "请输入信息哟", Snackbar.LENGTH_SHORT);
                }
            }
        });
        tape.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        tape.setText("松开发送");
                        audioRecordUtils.startRecord();
                        break;
                    case MotionEvent.ACTION_UP:
                        tape.setText("按住录音");
                        audioRecordUtils.stopRecord();
                        break;
                }
                tranmit_audio();
                return true;
            }
        });
        nextTransaction.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                transaction_start();
            }
        });
    }

    private void transaction_start( ){

        String address = "http://host:8080/wte/transaction-start";
        OkHttpClient okHttpClient = new OkHttpClient();
        //构建一个请求对象
        Request request = new Request.Builder().url(address).build();
        //发送请求
        try {
            Response response = okHttpClient.newCall(request).execute();
            String responseData = response.body().string();
            JSONArray jsonArray = new JSONArray(responseData);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            transaction_no = jsonObject.getString("transaction");
            voinceNo = 0;
        } catch (Exception e) {

        }
    }

    private boolean transmit_message(String mesg){
        String address = " http://host:8080/wte/voice-upload?Transaction="+ transaction_no + "&VoiceNo="+ voinceNo;
        //申明给服务端传递一个json串
        //创建一个OKHttpClient对象

        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            //创建一个RequestBody（参数1：数据类型，参数2：传递的json串）
            JSONObject message = new JSONObject();
            message.put("message",mesg);


            RequestBody requestBody = RequestBody.create(JSON,String.valueOf(message).getBytes());
            //创建一个请求对象
            Request request = new Request.Builder()
                    .url(address)
                    .post(requestBody)
                    .build();
            //发送请求获取响应
            Response response = okHttpClient.newCall(request).execute();
            String responseData = response.body().string();
            JSONArray jsonArray = new JSONArray(responseData);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            if(jsonObject.getString("status").equals("success")){
                return true;
            }else if(jsonObject.getString("status").equals("failure")){
                return false;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private void tranmit_audio(){

    }

    private void get_reply(){

    }


}
