package com.cargo.model.dao;

import com.cargo.model.entity.Branch;
import com.cargo.model.entity.Cargo;
import com.cargo.model.entity.User;
import com.cargo.model.enums.City;
import com.cargo.model.enums.DeliveryStatus;
import com.cargo.model.enums.InvoiceStatus;
import com.cargo.model.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CargoDaoTest {
    private static final Connection jdbcConnection = Mockito.mock(Connection.class);
    private static final PreparedStatement statement = Mockito.mock(PreparedStatement.class);

    private CargoDao cargoDao = Mockito.mock(CargoDao.class);
    private static final Cargo cargo = new Cargo();
    private static final User user = new User();
    private static final Branch branch = new Branch();

    @BeforeEach
    public void setUp() {
        user.setId(1);
        user.setUsername("username");
        user.setFullname("fullname");
        user.setEmail("email@gmail.com");
        user.setPassword("password");
        user.setRole(Role.USER);
        user.setBalance(100);

        cargo.setId(1);
        cargo.setType("");
        cargo.setReceiverFullname("");
        cargo.setUser(user);
        cargo.setDepartureBranch(new Branch());
        cargo.setDestinationBranch(new Branch());
        cargo.setPrice(1);
        cargo.setWeight(1);
        cargo.setLength(1);
        cargo.setHeight(1);
        cargo.setWidth(1);
        cargo.setCreationDate(Timestamp.valueOf("2023-01-15 03:02:12"));
        cargo.setDeliveryDate(Timestamp.valueOf("2023-01-15 03:02:12"));
        cargo.setDeliveryStatus(DeliveryStatus.TRANSIT);
        cargo.setInvoiceStatus(InvoiceStatus.PENDING);
        branch.setId(1);
        branch.setCity(City.KYIV);
        branch.setAddress("address");
    }

    @Test
    void getInstanceTest() {
        cargoDao = CargoDao.getInstance();
        boolean exist = cargoDao != null;
        assertTrue(exist);
    }

    @Test
    void getCargoByIdTest() throws SQLException {
        Mockito.when(cargoDao.getCargoById(1)).thenReturn(cargo);
        assertEquals(cargo, cargoDao.getCargoById(1));
        assertDoesNotThrow(() -> cargoDao.getCargoById(1));
    }

    @Test
    void changeInvoiceStatusTest() throws SQLException {
        Mockito.when(jdbcConnection.createStatement()).thenReturn(statement);
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        doNothing().when(statement).setString(eq(1), any());
        when(statement.execute()).thenReturn(true);

        cargoDao.changeInvoiceStatus(1);
        assertEquals(statement, jdbcConnection.createStatement());
        verify(cargoDao).changeInvoiceStatus(1);
        assertDoesNotThrow(() -> cargoDao.changeInvoiceStatus(1));
    }

    @Test
    void updateCargoProfileTest() throws SQLException {
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.execute()).thenReturn(true);

        cargoDao.updateCargoProfile(cargo);
        verify(cargoDao).updateCargoProfile(cargo);
        assertDoesNotThrow(() -> cargoDao.updateCargoProfile(cargo));
    }

    @Test
    void addCargoTest() throws SQLException {
        when(jdbcConnection.prepareStatement(any())).thenReturn(statement);
        when(statement.execute()).thenReturn(true);
        cargoDao.addCargo(cargo);
        verify(cargoDao).addCargo(cargo);
        assertDoesNotThrow(() -> cargoDao.addCargo(cargo));
    }
}
