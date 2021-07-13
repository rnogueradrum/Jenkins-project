package com.example.demo.rest;

import java.util.List;

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

import org.springframework.stereotype.Component;

import com.example.demo.domain.ElectricCar;
import com.example.demo.domain.GasCar;
import com.example.demo.service.GasCarService;

@Path("/gascar")
	@Component
	@Produces(MediaType.APPLICATION_JSON) 
	@Consumes(MediaType.APPLICATION_JSON) 
	public class GasCarsController {

		private GasCarService gasCarService;

		public GasCarsController(GasCarService gasCarService) {
			this.gasCarService = gasCarService;
		}
		
		/**
		 * GET http://localhost:8080/api/gascar
		 */
	    @GET
	    public List<GasCar> findAll(){
	        return gasCarService.findAll();
	    }

	    /**
	     * GET
	     * http://localhost:8080/api/gascar/1
	     * http://localhost:8080/api/gascar/2
	     * http://localhost:8080/api/gascar/3
	     * http://localhost:8080/api/gascar/4
	     */
	    @GET
	    @Path("{id}")
	    public Response findOne(@PathParam("id") Long id){
	    	GasCar gasCar = gasCarService.findOne(id);
	        if (gasCar == null)
	            return Response.status(Response.Status.NOT_FOUND).build();

	        return Response.ok(gasCar).build();
	    }

	    /**
	     * POST http://localhost:8080/api/gascar
	     */
	    @POST // CREAR NUEVO
	    public Response create(GasCar gasCar){
	        
	        if (gasCar.getId() == null || gasCar.getId() == 0)  {
	        	GasCar result = gasCarService.save(gasCar);
	        	return Response.ok(result).build();
	        }
	         
	        return Response.status(Response.Status.BAD_REQUEST).build();
	    }

	    /**
	     * PUT http://localhost:8080/api/gascar
	     */
	    @PUT // ACTUALIZAR
	    public Response update(GasCar gasCar){
	        if (gasCar.getId() == null || gasCar.getId() == 0) 
	            return Response.status(Response.Status.BAD_REQUEST).build();
	        
	        GasCar result = gasCarService.save(gasCar);
	        return Response.ok(result).build();
	    }


	    /**
	     * DELETE
	     * http://localhost:8080/api/gascar/1
	     * http://localhost:8080/api/gascar/2
	     */
	    @DELETE
	    @Path("{id}")
	    public Response deleteOne(@PathParam("id") Long id){
	        if(!gasCarService.delete(id))
	        	return Response.status(Response.Status.NOT_FOUND).build();
	        
	        return Response.ok(Response.Status.OK).build();
	    }

	    /**
	     * DELETE
	     * http://localhost:8080/api/gascar
	     */
	    @DELETE
	    public Response deleteAll(){
	    	gasCarService.deleteAll();
	        return Response.ok(Response.Status.OK).build();
	    }
	    
	    @GET
	    @Path("color/{color}")
	    public Response findByColor(@PathParam("color") String color){
	    	System.out.println(color);
	    	return null;
//	    	List<GasCar> byColor = gasCarService.findByColor(color);
//	        if (byColor == null)
//	            return Response.status(Response.Status.NOT_FOUND).build();
//
//	        return Response.ok(byColor).build();
	    }

	   
	    
//	    @GET
//	    @Path("{brand}")
//	    public Response findByBrand(@PathParam("brand") String brand){
//	    	List<GasCar> byBrand = gasCarService.findByColor(brand);
//	        if (byBrand == null)
//	            return Response.status(Response.Status.NOT_FOUND).build();
//
//	        return Response.ok(byBrand).build();
//	    }
		

}
