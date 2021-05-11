import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CarTrackingDataRead {

	static Connection con; //baðlantý oluþturuyoruz
	static String template = "<!DOCTYPE html>\r\n"
			+ "<html>\r\n"
			+ "  <head>\r\n"
			+ "    <title>Simple Polylines</title>\r\n"
			+ "    <script src=\"https://polyfill.io/v3/polyfill.min.js?features=default\"></script>\r\n"
			+ "    <style type=\"text/css\">\r\n"
			+ "      /* Always set the map height explicitly to define the size of the div\r\n"
			+ "       * element that contains the map. */\r\n"
			+ "      #map {\r\n"
			+ "        height: 100%;\r\n"
			+ "      }\r\n"
			+ "\r\n"
			+ "      /* Optional: Makes the sample page fill the window. */\r\n"
			+ "      html,\r\n"
			+ "      body {\r\n"
			+ "        height: 100%;\r\n"
			+ "        margin: 0;\r\n"
			+ "        padding: 0;\r\n"
			+ "      }\r\n"
			+ "    </style>\r\n"
			+ "    <script>\r\n"
			+ "      // This example creates a 2-pixel-wide red polyline showing the path of\r\n"
			+ "      // the first trans-Pacific flight between Oakland, CA, and Brisbane,\r\n"
			+ "      // Australia which was made by Charles Kingsford Smith.\r\n"
			+ "      function initMap() {\r\n"
			+ "        const map = new google.maps.Map(document.getElementById(\"map\"), {\r\n"
			+ "          zoom: 3,\r\n"
			+ "          center: { lat: 0, lng: -180 },\r\n"
			+ "          mapTypeId: \"terrain\",\r\n"
			+ "        });\r\n"
			+ "        const flightPlanCoordinates = [\r\n"
			+ "          {DYNAMICDATA}"
			+ "          \r\n"
			+ "        ];\r\n"
			+ "        const flightPath = new google.maps.Polyline({\r\n"
			+ "          path: flightPlanCoordinates,\r\n"
			+ "          geodesic: true,\r\n"
			+ "          strokeColor: \"#FF0000\",\r\n"
			+ "          strokeOpacity: 1.0,\r\n"
			+ "          strokeWeight: 2,\r\n"
			+ "        });\r\n"
			+ "        flightPath.setMap(map);\r\n"
			+ "      }\r\n"
			+ "    </script>\r\n"
			+ "  </head>\r\n"
			+ "  <body>\r\n"
			+ "    <div id=\"map\"></div>\r\n"
			+ "\r\n"
			+ "    <!-- Async script executes immediately and must be after any DOM elements used in callback. -->\r\n"
			+ "    <script\r\n"
			+ "      src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyDyqFfhOQNnXRDZILhHGdL2q1nGqWJtBVU&callback=initMap&libraries=&v=weekly\"\r\n"
			+ "	  \r\n"
			+ "      async\r\n"
			+ "    ></script>\r\n"
			+ "  </body>\r\n"
			+ "</html>";
	public static void main(String[] args) throws SQLException {
		
		  try{
	         Class.forName("com.mysql.jdbc.Driver");  //baðlanýyoruz
	         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/car_traking","root","");  
		  }
		  catch(Exception e){
			  System.out.println(e);
		  }
		
		  StringBuilder sb = new StringBuilder();
		  
	    Statement stmt=con.createStatement(); //sorgu oluþuyorum 
		String sorgu2="SELECT `id`, `enlem`, `boylam`, `saat`, `plaka` FROM `car` WHERE 1";
        ResultSet secme=stmt.executeQuery(sorgu2);
        while(secme.next()){
        	 Integer id= (Integer)secme.getInt(1);
             Double enlem1=(Double)secme.getDouble(2);
             Double boylam1=(Double)secme.getDouble(3);
             java.sql.Timestamp date1=(java.sql.Timestamp)secme.getTimestamp(4);
             String plaka=(String) secme.getString(5);
             
             // { lat: 38.734435, lng: 35.495064 },
             sb.append("{ lat: " +enlem1 + ", lng: "+ boylam1 + "},");
    	}
        
        template = template.replace("{DYNAMICDATA}", sb.toString());
        FileUtils.write(template);
	}
}
