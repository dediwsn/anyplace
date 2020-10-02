/*
* Anyplace: A free and open Indoor Navigation Service with superb accuracy!
*
* Anyplace is a first-of-a-kind indoor information service offering GPS-less
* localization, navigation and search inside buildings using ordinary smartphones.
*
* Author(s): Timotheos Constambeys, Lambros Petrou
* 
* Supervisor: Demetrios Zeinalipour-Yazti
*
* URL: http://anyplace.cs.ucy.ac.cy
* Contact: anyplace@cs.ucy.ac.cy
*
* Copyright (c) 2015, Data Management Systems Lab (DMSL), University of Cyprus.
* All rights reserved.
*
* Permission is hereby granted, free of charge, to any person obtaining a copy of
* this software and associated documentation files (the "Software"), to deal in the
* Software without restriction, including without limitation the rights to use, copy,
* modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
* and to permit persons to whom the Software is furnished to do so, subject to the
* following conditions:
*
* The above copyright notice and this permission notice shall be included in all
* copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
* OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
* FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
* DEALINGS IN THE SOFTWARE.
*
*/

//TODO Replace with Anyplace from library! Move the api calls to the library

package cy.ac.ucy.cs.anyplace.lib.android;

//TODO: To be replaced with the Anyplace java file from lib core .. Done for logger

import android.content.Context;
import android.content.SharedPreferences;

import java.io.File;

public class AnyplaceAPI {



	public final static Boolean FLOOR_SELECTOR = true;

	// Lock Location to GPS
	public final static Boolean LOCK_TO_GPS = false;
	// Show Debug Messages
	public final static Boolean DEBUG_MESSAGES = true;
	// Wifi and GPS Data
	public final static Boolean DEBUG_WIFI = false;
	// API URLS
	public final static Boolean DEBUG_URL = false;

	// Load All Building's Floors and Radiomaps
	public final static Boolean PLAY_STORE = true;

	// private static String server ="http://thinklambros.in.cs.ucy.ac.cy:9000";
	// private static String server ="http://anyplace.in.cs.ucy.ac.cy";
	// private static String server = "https://anyplace.rayzit.com";

	private static String getServerIPAddress(Context ctx){


		if (!DEBUG_URL) {
			Context c = ctx;
			SharedPreferences preferences = c.getSharedPreferences("Anyplace_Preferences", c.MODE_PRIVATE);
			return preferences.getString("server_ip_address", "ap-dev.cs.ucy.ac.cy" ).trim();
		} else {
			return "http://192.168.1.2:9000";
		}
	}

	private final static String PREDICT_FLOOR_ALGO1 = "/anyplace/position/predictFloorAlgo1";
	private final static String PREDICT_FLOOR_ALGO2 = "/anyplace/position/predictFloorAlgo2";

	private final static String RADIO_DOWNLOAD_XY = "/anyplace/position/radio_download_floor";
	private final static String RADIO_DOWNLOAD_BUID = "/anyplace/position/radio_by_building_floor";
	private final static String RADIO_UPLOAD_URL_API = "/anyplace/position/radio_upload";

	private final static String NAV_ROUTE_URL_API = "/anyplace/navigation/route";
	private final static String NAV_ROUTE_XY_URL_API = "/anyplace/navigation/route_xy";

	private final static String FLOOR_PLAN_DOWNLOAD = "/anyplace/floorplans";
	private final static String FLOOR_TILES_ZIP_DOWNLOAD = "/anyplace/floortiles";

	public static String predictFloorAlgo1(Context ctx) {
		return getServerIPAddress(ctx) + PREDICT_FLOOR_ALGO1;
	}

	public static String predictFloorAlgo2(Context ctx) {
		return getServerIPAddress(ctx) + PREDICT_FLOOR_ALGO2;
	}

	public static String getRadioDownloadBuid(Context ctx) {   //deprecated
		return getServerIPAddress(ctx) + RADIO_DOWNLOAD_BUID;
	}

	public static String getRadioDownloadXY(Context ctx) {
		return getServerIPAddress(ctx) + RADIO_DOWNLOAD_XY;
	}

	public static String getRadioUploadUrl(Context ctx) {  //deprecated
		return getServerIPAddress(ctx) + RADIO_UPLOAD_URL_API;
	}

	private static String getNavRouteUrl(Context ctx) {
		return getServerIPAddress(ctx) + NAV_ROUTE_URL_API;
	}

	public static String getNavRouteXYUrl(Context ctx) {
		return getServerIPAddress(ctx) + NAV_ROUTE_XY_URL_API;
	}

	// --------------Select Building Activity--------------------------

	public static String getFetchBuildingsUrl(Context ctx) {     // Deprecated
		return getServerIPAddress(ctx) + "/anyplace/mapping/building/all";
	}

	public static String getFetchBuildingsByBuidUrl(Context ctx) {      //  This call is wrong and deprecated now
		return getServerIPAddress(ctx) + "/anyplace/navigation/building/id";
	}

	public static String getFetchFloorsByBuidUrl(Context ctx) {     // deprecated
		return getServerIPAddress(ctx) + "/anyplace/mapping/floor/all";
	}

	public static String getServeFloorTilesZipUrl(String buid, String floor_number,Context ctx) {   // Replace but needs access token
		return getServerIPAddress(ctx) + FLOOR_TILES_ZIP_DOWNLOAD + File.separatorChar + buid + File.separatorChar + floor_number;
	}

	// -------------- Near coordinates ----------------------------------

	private static String getFetchBuildingsCoordinatesUrl(Context ctx) {
		return getServerIPAddress(ctx) + "/anyplace/mapping/building/coordinates";
	}

	private static String getServeFloorPlanUrl(String buid, String floor_number, Context ctx) {
		return getServerIPAddress(ctx) + FLOOR_PLAN_DOWNLOAD + File.separatorChar + buid + File.separatorChar + floor_number;
	}

	// ----------------------------------------------------------------

	// --------------POIS Api--------------------------

	public static String getFetchPoisByBuidUrl(Context ctx) { //deprecated
		return getServerIPAddress(ctx) + "/anyplace/mapping/pois/all_building";
	}

	public static String getFetchPoisByBuidFloorUrl(Context ctx) { //deprecated
		return getServerIPAddress(ctx) + "/anyplace/mapping/pois/all_floor";
	}

	public static String getFetchPoisByPuidUrl(Context ctx) { //deprecated
		return getServerIPAddress(ctx) + "/anyplace/navigation/pois/id";
	}

	// ------------------------------------------------

}
