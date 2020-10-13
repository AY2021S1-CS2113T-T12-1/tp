package bookmark;

import java.util.ArrayList;

public class NusCategory extends BookmarkCategory {
    private String name = "NUS";
    private ArrayList<BookmarkList> links = new ArrayList<>();

    public String getName() {
        return name;
    }

    public ArrayList<BookmarkList> getLinks() {
        return links;
    }

    public void addLink(String link) {
        links.add(new BookmarkList(link));
    }

    public void removeLink(int number) {
        links.remove(number - 1);
    }
}
