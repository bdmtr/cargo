package com.cargo.controller.command;

import com.cargo.controller.Path;
import com.cargo.controller.command.PageCommands.ShowUserPageCommand;
import com.cargo.model.entity.User;
import com.cargo.model.service.CargoService;
import com.cargo.model.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShowUserPageCommandTest {
    private ShowUserPageCommand showUserPageCommand;

    @Mock
    private CargoService cargoService;
    @Mock
    private UserService userService;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private HttpSession session;

    @BeforeEach
    public void setUp() {
        showUserPageCommand = new ShowUserPageCommand(cargoService, userService);
    }

    @Test
    void setShowUserPageSuccessfully() throws IOException, SQLException {
        int page = 1;
        int recordsPerPage = 10;
        int userId = 1;
        User user = new User();
        user.setBalance(100);
        when(session.getAttribute("currentUserId")).thenReturn(userId);
        when(userService.findUserById(userId)).thenReturn(user);
        when(request.getParameter("page")).thenReturn(String.valueOf(page));
        when(cargoService.getAllCargoForUserByIdWithLimit(userId, 0, recordsPerPage)).thenReturn(List.of());
        when(cargoService.getNoOfRecords()).thenReturn(0);
        when(request.getSession()).thenReturn(session);

        String result = showUserPageCommand.execute(request, response);

        verify(session).setAttribute("balance", 100);
        verify(request).setAttribute("cargoList", List.of());
        verify(request).setAttribute("noOfPages", 0);
        verify(request).setAttribute("currentPage", page);
        assertEquals(Path.PAGE_SHOW_CARGOS, result);
    }
}