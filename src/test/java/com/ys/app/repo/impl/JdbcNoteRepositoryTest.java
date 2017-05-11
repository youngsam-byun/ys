package com.ys.app.repo.impl;


import com.ys.app.model.Note;
import com.ys.app.repo.NoteRepository;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by byun.ys on 4/11/2017.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig_REPO_TEST.class})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@WebAppConfiguration
@ActiveProfiles("dev")
public class JdbcNoteRepositoryTest {

    @Autowired
    private NoteRepository noteRepository;

    static Note n;
    static int returnId;
    static Date d;

    @Before
    public void init(){
        n=new Note();
        n.setId(1);
        n.setSendId(2);
        n.setRecvId(3);
        n.setMsg("test msg");
        d=new Date();
        n.setSendTime(d);
        n.setSendDel(false);
        n.setRecvDel(false);

        noteRepository.setTable(2);

    }


    @Test
    public void A_insertDataReturnId(){
        for(int i=0;i<10;i++) {
            returnId = noteRepository.create(n);
        }
        assertThat(returnId).isGreaterThanOrEqualTo(1);
    }


    @Test
    public void B_readByIdShouldEqualToReturnId(){
        Note n= noteRepository.read(returnId);
        assertThat(n.getId()).isEqualTo(returnId);
    }


    @Test
    @Transactional
    public void D_deleteByIdShouldEqualToOne(){
        int r= noteRepository.deleteByUpdateSendDel(returnId);
        assertThat(r).isEqualTo(1);
    }

    @Test
    @Transactional
    public void D2_deleteByIdShouldEqualToOne(){
        int r= noteRepository.deleteByUpdateRecvDel(returnId);
        assertThat(r).isEqualTo(1);
    }
    @Test
    public  void E_getListShouldContainsOriginalObject(){
        List<Note> al= noteRepository.getList(1,10);
        assertThat(al.size()).isEqualTo(10);
        assertThat(al.get(0).getMsg()).contains(n.getMsg());
    }

    @Test
    public  void F_getTotalShouldReturnSizeOfOne(){
        int total= noteRepository.getTotal();
        assertThat(total).isGreaterThanOrEqualTo(10);
    }

}
