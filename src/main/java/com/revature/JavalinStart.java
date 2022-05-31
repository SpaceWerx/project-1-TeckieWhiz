package com.revature;

import com.revature.controller.AuthController;
import com.revature.controller.ReimbursmentController;
import com.revature.controller.UserController;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

public class JavalinStart 
{
	 static Javalin app;
	 
	 AuthController authController = new AuthController();
	 ReimbursmentController reimController = new ReimbursmentController();
	 
	
	public JavalinStart() 
	{
		super();
		
		app = Javalin.create(config -> {
        	config.enableCorsForAllOrigins();
        	config.addStaticFiles("/public", Location.CLASSPATH);
        	config.addStaticFiles("/assets", Location.CLASSPATH);
        });
        
        app.exception(Exception.class, (e, ctx) -> {
           e.printStackTrace();
            ctx.status(500);
        });
        
        app.post("/login", authController.authenticateReq);
        app.post("/register", authController.registerReq);
        
        app.post("/login_service", authController.authenticateServ);
        app.post("/register_service", authController.registerServ);
        app.get("/activeUser_service", authController.activeUserServ);
        
                
        app.get("/users", UserController.fetchAllUsernames);
        app.get("/users/{id}", UserController.fetchById);
        
        
        app.get("/userReimbursments", reimController.fetchByAuthor);
        
        app.post("/submitReimbursment", reimController.addReimbursmentReq);
        app.post("/updateReimbursment", reimController.updateReimbursmentReq);
        
        app.get("/allReimbursments", reimController.getAll);
        app.post("/addReimbursment", reimController.addReimbursment);
        app.post("/approveReimbursment", reimController.updateReimbursment);
	}

	public static void main(String[] args) 
    {
		
		JavalinStart javalinApp = new JavalinStart();
		javalinApp.start(7777);
        
        
       
        
        
   }
	
   public void start(int port)
   {
	   this.app.start(port);
   }
}
