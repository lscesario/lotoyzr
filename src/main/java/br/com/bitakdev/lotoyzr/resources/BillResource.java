package br.com.bitakdev.lotoyzr.resources;


import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.bitakdev.lotoyzr.conf.Constants;
import br.com.bitakdev.lotoyzr.models.Bill;
import br.com.bitakdev.lotoyzr.operations.BillControl;

@Path("/bills")
public class BillResource {
	
		
	@Inject
	BillControl bc;
	
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/create")
	public Response createBill(Bill bill){
		if (bill == null) throw new BadRequestException();
		bc.createBill(bill);
		String result="Bill created: "+bill;
		return Response.status(201).entity(result).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{bill_id}")
	public Response loadBillById(@PathParam("bill_id")int bill_id){
		if(bill_id==Constants.VOCE_NAO_VALE_NADA){
			System.out.println("Bill id = 0");
			throw new BadRequestException();
		}
		return Response.status(200).entity(bc.loadBillById(bill_id)).build();
	  }
	
	@GET
	public String getVersion(){
		return "Tests v1";
	}
	
	@DELETE
	@Path("remove/{bill_id}")
	public Response removeBillById(@PathParam("bill_id")int bill_id){
		if(bill_id==Constants.VOCE_NAO_VALE_NADA){
			System.out.println("Bill id = 0");
			throw new BadRequestException();
		}
		bc.removeBillById(bill_id);
		String result="Bill Id deleted: "+bill_id;
		return Response.status(200).entity(result).build();
	}

	@PUT
	@Path("update/{bill_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateBillById(@PathParam("bill_id")int bill_id, Bill bill){
		bc.updateBillById(bill_id, bill);
		String result="Bill Id altered: "+bill;
		return Response.status(200).entity(result).build();
	}
	
	@PUT
	@Path("associate/{bill_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response associateBillResponsible(@PathParam("bill_id") int bill_id,
											 @HeaderParam("member_id") int member_id,
											 @HeaderParam("house_id") int house_id,
											 @HeaderParam("token") String JWT){
		return bc.associateBillResponsible(bill_id, house_id, member_id, JWT);
	}
	
	
}


