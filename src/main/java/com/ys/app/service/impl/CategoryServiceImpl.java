package com.ys.app.service.impl;

import com.ys.app.model.Category;
import com.ys.app.model.Role;
import com.ys.app.model.User;
import com.ys.app.repo.CategoryRepository;
import com.ys.app.service.CategoryService;
import com.ys.app.util.UtilPagination;
import com.ys.app.util.UtilValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ys.app.security.service.CustomUserDetailsService.extractUser;

/**
 * Created by byun.ys on 4/22/2017.
 */

@Service
public class CategoryServiceImpl implements CategoryService {

    @Value("${categoryService.create.noPermission}")
    private String NO_PERMISSION_TO_CREATE_CATEGORY = "categoryService.create.noPermission";
    @Value("${categoryService.update.noPermission}")
    private String NO_PERMISSION_TO_UPDATE_CATEGORY = "categoryService.update.noPermission";
    @Value("${categoryService.delete.noPermission}")
    private String NO_PERMISSION_TO_DELETE_CATEGORY = "categoryService.delete.noPermission";

    private CategoryRepository categoryRepository;
    private Role role;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
        this.role = Role.SUPERADMIN;
    }

    @Override
    @Transactional
    public boolean create(Category category, Principal principal) {

        if (UtilValidation.isNull(category, principal))
            throw new NullPointerException();

        if (hasPermission(principal, role) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_CREATE_CATEGORY);

        String table = category.getName();
        if (table == null || table.isEmpty())
            throw new NullPointerException();

        categoryRepository.createTable(table);
        return categoryRepository.create(category) >= 1;

    }


    @Override
    public Category read(Integer id) {

        if (UtilValidation.isNull(id))
            throw new NullPointerException();

        return categoryRepository.read(id);

    }

    @Override
    @Transactional
    public boolean update(Category category, Principal principal) {

        if (UtilValidation.isNull(category, principal))
            throw new NullPointerException();

        if (hasPermission(principal, role) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_UPDATE_CATEGORY);

        int categoryId = category.getId();
        String newTable = category.getName();

        Category c = categoryRepository.read(categoryId);
        String oldTable = c.getName();

        if (newTable == null || newTable.isEmpty())
            throw new NullPointerException();

        if (oldTable.equals(newTable) == false) {
            categoryRepository.renameTable(oldTable, newTable);
        }

        return categoryRepository.update(category) == 1;
    }

    @Override
    @Transactional
    public boolean delete(Integer id, Principal principal) {
        if (UtilValidation.isNull(id, principal))
            throw new NullPointerException();

        if (hasPermission(principal, role) == false)
            throw new AccessDeniedException(NO_PERMISSION_TO_DELETE_CATEGORY);

        Category category = categoryRepository.read(id);
        String tableName = category.getName();

        if (tableName == null || tableName.isEmpty())
            throw new NullPointerException();

        categoryRepository.dropTable(tableName);
        return categoryRepository.delete(id) == 1;
    }

    @Override
    public List<Category> getList(Integer pageNo, Integer pageSize) {

        if (UtilValidation.isNull(pageNo, pageSize))
            throw new NullPointerException();

        return categoryRepository.getList(pageNo, pageSize);

    }

    @Override
    public List<Category> getListBySearch(Integer pageNo, Integer pageSize, String keyword) {

        if (UtilValidation.isNull(pageNo, pageSize, keyword))
            throw new NullPointerException();

        return categoryRepository.getListBySearch(pageNo, pageSize, keyword);
    }

    @Override
    public UtilPagination getPagination(Integer pageNo, Integer pageSize) {
        if (UtilValidation.isNull(pageNo, pageSize))
            throw new NullPointerException();

        int total = categoryRepository.getTotal();
        return new UtilPagination(pageNo, total, pageSize);
    }

    @Override
    public UtilPagination getPaginationBySearch(Integer pageNo, Integer pageSize, String keyword) {

        if (UtilValidation.isNull(pageNo, pageSize, keyword))
            throw new NullPointerException();

        int total = categoryRepository.getTotalBySearch(keyword);
        return new UtilPagination(pageNo, total, pageSize);
    }

    @Override
    public Map<Integer, Category> getAllHashMap() {
        List<Category> categoryList = categoryRepository.getAll();
        Map<Integer, Category> hashMap = new HashMap<>();
        for (Category c : categoryList) {
            hashMap.put(c.getId(), c);
        }
        return hashMap;

    }

    public boolean hasPermission(Principal principal) {
        return  hasPermission(principal,role);
    }

    private boolean hasPermission(Principal principal, Role role) {
        User user = extractUser(principal);
        int roleId = user.getRoleId();
        return roleId >= role.getId();
    }
    
}
