package com.ys.app.service.impl;

import com.ys.app.model.Note;
import com.ys.app.model.Role;
import com.ys.app.model.User;
import com.ys.app.model.dto.NoteDTO;
import com.ys.app.repo.NoteRepository;
import com.ys.app.repo.UserRepository;
import com.ys.app.service.NoteService;
import com.ys.app.util.UtilPagination;
import com.ys.app.util.UtilValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import static com.ys.app.security.service.CustomUserDetailsService.extractUser;

/**
 * Created by byun.ys on 5/11/2017.
 */
@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;
    private UserRepository userRepository;
    private Role role;


    @Value("${noteService.create.noPermission?:noteService.create.noPermission}")
    private String NO_PERMISSION_TO_CREATE_NOTE;// = "noteService.create.noPermission";
    @Value("${noteService.read.noPermission?:noteService.read.noPermission}")
    private String NO_PERMISSION_TO_READ_NOTE;// = "noteService.create.noPermission";
    @Value("${noteService.delete.noPermission?:noteService.delete.noPermission?}")
    private String NO_PERMISSION_TO_DELETE_NOTE;// = "noteService.delete.noPermission";

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.role = Role.USER;
    }

    @Override
    public boolean create(Integer userId,Note note, Principal principal) {
        if (UtilValidation.isNull(userId,note, principal))
            throw new NullPointerException();

        if (hasCreatePermission(principal, role) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_CREATE_NOTE);

        noteRepository.setTable(userId);
        return noteRepository.create(note) >= 1;
    }

    @Override
    public Note read(Integer userId,Integer id, Principal principal) {

        if (UtilValidation.isNull(userId,id, principal))
            throw new NullPointerException();

        noteRepository.setTable(userId);
        Note note = noteRepository.read(id);

        if (hasReadPermission(principal, role, note) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_READ_NOTE);

        return  note;

    }

    @Override
    public boolean delete(Integer userId,Integer id, Principal principal) {

        if (UtilValidation.isNull(userId,id, principal))
            throw new NullPointerException();

        noteRepository.setTable(userId);
        Note note = noteRepository.read(id);

        if (hasDeletePermission(principal, role, note) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_DELETE_NOTE);

        int sendId=note.getSendId();
        int recvId=note.getRecvId();

        if(userId==sendId)
            return noteRepository.deleteByUpdateSendDel(id)==1;
        else
            return noteRepository.deleteByUpdateRecvDel(id)==1;
    }

    @Override
    public List<NoteDTO> getList(Integer userId,Integer pageNo, Integer pageSize) {
        if (UtilValidation.isNull(userId,pageNo, pageSize))
            throw new NullPointerException();

        noteRepository.setTable(userId);
        List<Note> noteList=noteRepository.getList(pageNo,pageSize);
        List<NoteDTO> noteDTOList=new ArrayList<>();

        for(Note note:noteList){
            User user=userRepository.read(userId);
            NoteDTO noteDTO=new NoteDTO();
            noteDTO.setNote(note);
            noteDTO.setUser(user);

            noteDTOList.add(noteDTO);
        }

        return  noteDTOList;
    }

    @Override
    public UtilPagination getPagination(Integer userId,Integer pageNo, Integer pageSize) {
        if (UtilValidation.isNull(userId,pageNo, pageSize))
            throw new NullPointerException();

        noteRepository.setTable(userId);

        int total=noteRepository.getTotal();
        return new UtilPagination(pageNo,total,pageSize);

    }

    private boolean hasCreatePermission(Principal principal, Role role) {
        User user = extractUser(principal);
        int roleId = user.getRoleId();
        return roleId >= role.getId();
    }


    private boolean hasReadPermission(Principal principal, Role role, Note note) {
        User user = extractUser(principal);

        int roleId = user.getRoleId();

        if (roleId < role.getId())
            return false;

        int userId = user.getId();
        int sendId = note.getSendId();
        int recvId = note.getRecvId();

        return userId == sendId || userId == recvId;

    }


    private boolean hasDeletePermission(Principal principal, Role role,Note note) {
        User user = extractUser(principal);
        int roleId = user.getRoleId();

        if (roleId < role.getId())
            return false;

        int userId = user.getId();

        int sendId = note.getSendId();
        int recvId = note.getRecvId();

        return userId == sendId || userId == recvId || roleId >= role.getId();
    }

}