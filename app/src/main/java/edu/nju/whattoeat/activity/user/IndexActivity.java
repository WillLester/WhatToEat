package edu.nju.whattoeat.activity.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import edu.nju.whattoeat.R;
import edu.nju.whattoeat.activity.recommend.RecommendActivity;
import edu.nju.whattoeat.activity.user.adpater.DishAdapter;
import edu.nju.whattoeat.vo.Dish;

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
        adapter = new DishAdapter(IndexActivity.this, R.layout.dish_item, dishList);
        userInfo = (Button) findViewById(R.id.user_info);
        dishRecommend = (Button) findViewById(R.id.dish_recommend);
        dishListView = (ListView) findViewById(R.id.dish_list_view);
        dishListView.setAdapter(adapter);
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IndexActivity.this, PersonalInfoActivity.class);
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
}
