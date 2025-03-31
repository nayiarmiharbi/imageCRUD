package com.example.imagecrud.model;

public class Image {
    private Long id;
    private String name;
    private String type;
    private byte[] data;

    // Constructors
    public Image() {}
    public Image(Long id, String name, String type, byte[] data) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.data = data;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public byte[] getData() { return data; }
    public void setData(byte[] data) { this.data = data; }
}