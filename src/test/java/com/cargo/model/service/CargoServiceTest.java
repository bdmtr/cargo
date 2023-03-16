package com.cargo.model.service;

import com.cargo.exceptions.DaoException;
import com.cargo.model.dao.CargoDao;
import com.cargo.model.entity.Cargo;
import com.cargo.model.entity.User;
import com.cargo.model.enums.DeliveryStatus;
import com.cargo.model.enums.InvoiceStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class CargoServiceTest {
    private static final Cargo testCargo = new Cargo();
    List<Cargo> testCargoList = new ArrayList<>();

    @Mock
    CargoDao cargoDaoMock;

    @Mock
    User user;

    @BeforeEach
    public void setUp() {
        testCargo.setId(1);
        testCargo.setType("test Type");
        testCargo.setUser(user);
        testCargo.setReceiverFullname(user.getFullname());
        testCargo.setPrice(100);
        testCargo.setWeight(100);
        testCargo.setLength(100);
        testCargo.setHeight(100);
        testCargo.setWidth(100);
        testCargo.setCreationDate(Timestamp.valueOf("2023-01-15 03:02:12"));
        testCargo.setDeliveryDate(Timestamp.valueOf("2023-01-15 03:02:12"));
        testCargo.setDeliveryStatus(DeliveryStatus.TRANSIT);
        testCargo.setInvoiceStatus(InvoiceStatus.PENDING);

        testCargoList.add(testCargo);
        testCargoList.add(testCargo);
        testCargoList.add(testCargo);
        testCargoList.add(testCargo);
    }

    @Test
    void getCargoByIdTest() throws SQLException {
        assertNotNull(cargoDaoMock);
        CargoService service = new CargoService(cargoDaoMock);
        Mockito.when(cargoDaoMock.getCargoById(1)).thenReturn(testCargo);
        assertEquals(testCargo, service.getCargoById(1));
    }

    @Test
    void addCargoTest() throws SQLException {
        assertNotNull(cargoDaoMock);
        CargoService service = new CargoService(cargoDaoMock);
        service.addCargo(testCargo);
        Mockito.verify(cargoDaoMock).addCargo(testCargo);
    }

    @Test
    void changeInvoiceStatusTest() {
        assertNotNull(cargoDaoMock);
        CargoService service = new CargoService(cargoDaoMock);
        service.changeInvoiceStatus(1);
        Mockito.verify(cargoDaoMock).changeInvoiceStatus(1);
    }

    @Test
    void sortByCityDateTest() throws SQLException, DaoException {
        assertNotNull(cargoDaoMock);
        CargoService service = new CargoService(cargoDaoMock);
        Mockito.when(cargoDaoMock.sortByCityDate(1, 10, "KYIV", null)).thenReturn(testCargoList);
        service.sortByCityDate(1, 10, "KYIV", null);
        assertEquals(testCargoList, service.sortByCityDate(1, 10, "KYIV", null));
    }

    @Test
    void sortByCityDateManagerTest() throws SQLException, DaoException {
        assertNotNull(cargoDaoMock);
        CargoService service = new CargoService(cargoDaoMock);
        Mockito.when(cargoDaoMock.sortByCityDateManager(1, 10, "1", "2023-01-15 03:02:12", "ASC")).thenReturn(testCargoList);
        service.sortByCityDate(1, 10, "KYIV", null);
        assertEquals(testCargoList, service.sortByCityDateManager(1, 10, "1", "2023-01-15 03:02:12", "ASC"));
    }

    @Test
    void updateCargoProfileTest() {
        assertNotNull(cargoDaoMock);
        CargoService service = new CargoService(cargoDaoMock);
        service.updateCargoProfile(testCargo);
        Mockito.verify(cargoDaoMock).updateCargoProfile(testCargo);
    }

    @Test
    void getAllCargoForUserByIdWithLimitTest(){
        assertNotNull(cargoDaoMock);
        CargoService service = new CargoService(cargoDaoMock);
        Mockito.when(cargoDaoMock.getAllCargoForUserByIdWithLimit(1, 2, 5)).thenReturn(testCargoList);
        service.getAllCargoForUserByIdWithLimit(1, 2, 5);
        assertEquals(testCargoList, service.getAllCargoForUserByIdWithLimit(1, 2, 5));
    }


    @Test
     void getNoOfRecordsTest(){
         assertNotNull(cargoDaoMock);
         CargoService service = new CargoService(cargoDaoMock);
         service.getNoOfRecords();
         Mockito.verify(cargoDaoMock).getNoOfRecords();
     }
}
