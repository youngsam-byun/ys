package com.ys.app.service.impl;

import com.ys.app.model.Comment;
import com.ys.app.model.Role;
import com.ys.app.model.User;
import com.ys.app.model.dto.CommentDTO;
import com.ys.app.repo.CommentRepository;
import com.ys.app.repo.UserRepository;
import com.ys.app.security.CustomUserDetails;
import com.ys.app.service.CommentService;
import com.ys.app.util.UtilPagination;
import com.ys.app.util.UtilValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by byun.ys on 4/17/2017.
 */

@SuppressWarnings({"ALL", "PointlessBooleanExpression"})
@Service
public class CommentServiceImpl implements CommentService {


    @Value("${commentService.write.noPermission?:}")
    private String NO_PERMISSION_TO_WRITE_ARTICLE;// = "commentservice.write.nopermission";
    @Value("${commentService.update.noPermission?:}")
    private String NO_PERMISSION_TO_UPDATE_ARTICLE;// = "commentservice.update.nopermission";
    @Value("${commentService.delete.noPermission?:}")
    private String NO_PERMISSION_TO_DELETE_ARTICLE;// = "commentservice.delete.nopermission";


    private CommentRepository commentRepository;
    private UserRepository userRepository;
    private Role role;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserRepository userRepository) {

        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        role = Role.USER;
    }


    @Override
    public void setTable(String table) {
        commentRepository.setTable(table);
    }

    @Override
    public boolean writeComment(Comment comment, Principal principal) {
        if (UtilValidation.isNull(comment, principal))
            throw new NullPointerException();


        if (hasWritePermission(principal, role) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_WRITE_ARTICLE);

        return commentRepository.create(comment) >= 1;
    }

    @Override
    public CommentDTO readComment(Integer id) {
        if (UtilValidation.isNull(id))
            throw new NullPointerException();


        Comment comment = commentRepository.read(id);
        if (comment == null)
            return null;

        int userId = comment.getUserId();
        User user = userRepository.read(userId);

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setComment(comment);
        commentDTO.setUser(user);

        return commentDTO;

    }

    @Override
    public boolean updateComment(Comment comment, Principal principal) {
        if (UtilValidation.isNull(comment, principal))
            throw new NullPointerException();

        if (hasUpdatePermission(principal, comment) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_UPDATE_ARTICLE);

        return commentRepository.update(comment) == 1;
    }

    @Override
    public boolean deleteComment(Integer id, Principal principal) {
        if (UtilValidation.isNull(id))
            throw new NullPointerException();

        if (hasDeletePermission(principal, id) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_DELETE_ARTICLE);

        return commentRepository.delete(id) == 1;
    }


    @Override
    public List<CommentDTO> getList(Integer pageNo, Integer pageSize) {
        if (UtilValidation.isNull(pageNo, pageSize))
            throw new NullPointerException();


        List<Comment> commentList = commentRepository.getList(pageNo, pageSize);

        return getCommentDTOList(commentList);

    }


    @Override
    public UtilPagination getPagination(Integer pageNo, Integer pageSize) {
        if (UtilValidation.isNull(pageNo))
            throw new NullPointerException();


        int total = commentRepository.getTotal();
        return new UtilPagination(pageNo, total, pageSize);
    }

    private boolean hasWritePermission(Principal principal, Role role) {
        User user = getUser(principal);
        int roleId = user.getRoleId();
        return roleId >= role.getId();
    }

    private User getUser(Principal principal) {
        return ((CustomUserDetails) principal).getUser();
    }

    private boolean hasUpdatePermission(Principal principal, Comment comment) {
        User user = getUser(principal);
        int roleId = user.getRoleId();
        int id = user.getId();
        int userId = comment.getUserId();

        return id == userId || roleId >= role.getId();

    }

    private boolean hasDeletePermission(Principal principal, Integer commentId) {
        User user = getUser(principal);
        int roleId = user.getRoleId();
        int id = user.getId();

        Comment comment = commentRepository.read(commentId);
        int userId = comment.getUserId();

        return id == userId || roleId >= role.getId();
    }

    private List<CommentDTO> getCommentDTOList(List<Comment> commentList) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (Comment comment : commentList) {
            int userId = comment.getUserId();
            User user = userRepository.read(userId);
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setComment(comment);
            commentDTO.setUser(user);

            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
