package tw.com.wd.jersey.service;

public class Book {
    private String id;
    private String title;
    private String description;
    private BookType type;


    public String getID() {
        return id;
    }

    public Book setId(String id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Book setDescription(String description) {
        this.description = description;
        return this;
    }

    public BookType getType() {
        return type;
    }

    public Book setType(BookType type) {
        this.type = type;
        return this;
    }
}
