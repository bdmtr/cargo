package com.cargo.util;


import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;


class DataSourceUtilTest {

    @Test
    void testConnection() {
        assertDoesNotThrow(DataSourceUtil::getConnection);
    }

}