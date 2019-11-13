package org.unipisa.pr2cheli;

import java.util.TreeSet;
import java.util.HashSet;

/**
 * Category class for 2nd implementation (Board2).
 * contents and friends must not be null.
 * The values of contents are sorted by likes and valid posts.
 * Each value in contents contains the category in which it resides.
 * The values or friends are empty or contain only valid usernames.
 */
public class Category<E extends DataElement> {
    private String category;
    private TreeSet<E> contents;
    private HashSet<String> friends;

    public Category(String name) {
        this.category = name;
        this.contents = new TreeSet<E>();
        this.friends = new HashSet<String>();
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @return the contents
     */
    public TreeSet<E> getContents() {
        return contents;
    }

    /**
     * @return the friends
     */
    public HashSet<String> getFriends() {
        return friends;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @param contents the contents to set
     */
    public void setContents(TreeSet<E> contents) {
        this.contents = contents;
    }

    /**
     * @param friends the friends to set
     */
    public void setFriends(HashSet<String> friends) {
        this.friends = friends;
    }

}