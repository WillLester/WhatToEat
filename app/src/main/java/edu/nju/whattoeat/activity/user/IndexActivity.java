package edu.nju.whattoeat.activity.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import edu.nju.whattoeat.R;
import edu.nju.whattoeat.Util.HttpCallbackListener;
import edu.nju.whattoeat.Util.HttpUtil;
import edu.nju.whattoeat.activity.recommend.DishDetailActivity;
import edu.nju.whattoeat.activity.recommend.RecommendActivity;
import edu.nju.whattoeat.activity.user.adpater.DishAdapter;
import edu.nju.whattoeat.vo.Dish;
import okhttp3.Response;

public class IndexActivity extends AppCompatActivity {

    private ListView dishListView;
    private Button userInfo;
    private Button dishRecommend;
    private DishAdapter adapter;
    private List<Dish> dishList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_index);
        initDishes();
        adapter = new DishAdapter(IndexActivity.this, R.layout.dish_item, dishList);
        userInfo = (Button) findViewById(R.id.user_info);
        dishRecommend = (Button) findViewById(R.id.dish_recommend);
        dishListView = (ListView) findViewById(R.id.dish_list_view);
        dishListView.setAdapter(adapter);
        dishListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent,View view, int position,long id){
                Intent intent = new Intent(IndexActivity.this, DishDetailActivity.class);
                startActivity(intent);
            }
        });
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, InfoManageActivity.class);
                startActivity(intent);
            }
        });
        dishRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, RecommendActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDishes(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String address = "http://host:8080/wte/foodinfo?ID=???";
                try{
                    HttpUtil.sendHttpRequestGet(address, new HttpCallbackListener() {
                        @Override
                        public void onFinish(Response response) {
                            try{
                                String responseData = response.body().string();
                                JSONArray jsonArray = new JSONArray(responseData);
                                for(int i = 0;i < jsonArray.length();i++){
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    JSONArray dish =  jsonObject.getJSONArray("result");
                                    for(int j = 0;j < dish.length();j++){
                                        JSONObject item = dish.getJSONObject(i);
                                        String name = item.getString("name");
                                        String time = item.getString("time");
                                        String mainstance = item.getString("maintance");
                                        String substance = item.getString("substance");
                                        String taste = item.getString("time");
                                        String kind = item.getString("kind");
                                        String nutrition = item.getString("nutrition");
                                        String place = item.getString("place");
                                        String opertion = item.getString("operation");
                                        dishList.add(new Dish(name,mainstance,substance,opertion,taste,time,nutrition,kind,place));
                                    }
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onError(Exception e) {

                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

}
