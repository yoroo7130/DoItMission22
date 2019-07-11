package org.techtown.mission22;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 *
 */
public class Fragment1 extends Fragment {

    EditText editText;
    EditText editText2;
    EditText editText3;
    EditText editText4;

    OnDatabaseCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (OnDatabaseCallback) getActivity();
    }

    public void setText() {
        String name = "";
        String author = "";
        String contents = "";
        String url = "";

        editText.setText(name);
        editText2.setText(author);
        editText3.setText(contents);
        editText4.setText(url);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);

        editText = (EditText) rootView.findViewById(R.id.editText);
        editText2 = (EditText) rootView.findViewById(R.id.editText2);
        editText3 = (EditText) rootView.findViewById(R.id.editText3);
        editText4 = (EditText) rootView.findViewById(R.id.editText4);

        Button button = (Button) rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editText.getText().toString();
                String author = editText2.getText().toString();
                String contents = editText3.getText().toString();
                String url = editText4.getText().toString();

                callback.insert(name, author, contents, url);
                Toast.makeText(getContext(), "책 정보를 추가했습니다.", Toast.LENGTH_LONG).show();
                setText();
            }
        });

        return rootView;
    }
}
