package com.ys.app.service.impl;

import com.sun.javaws.exceptions.InvalidArgumentException;
import com.ys.app.model.Category;
import com.ys.app.model.User;
import com.ys.app.repo.CategoryRepository;
import com.ys.app.security.util.UtilSecurityContextTest;
import com.ys.app.service.CategoryService;
import com.ys.app.util.TestDoubles;
import com.ys.app.util.UtilPagination;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.security.access.AccessDeniedException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by byun.ys on 4/22/2017.
 */

@RunWith(HierarchicalContextRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryServiceImplTest {

    CategoryRepository categoryRepository;
    CategoryService categoryService;

    @Before
    public void init() {
        categoryRepository= TestDoubles.mock(CategoryRepository.class);
        categoryService=new CategoryServiceImpl(categoryRepository);
    }


    @Test(expected = NullPointerException.class)
    public void A_create_throwNullPointerException(){
        categoryService.create(null,null);
    }

    @Test(expected = AccessDeniedException.class)
    public void B_create_throwAccessDeniedException(){
        Category c= new Category();
        categoryService.create(c, UtilSecurityContextTest.returnAuthentication(new User(),0));
        verify(categoryRepository,times(0)).create(c);
        Mockito.verifyNoMoreInteractions(categoryRepository);
    }

    @Test(expected = AccessDeniedException.class)
    public void C_createTable_throwNullPointerException(){

        Category c= new Category();
        categoryService.create(c, UtilSecurityContextTest.returnAuthentication(new User(),0));

        verify(categoryRepository,times(0)).create(c);
        Mockito.verifyNoMoreInteractions(categoryRepository);
    }

    @Test(expected = NullPointerException.class)
    public void D_read_throwNullPointerException(){

        categoryService.read(null);
        verify(categoryRepository,times(0)).read(0);
        Mockito.verifyNoMoreInteractions(categoryRepository);
    }

    @Test(expected = NullPointerException.class)
    public void D_update_throwNullPointerException(){
        categoryService.update(null,null);
        verify(categoryRepository,times(0)).update(null);
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test(expected = AccessDeniedException.class)
    public void E_update_throwAccessDeniedException(){
        categoryService.update(new Category(),UtilSecurityContextTest.returnAuthentication(new User(),0));
        verify(categoryRepository,times(0)).update(new Category());
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test(expected = NullPointerException.class)
    public void E1_delete_throwNullPointerException(){
        categoryService.delete(null,null);
        verify(categoryRepository,times(0)).delete(0);
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test(expected = AccessDeniedException.class)
    public void E2_delete_throwAccessDeniedException(){
        categoryService.delete(1,UtilSecurityContextTest.returnAuthentication(new User(),0));
        verify(categoryRepository,times(0)).delete(0);
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test(expected = NullPointerException.class)
    public  void F_getList_throwNullPointerException(){
        categoryService.getList(null,null);
        verify(categoryRepository,times(0)).getList(0,0);
        verifyNoMoreInteractions(categoryRepository);
    }
    @Test(expected = NullPointerException.class)
    public  void G_getListBySearch_throwNullPointerException(){
        categoryService.getListBySearch(null,null,null);
        verify(categoryRepository,times(0)).getListBySearch(0,0,null);
        verifyNoMoreInteractions(categoryRepository);
    }
    @Test(expected = NullPointerException.class)
    public  void H_getPagination_throwNullPointerException(){
        categoryService.getPagination(null,null);
        verify(categoryRepository,times(0)).getTotal();
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test(expected = NullPointerException.class)
    public  void I_getPaginationBySearch_throwNullPointerException(){
        categoryService.getPaginationBySearch(null,null,null);
        verify(categoryRepository,times(0)).getTotalBySearch(null);
        verifyNoMoreInteractions(categoryRepository);
    }

    @FixMethodOrder(MethodSorters.NAME_ASCENDING)
    public class ObjectFound{
        Category created;
        List<Category> createdList;
        @Before
        public  void setUp(){
            created =createCategory();
            createdList=createdCategoryList();

        }

        private Category createCategory() {
            Category c= new Category();
            c.setId(1);
            c.setName("newTable");
            c.setCreateTime(new Date());
            return c;
        }

        private  List<Category> createdCategoryList(){
            List<Category> categoryList=new ArrayList<>();
            categoryList.add(created);
            return categoryList;
        }

        @Test
        public  void A_create_returnTrue(){
            when(categoryRepository.create(created)).thenReturn(1);


            boolean b=categoryService.create(created,UtilSecurityContextTest.returnAuthentication(new User(),9));
            assertThat(b).isTrue();
            verify(categoryRepository,times(1)).createTable(created.getName());
            verify(categoryRepository,times(1)).create(created);
            verifyNoMoreInteractions(categoryRepository);
        }

        @Test
        public  void B_read_returnObject(){
            when(categoryRepository.read(1)).thenReturn(created);

            Category returned=categoryService.read(1);
            assertThat(returned).isEqualTo(created);
            verify(categoryRepository,times(1)).read(1);
            verifyNoMoreInteractions(categoryRepository);
        }

        @Test
        public  void C_update_returnTrue(){
            Category c = new Category();
            c.setId(1);
            c.setName("old");

            when(categoryRepository.update(created)).thenReturn(1);
            when(categoryRepository.read(1)).thenReturn(c);

            boolean returned=categoryService.update(created,UtilSecurityContextTest.returnAuthentication(new User(),9));
            assertThat(returned).isEqualTo(true);
            verify(categoryRepository,times(1)).read(1);
            verify(categoryRepository,times(1)).renameTable(c.getName(),created.getName());
            verify(categoryRepository,times(1)).update(created);

            verifyNoMoreInteractions(categoryRepository);
        }

        @Test
        public  void D_delete_returnTrue(){

            when(categoryRepository.read(1)).thenReturn(created);
            when(categoryRepository.delete(1)).thenReturn(1);

            categoryService.delete(1,UtilSecurityContextTest.returnAuthentication(new User(),9));

            verify(categoryRepository,times(1)).read(1);
            verify(categoryRepository,times(1)).dropTable(created.getName());
            verify(categoryRepository,times(1)).delete(1);
            verifyNoMoreInteractions(categoryRepository);
        }

        @Test
        public  void E_getList_returnListArray() throws InvalidArgumentException {
            when(categoryRepository.getList(1,10)).thenReturn(createdList);

            List<Category> cl=categoryService.getList(1,10);
            assertThat(cl).isEqualTo(createdList) ;
            verify(categoryRepository,times(1)).getList(1,10);
            verifyNoMoreInteractions(categoryRepository);
        }

        @Test
        public  void F_getListBySearch_returnListArray() throws InvalidArgumentException {
            when(categoryRepository.getListBySearch(1,10,"ha")).thenReturn(createdList);

            List<Category> cl=categoryService.getListBySearch(1,10,"ha");
            assertThat(cl).isEqualTo(createdList) ;
            verify(categoryRepository,times(1)).getListBySearch(1,10,"ha");
            verifyNoMoreInteractions(categoryRepository);
        }

        @Test
        public  void G_getPagination_returnUtilPageNation(){
            when(categoryRepository.getTotal()).thenReturn(100);

            UtilPagination up= categoryService.getPagination(1,10);
            assertThat(up).isNotNull();
            verify(categoryRepository,times(1)).getTotal();
            verifyNoMoreInteractions(categoryRepository);
        }

        @Test
        public  void H_getPaginationBySearch_returnUtilPageNation(){
            when(categoryRepository.getTotalBySearch("ha")).thenReturn(100);

            UtilPagination up= categoryService.getPaginationBySearch(1,10,"ha");
            assertThat(up).isNotNull();

            verify(categoryRepository,times(1)).getTotalBySearch("ha");
            verifyNoMoreInteractions(categoryRepository);
        }
    }
}
