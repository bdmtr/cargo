package com.cargo.model.service;

import com.cargo.exceptions.DaoException;
import com.cargo.model.dao.BranchDao;
import com.cargo.model.entity.Branch;
import com.cargo.model.enums.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class BranchServiceTest {
    private static final Branch testBranch = new Branch();
    List<Branch> testBranchList = new ArrayList<>();

    @Mock
    BranchDao branchDaoMock;

    @BeforeEach
    public void setUp() {
        testBranch.setId(1);
        testBranch.setCity(City.KYIV);
        testBranch.setAddress("test address");

        testBranchList.add(testBranch);
        testBranchList.add(testBranch);
    }

    @Test
    void getBranchByIdTest() throws DaoException {
        assertNotNull(branchDaoMock);
        BranchService service = new BranchService(branchDaoMock);
        Mockito.when(branchDaoMock.getBranchById(1)).thenReturn(testBranch);
        assertEquals(testBranch, service.getBranchById(1));
    }

    @Test
    void getBranchByCityTest() throws DaoException {
        assertNotNull(branchDaoMock);
        BranchService service = new BranchService(branchDaoMock);
        Mockito.when(branchDaoMock.getBranchByCity("KYIV")).thenReturn(testBranch);
        assertEquals(testBranch, service.getBranchByCity("KYIV"));
    }

    @Test
    void getAllBranchesTest() throws DaoException {
        assertNotNull(branchDaoMock);
        BranchService service = new BranchService(branchDaoMock);
        Mockito.when(branchDaoMock.getAllBranches()).thenReturn(testBranchList);
        assertEquals(testBranchList, service.getAllBranches());
    }
}
