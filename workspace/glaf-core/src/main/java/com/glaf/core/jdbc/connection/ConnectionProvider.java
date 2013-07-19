/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.glaf.core.jdbc.connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

public interface ConnectionProvider {
	/**
	 * Initialize the connection provider from given properties.
	 * 
	 * @param props
	 * 
	 */
	public void configure(Properties props);

	/**
	 * Grab a connection, with the autocommit mode specified by
	 * <tt>connection.autocommit</tt>.
	 * 
	 * @return a JDBC connection
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException;

	public DataSource getDataSource();

	/**
	 * Dispose of a used connection.
	 * 
	 * @param conn
	 *            a JDBC connection
	 * @throws SQLException
	 */
	public void closeConnection(Connection conn) throws SQLException;

	/**
	 * Release all resources held by this provider. JavaDoc requires a second
	 * sentence.
	 * 
	 * @throws RuntimeException
	 */
	public void close();

}