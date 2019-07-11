package org.techtown.mission22;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.AlteredCharSequence;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 *
 */
public class Fragment2 extends Fragment {
    RecyclerView recyclerView;
    BookAdapter adapter;

    OnDatabaseCallback callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        callback = (OnDatabaseCallback) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment2, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BookAdapter();
        recyclerView.setAdapter(adapter);

        ArrayList<BookInfo> result = callback.selectAll();
        adapter.setItems(result);

        adapter.setOnItemClickListener(new OnBookItemClickListener() {
            @Override
            public void onItemClick(BookAdapter.ViewHolder holder, View view, int position) {
                final BookInfo item = adapter.getItem(position);
                final String name = item.getName();
                final String author = item.getAuthor();
                final String url = item.getUrl();

                String st[] = {"상세정보", "삭제하기", "취소하기"};
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("선택");
                builder.setItems(st, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        if(i == 0) {
                            if(url.length() > 0) {
                                Intent intent = new Intent(getActivity(), WebSite.class);
                                intent.putExtra("url", url);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getContext(),"등록된 URL이 없습니다.",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else if(i == 1) {
                            callback.delete(name, author);
                            adapter.items.remove(item);
                            adapter.notifyDataSetChanged();
                        } else
                        {
                            Toast.makeText(getContext(), "취소되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button button = rootView.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BookInfo> result = callback.selectAll();
                adapter.setItems(result);
                adapter.notifyDataSetChanged();
            }
        });

        return rootView;
    }
}
