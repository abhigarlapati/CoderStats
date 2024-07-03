package com.example.WebScrapping.model;

public class Contest {
	
	private String name;
    private String link;
    private String startsIn;

    public Contest() {
        // Default constructor
    }

    // Constructor with fields
    public Contest(String name, String link, String startsIn) {
        this.name = name;
        this.link = link;
        this.startsIn = startsIn;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStartsIn() {
        return startsIn;
    }

    public void setStartsIn(String startsIn) {
        this.startsIn = startsIn;
    }

}
