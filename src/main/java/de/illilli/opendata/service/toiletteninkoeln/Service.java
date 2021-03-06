package de.illilli.opendata.service.toiletteninkoeln;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import de.illilli.opendata.service.Config;
import de.illilli.opendata.service.Facade;

@Path("/")
public class Service {

	private final static Logger logger = Logger.getLogger(Service.class);
	public static final String ENCODING = Config.getProperty("encoding");

	@Context
	private HttpServletRequest request;
	@Context
	private HttpServletResponse response;

	/**
	 * <p>
	 * Beispiel: <a href="http://localhost:8080/toiletteninkoeln/service/awb">
	 * /toiletteninkoeln/service/awb</a>
	 * </p>
	 *
	 * 
	 * <p>
	 * Beispiel:
	 * <a href="http://localhost:8080/toiletteninkoeln/service/awb?geojson">
	 * /toiletteninkoeln/service/awb?geojson</a>
	 * </p>
	 * 
	 * @return
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/awb")
	public String getAllToilettesJson() throws MalformedURLException, IOException {
		logger.info("get all awb toilettes locations");
		request.setCharacterEncoding(ENCODING);
		response.setCharacterEncoding(ENCODING);
		boolean geojson = request.getParameter("geojson") != null;
		Facade facade = null;
		if (geojson) {
			facade = new AllToilettesGeoJsonFacade();
		} else {
			facade = new AllToilettesJsonFacade();
		}
		return facade.getJson();
	}

}
