package com.grevu.app.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;
import com.grevu.app.R;
import com.grevu.app.util.DataUtil;
import com.grevu.category.CategoryActivity;

/**
 * Created by jhkim on 2014-10-01.
 */
public class FacebookFragment extends Fragment {

    private static final String TAG = "FacebookFragment";

    private UiLifecycleHelper uiHelper;
    private SessionState sState;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(final Session session, final SessionState state, final Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_login, container, false);

        LoginButton btn_facebook = (LoginButton) view.findViewById(R.id.btn_facebook);
        btn_facebook.setFragment(this);
//        authButton.setReadPermissions(Arrays.asList("user_likes", "user_status"));

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(getActivity(), callback);
        uiHelper.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        // For scenarios where the main activity is launched and user
        // session is not null, the session state change notification
        // may not be triggered. Trigger it if it's open/closed.
        Session session = Session.getActiveSession();
        if (session != null &&
                (session.isOpened() || session.isClosed())) {
            onSessionStateChange(session, session.getState(), null);
        }


        uiHelper.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onPause() {
        super.onPause();
        uiHelper.onPause();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        Log.i(TAG, "onSessionStateChange : " + session.toString());
        sState = state;
        if (state.isOpened()) {

            Intent intent = new Intent(getActivity(), CategoryActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

            DataUtil.setPreferences(getActivity(), "isLogin", "true");

            Log.i(TAG, "Logged in...");
        } else if (state.isClosed()) {
            DataUtil.setPreferences(getActivity(), "isLogin", "false");
            Log.i(TAG, "Logged out...");
        }

        if (exception != null) {
            Log.e(TAG, "exception : " + exception.toString());
        }
    }
}