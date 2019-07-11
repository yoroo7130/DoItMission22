package org.techtown.mission22;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

public class BookView extends Fragment {
    FrameLayout viewContainer;
    Animation translateIn;
    Animation translateOut;

    BookItemView view;
    BookItemView view2;
    BookItemView view3;
    BookItemView view4;
    BookItemView view5;

    int selected = -1;

    boolean running = false;

    Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.book_view, container, false);

        AnimationThread thread = new AnimationThread();
        thread.start();

        viewContainer = rootView.findViewById(R.id.container);

        view = new BookItemView(getActivity());
        view.setImage(R.drawable.book1);
        view.setName("죽고 싶지만 떡볶이는 먹고싶어");
        view.setAuthor("백세희");
        view.setContents("우울과 불안 속에서 하루하루를 살아낸다는 것");
        view.setVisibility(View.VISIBLE);
        viewContainer.addView(view);

        view2 = new BookItemView(getActivity());
        view2.setImage(R.drawable.book2);
        view2.setName("한자와 나오키");
        view2.setAuthor("이케이도 준");
        view2.setContents("전 일본을 강타한 드라마 [한자와 나오키]의 원작 소설이자 누적 집계 570만 부가 판매된 소설 한자와 나오키 시리즈");
        view2.setVisibility(View.INVISIBLE);
        viewContainer.addView(view2);

        view3 = new BookItemView(getActivity());
        view3.setImage(R.drawable.book3);
        view3.setName("시즈카 할머니에게 맡겨 줘");
        view3.setAuthor("나카야마 시치리");
        view3.setContents("2018년 오카다 유이 주연 일본 아사히 TV 특집 드라마 방영작으로, 반전의 제왕, " +
                "<안녕, 드뷔시> 작가 나카야마 시치리의 단편 연작 미스터리 소설이다. ");
        view3.setVisibility(View.INVISIBLE);
        viewContainer.addView(view3);

        view4 = new BookItemView(getActivity());
        view4.setImage(R.drawable.book4);
        view4.setName("우울하면 좀 어때");
        view4.setAuthor("김승기");
        view4.setContents("혼자 있을 때 외로워지는 것은 인간의 보편적 감정이다.");
        view4.setVisibility(View.INVISIBLE);
        viewContainer.addView(view4);

        view5 = new BookItemView(getActivity());
        view5.setImage(R.drawable.book5);
        view5.setName("별을 잇는 손");
        view5.setAuthor("무라야마 사키");
        view5.setContents("책과 서점을 둘러싼 기적에 관한 이야기로 많은 사랑을 받은 <오후도 서점 이야기>의 후속작.");
        view5.setVisibility(View.INVISIBLE);
        viewContainer.addView(view5);

        translateIn = AnimationUtils.loadAnimation(getActivity(), R.anim.translate_in);
        translateOut = AnimationUtils.loadAnimation(getActivity(), R.anim.translate_out);

        return rootView;
    }

    class AnimationThread extends Thread {
        public void run() {
            running = true;
            while(running) {
                handler.post(new Runnable() {
                    public void run() {
                        if (selected == 0) {
                            view5.setVisibility(View.INVISIBLE);
                            view.startAnimation(translateOut);
                            view2.setVisibility(View.VISIBLE);
                            view2.startAnimation(translateIn);
                        } else if (selected == 1) {
                            view.setVisibility(View.INVISIBLE);
                            view2.startAnimation(translateOut);
                            view3.setVisibility(View.VISIBLE);
                            view3.startAnimation(translateIn);
                        } else if (selected == 2) {
                            view2.setVisibility(View.INVISIBLE);
                            view3.startAnimation(translateOut);
                            view4.setVisibility(View.VISIBLE);
                            view4.startAnimation(translateIn);
                        } else if (selected == 3) {
                            view3.setVisibility(View.INVISIBLE);
                            view4.startAnimation(translateOut);
                            view5.setVisibility(View.VISIBLE);
                            view5.startAnimation(translateIn);
                        } else if (selected == 4) {
                            view4.setVisibility(View.INVISIBLE);
                            view5.startAnimation(translateOut);
                            view.setVisibility(View.VISIBLE);
                            view.startAnimation(translateIn);
                        }
                    }
                });

                selected += 1;
                if (selected > 4) {
                    selected = 0;
                }

                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                }
            }
        }
    }
}
