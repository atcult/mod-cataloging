/*
 * (c) LibriCore
 * 
 * Created on 20 Jun 2007
 * 
 * DAOSerialNumberFormatting.java
 */
package com.libricore.patterns;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOSerialNumberFormatting {
	public String getFormattedNumber(String type, int number) {
		String result = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn
					.prepareStatement("SELECT VLU FROM S_SRL_NBR_FRMTNG WHERE TYP = ? AND NBR = ?");
			stmt.setString(1, type);
			stmt.setInt(2, number);
			rs = stmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("vlu");
			} else {
				result = Integer.toString(number);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			result = Integer.toString(number);
		}
		return result;
	}

}
