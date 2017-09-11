package com.laowei.dbassist;

import java.sql.ResultSet;

public interface ResultSetHandler {

    Object handle(ResultSet resultSet);
}
