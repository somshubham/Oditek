package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.som.android.oditek.R;


public class TwoFragment extends Fragment {

    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View x= inflater.inflate(R.layout.fragment_two, container, false);

        String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
                "WebOS","Ubuntu","Windows7","Max OS X"};

        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.activity_listview, mobileArray);

        ListView listView = (ListView)x.findViewById(R.id.list1);
        listView.setAdapter(adapter);


        return x;
    }

}
