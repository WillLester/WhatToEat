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

import java.util.ArrayList;
import java.util.List;

import edu.nju.whattoeat.R;
import edu.nju.whattoeat.activity.recommend.adapter.MessageAdapter;
import edu.nju.whattoeat.vo.Message;

public class RecommendActivity extends AppCompatActivity {

    private ListView messageListView;
    private EditText editText;
    private Button tape;
    private Button send;
    private MessageAdapter adapter;
    private List<Message> messageList = new ArrayList<>();

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
                        break;
                    case MotionEvent.ACTION_UP:
                        tape.setText("按住录音");
                        break;
                }
                return true;
            }
        });
    }
}
