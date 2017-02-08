package es.cice.androidstackexchange;


import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import es.cice.androidstackexchange.database.QuestionsOpenHelper;
import es.cice.androidstackexchange.events.NewDataEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionListFragment extends ListFragment {


    private static final String TAG = QuestionListFragment.class.getCanonicalName();

    public QuestionListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getDataNotificationEvent(NewDataEvent event){
        Log.d(TAG,"getDataNotificationEvent()...");
        Cursor cursor=event.getCursor();
        ((CursorAdapter)getListView().getAdapter()).swapCursor(cursor);
        ((CursorAdapter) getListView().getAdapter()).notifyDataSetChanged();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(getActivity(),R.layout.question_row,null,
                new String[]{QuestionsOpenHelper.OWNER_AVATAR_COLUM,
                        QuestionsOpenHelper.QUESTION_TITLE_COLUMN},
                new int[]{R.id.avatarIV,R.id.questionTV},0);
        adapter.setViewBinder(new QuestionViewBinder());
        setListAdapter(adapter);

        return super.onCreateView(inflater,container,savedInstanceState);
    }

    private class QuestionViewBinder implements SimpleCursorAdapter.ViewBinder {
        @Override
        public boolean setViewValue(View view, Cursor cursor, int i) {
            switch(view.getId()){
                case R.id.avatarIV:
                    Picasso
                            .with(getActivity())
                            .load(Uri.parse(cursor.getString(cursor.getColumnIndex(
                                    QuestionsOpenHelper.OWNER_AVATAR_COLUM
                            ))))
                            .resize(54,54)
                            .centerCrop()
                            .into((ImageView) view);
                    return true;

                case R.id.questionTV:
                    ((TextView)view).setText(
                            cursor.getString(cursor.getColumnIndex(QuestionsOpenHelper.QUESTION_TITLE_COLUMN)));
                    return true;
            }
            return false;
        }
    }
}
