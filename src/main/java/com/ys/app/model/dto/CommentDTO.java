package com.ys.app.model.dto;

import com.ys.app.model.Comment;
import com.ys.app.model.User;

/**
 * Created by byun.ys on 4/17/2017.
 */
public class CommentDTO {


    private Comment comment;
    private User user;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
