package org.techtown.mission22;

import java.util.ArrayList;

public interface OnDatabaseCallback {
    public void insert(String name, String author, String contents, String url);
    public ArrayList<BookInfo> selectAll();
    public void delete(String name, String author);
}
