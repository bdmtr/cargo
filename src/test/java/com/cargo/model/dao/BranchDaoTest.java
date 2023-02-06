package com.cargo.model.dao;

import com.cargo.model.entity.Branch;
import com.cargo.model.enums.City;
import com.cargo.model.service.BranchService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BranchDaoTest {
    private BranchDao branchDao;
    private BranchService branchService;
    private static final Branch branch = new Branch();

    @BeforeEach
    void setUp() {
        branchDao = mock(BranchDao.class);
        branchService = new BranchService(branchDao);

        branch.setId(1);
        branch.setCity(City.KYIV);
        branch.setAddress("address");
    }

    @Test
    void getAllBranchesTest() throws SQLException {
        List<Branch> expected = new ArrayList<>();
        expected.add(new Branch(1, City.KYIV, "Kyiv"));
        expected.add(new Branch(2, City.DNIPRO, "Dnipro"));
        expected.add(new Branch(3, City.KHARKIV, "Kharkiv"));
        expected.add(new Branch(4, City.LVIV, "Lviv"));
        when(branchDao.getAllBranches()).thenReturn(expected);
        List<Branch> actual = branchService.getAllBranches();

        assertEquals(expected, actual);
    }

    @Test
    void getAllBranchesShouldReturnEmptyListWhenThereAreNoBranches() throws SQLException {
        when(branchDao.getAllBranches()).thenReturn(Collections.emptyList());
        List<Branch> actual = branchService.getAllBranches();
        assertTrue(actual.isEmpty());
    }

    @Test
    void getBranchByIdTest() throws SQLException {
        Mockito.when(branchDao.getBranchById(1)).thenReturn(branch);
        assertEquals(branch, branchDao.getBranchById(1));
    }

    @Test
    void getBranchByCityTest() throws SQLException {
        Mockito.when(branchDao.getBranchByCity("KYIV")).thenReturn(branch);
        assertEquals(branch, branchDao.getBranchByCity("KYIV"));
    }

    @Test
    void getInstanceTest() {
        branchDao = BranchDao.getInstance();
        boolean exist = branchDao != null;
        assertTrue(exist);
    }
}