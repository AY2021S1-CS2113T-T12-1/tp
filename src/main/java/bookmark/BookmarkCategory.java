package bookmark;

import java.util.ArrayList;

public abstract class BookmarkCategory {
    public abstract String getName();

    public abstract ArrayList<BookmarkList> getLinks();

    public abstract void addLink(String link);

    public abstract void removeLink(int number);
}
