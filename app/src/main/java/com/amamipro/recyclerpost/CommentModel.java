package com.amamipro.recyclerpost;

public class CommentModel {
    private String postId;
    private String id;
    private String name;
    private String email;
    private String comment;

    public CommentModel() {
    }
    public CommentModel(String postId, String id, String name, String email, String comment) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.comment = comment;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

