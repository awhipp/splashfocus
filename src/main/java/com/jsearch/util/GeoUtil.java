package com.jsearch.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.jsearch.database.MySQLConnector;

public class GeoUtil {

	private static final String API_KEY = "API KEY";
	private static GeoApiContext context;
	private static final Logger LOG = Logger.getLogger(GeoUtil.class.getName());

	public static void init() {
		if (context == null) {
			context = new GeoApiContext().setApiKey(API_KEY);
		}
	}

	public static double[] getCoordinates(String address) throws SQLException {
		GeocodingResult[] results;
		ResultSet rs = null;

		try {
			String query = "SELECT latitude, longitude FROM `geo_assist` WHERE address = ?";
			Connection c = MySQLConnector.instance();
			PreparedStatement stmt = c.prepareStatement(query);
			stmt.setString(1, address);
			rs = stmt.executeQuery();

			while (rs.next()) {
				double lat = rs.getDouble("latitude");
				double lng = rs.getDouble("longitude");
				LOG.info("Old Address found: " + address + " at (" + lat + ", " + lng + ")");
				return new double[] { lat, lng };
			}

			results = GeocodingApi.geocode(context, address).await();

			if (results.length > 0) {
				LatLng coords = results[0].geometry.location;

				query = "INSERT INTO `geo_assist` (`address`,`latitude`,`longitude`) VALUES" + "(?,?,?)";
				stmt = c.prepareStatement(query);
				stmt.setString(1, address);
				stmt.setDouble(2, coords.lat);
				stmt.setDouble(3, coords.lng);
				stmt.executeUpdate();
				LOG.info("New Address Entered: " + address + " at (" + coords.lat + ", " + coords.lng + ")");
				return new double[] { coords.lat, coords.lng };
			} else {
				LOG.info("Error - Probably Address Not Found: " + address);
				return new double[] { -1, -1 };
			}
		} catch (Exception e) {
			LOG.info("Error - Probably Address Not Found: " + e);
			return new double[] { -1, -1 };
		} finally {
			if (rs != null) {
				rs.close();
			}
		}
	}
}
