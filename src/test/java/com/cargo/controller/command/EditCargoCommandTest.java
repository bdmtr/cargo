package com.cargo.controller.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cargo.model.enums.DeliveryStatus;
import com.cargo.model.enums.InvoiceStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cargo.model.entity.Cargo;
import com.cargo.model.service.CargoService;

import java.io.IOException;
import java.sql.SQLException;


@ExtendWith(MockitoExtension.class)
class EditCargoCommandTest {
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;
    @Mock
    private CargoService cargoService;
    @InjectMocks
    private EditCargoCommand editCargoCommand;

    private Cargo cargo;
    private static final int CARGO_ID = 1;
    private static final String RECEIVER_FULLNAME = "BILBO";
    private static final String DELIVERY_STATUS = "DELIVERED";
    private static final String INVOICE_STATUS = "PAYED";

    @BeforeEach
    void setUp() {
        cargo = new Cargo();
        cargo.setId(CARGO_ID);
        cargo.setReceiverFullname(RECEIVER_FULLNAME);
        cargo.setDeliveryStatus(DeliveryStatus.DELIVERED);
        cargo.setInvoiceStatus(InvoiceStatus.PAYED);
    }

    @Test
    void testEditCargoCommand() throws IOException, SQLException {
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("session_current_editId")).thenReturn(CARGO_ID);
        when(request.getParameter("receiverFullname")).thenReturn(RECEIVER_FULLNAME);
        when(request.getParameter("deliveryStatus")).thenReturn(DELIVERY_STATUS);
        when(request.getParameter("invoiceStatus")).thenReturn(INVOICE_STATUS);
        when(cargoService.getCargoById(CARGO_ID)).thenReturn(cargo);
        doNothing().when(cargoService).updateCargoProfile(cargo);

        String result = editCargoCommand.execute(request, response);

        assertEquals("redirect:controller?action=showmanagerpage", result);
        verify(request).getSession();
        verify(session).getAttribute("session_current_editId");
        verify(request).getParameter("receiverFullname");
        verify(request).getParameter("deliveryStatus");
        verify(request).getParameter("invoiceStatus");
        verify(cargoService).getCargoById(CARGO_ID);
        verify(cargoService).updateCargoProfile(cargo);
        assertEquals(RECEIVER_FULLNAME, cargo.getReceiverFullname());
        assertEquals(DeliveryStatus.valueOf(DELIVERY_STATUS), cargo.getDeliveryStatus());
        assertEquals(InvoiceStatus.valueOf(INVOICE_STATUS), cargo.getInvoiceStatus());
    }
}