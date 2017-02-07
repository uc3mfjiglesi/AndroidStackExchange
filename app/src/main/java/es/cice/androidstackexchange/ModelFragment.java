package es.cice.androidstackexchange;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import es.cice.androidstackexchange.events.NewDataEvent;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModelFragment extends Fragment {


    public ModelFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new ModelLoadThread().start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        textView.setText(R.string.hello_blank_fragment);
        return textView;
    }

    public class ModelLoadThread extends Thread{
        public void run(){
            for(int i=0;i<10;i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                EventBus.getDefault().postSticky(new NewDataEvent());
            }

        }
    }

}
