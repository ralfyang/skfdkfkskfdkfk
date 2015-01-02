package com.grevu.category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.model.GraphUser;
import com.grevu.app.R;
import com.grevu.app.view.LoginActivity;
import com.grevu.item.ItemListActivity;
import com.grevu.map.GrevuMapActivity;

/**
 * Created by jhkim on 2014. 10. 9..
 */
public class CategoryActivity extends Activity {
    final static String TAG = "CategoryActivity";
    ImageView btn_food, btn_stay, btn_item;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_category);

        btn_food = (ImageView) findViewById(R.id.btn_food);
        btn_item = (ImageView) findViewById(R.id.btn_item);
        btn_stay = (ImageView) findViewById(R.id.btn_stay);

        btn_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, GrevuMapActivity.class);
                intent.putExtra("cate", "01");
                startActivity(intent);
            }
        });

        btn_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CategoryActivity.this, GrevuMapActivity.class);
                intent.putExtra("cate", "02");
                startActivity(intent);
            }
        });

        btn_stay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //temparary fb logout
//                Session.openActiveSession(CategoryActivity.this, true, new Session.StatusCallback() {
//                    @Override
//                    public void call(final Session session, final SessionState state, Exception exception) {
//                        Log.i(TAG, session.toString());
//                        if (session.isOpened()) {
//                            Request.executeMeRequestAsync(session, new Request.GraphUserCallback() {
//                                @Override
//                                public void onCompleted(GraphUser user, Response response) {
//                                    if (user != null) {
//                                        try {
//                                            session.close();
//                                            session.closeAndClearTokenInformation();
//
//                                            Intent intent = new Intent(CategoryActivity.this, LoginActivity.class);
//                                            finish();
//                                            startActivity(intent);
//                                        } catch (Exception e) {
//                                            e.printStackTrace();
//                                        }
//                                    }
//                                }
//                            });
//                        }
//                    }
//                });

                Intent intent = new Intent(CategoryActivity.this, GrevuMapActivity.class);
                intent.putExtra("cate", "03");
                startActivity(intent);
            }
        });

    }
}
