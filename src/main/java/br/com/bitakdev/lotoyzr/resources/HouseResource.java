package br.com.bitakdev.lotoyzr.resources;


import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bitakdev.lotoyzr.models.House;
import br.com.bitakdev.lotoyzr.operations.HouseControl;

@Path("/house")
public class HouseResource {
	
	@Inject
	HouseControl hc;
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response createHouse(House house){
		if (house == null) throw new BadRequestException();
		hc.createHouse(house);
		String result="House created: "+house;
		return Response.status(201).entity(result).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{house_id}")
	public Response loadHouseById(@PathParam("house_id")int house_id){
		if(house_id==0){
			System.out.println("House id = 0");
			throw new BadRequestException();
		}
		return Response.status(200).entity(hc.loadHouseById(house_id)).build();
	  }
	
	@DELETE
	@Path("remove/{house_id}")
	public Response removeHouseById(@PathParam("house_id")int house_id){
		if(house_id==0){
			System.out.println("House id = 0");
			throw new BadRequestException();
		}
		hc.removeHouseById(house_id);
		String result="House Id deleted: "+house_id;
		return Response.status(200).entity(result).build();
	}

	@PUT
	@Path("update/{house_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateHouseById(@PathParam("house_id")int house_id, House house){
		hc.updateHouseById(house_id, house);
		String result="House Id altered: "+house;
		return Response.status(200).entity(result).build();
	}
	
}


