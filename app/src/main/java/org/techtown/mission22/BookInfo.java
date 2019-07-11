package org.techtown.mission22;

public class BookInfo {

    String name;
    String author;
    String contents;
    String url;

    public BookInfo(String name, String author, String contents, String url) {
        this.name = name;
        this.author = author;
        this.contents = contents;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    @Override
    public String toString() {
        return "BookInfo{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", contents='" + contents + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
