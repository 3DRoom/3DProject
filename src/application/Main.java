package application;
	

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	/*
	 * 
	 * This is a committed version of my code
	 * Trying to fix latches error with ANTs
	 * 
	 */
	double anchorY;
	double anchorX;
	double anchorAngle;
	
	Translate translate;
	Rotate rotateX, rotateY, rotateZ;
	
	double centerX;
	double centerY;
	double centerZ;
        
	Image brick = new Image("/application/Brick.jpg");
	Image floorP = new Image("/application/Floor.jpg");
	Image doorPic = new Image("/application/Door.jpg");
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Group group = new Group();
			Scene scene = new Scene(group,1000,700, true);
			scene.setFill(Color.GRAY);
			primaryStage.setTitle("3D Environment");
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			
			//Camera
			PerspectiveCamera camera = new PerspectiveCamera(true);
//		    camera.getTransforms().addAll(
//	               rotateY = new Rotate(25, Rotate.Y_AXIS),
//	               rotateX = new Rotate(-15, Rotate.X_AXIS),
//	               rotateZ = new Rotate(10, Rotate.Z_AXIS),
//	               translate = new Translate(centerX, centerY, -1000)
//	        );
			
		    camera.setNearClip(0.1);
			camera.setFarClip(2000.0);
			
			//camera.setFieldOfView(35);
		    scene.setCamera(camera);
		    
		  //BOX
			Box box = new Box(100,100,100);
			Box box2 = new Box(100, 100, 100);
			Box box3 = new Box(50, 100, 500);
			Box box4 = new Box(50, 100, 500);
			Box floor = new Box(230, 1, 500);
			Box door = new Box (100, 100, 1);
			
			box.setTranslateX(centerX);
			box.setTranslateY(centerY);
			
			box2.setTranslateX(centerX + 200);
			box2.setTranslateY(centerY);
			
			box3.setTranslateZ(400);
			box3.setTranslateX(centerX - 30);
			box3.setTranslateY(centerY);
			
			box4.setTranslateZ(400);
			box4.setTranslateX(centerX + 200);
			box4.setTranslateY(centerY);
			
			floor.setTranslateX(centerX + 80);
			floor.setTranslateY(centerY + 50);
			floor.setTranslateZ(400);
			
			door.setTranslateX(centerX + 100);
			door.setTranslateY(centerY);
			
			//box.setTranslateZ(100);
		    
		    scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
	            @Override
	            public void handle(KeyEvent ke) {
	                KeyCode key = ke.getCode();
	                switch (key) {
	                //ZOOM
	                case S:   
	                	camera.getTransforms().addAll(
	         	               translate = new Translate(centerX, centerY, -10)
	         	        );
	            				System.out.println("Key Pressed: " + ke.getCode() + translate.toString());
	            	break;
	            	
	                case W:    
	                	camera.getTransforms().addAll(
		         	               translate = new Translate(centerX, centerY, 10)
		         	        );
	                	System.out.println("Key Pressed: " + ke.getCode() + translate.toString());
                	
					break;
					// Rotation around Y Axis
	                case LEFT:
	                	door.getTransforms().add(new Rotate(5, Rotate.Y_AXIS));
        				System.out.println("Key Pressed: " + ke.getCode());
	            	break;
	            	
	                case RIGHT: 
	                	box.getTransforms().add(new Rotate(-5, Rotate.Y_AXIS));
        				System.out.println("Key Pressed: " + ke.getCode());
	            	break;
	            	// Strafing Left
	                case Q:
	                	camera.getTransforms().addAll(
	                				translate = new Translate(-10, centerY, centerZ)
	                			);
	                		System.out.println("Key Presed: " + ke.getCode() + translate.toString());
	            	break;
	            	
	            	// Strafing Right
	                case E:
	                	camera.getTransforms().addAll(
	                				translate = new Translate(10, centerY, centerZ)
	                			);
	                		System.out.println("Key Presed: " + ke.getCode() + translate.toString());
	            	break;
	            	
	            	
	                // Camera rotation around Z Axis
	                case NUMPAD1:
	                	rotateZ = new Rotate(-1, Rotate.Z_AXIS);
	                	camera.getTransforms().add(rotateZ);
        				System.out.println("Key Pressed: " + ke.getCode() + rotateZ);
        			break;
        			
	                case NUMPAD3:
	                	rotateZ = new Rotate(2, Rotate.Z_AXIS);
	                	camera.getTransforms().add(rotateZ);
        				System.out.println("Key Pressed: " + ke.getCode() + rotateZ);
        			break;
        			// Camera rotation Y Axis
	                case A:
	                	rotateY = new Rotate(-2, Rotate.Y_AXIS);
	                	camera.getTransforms().add(rotateY);
        				System.out.println("Key Pressed: " + ke.getCode() + rotateY);
        			break;
        			
	                case D:
	                	rotateY = new Rotate(3, Rotate.Y_AXIS);
	                	camera.getTransforms().add(rotateY);
        				System.out.println("Key Pressed: " + ke.getCode() + rotateY);
        			break;
        			// Rotate around X Axis
	                case UP:
	                	box.getTransforms().add(new Rotate(-5, Rotate.X_AXIS));
	                	System.out.println("Key Pressed: " + ke.getCode());
        			break;
        			
	                case DOWN:
	                	box.getTransforms().add(new Rotate(5, Rotate.X_AXIS));
	                	System.out.println("Key Pressed: " + ke.getCode());
        			break;
        			
	            	// Exit the Application	
	                case ESCAPE:	primaryStage.close();
					default:
						break;
	                }
	               }
	        });
		    
			
			
			//Color and phong material of the box
			PhongMaterial phong = new PhongMaterial();
			PhongMaterial floorMaterial = new PhongMaterial();
			PhongMaterial doorMaterial = new PhongMaterial();
			//phong.setDiffuseColor(Color.GREEN);
		    phong.setSpecularColor(Color.WHITE);
		    phong.setDiffuseMap(brick);
		    box.setMaterial(phong);
		    box2.setMaterial(phong);
		    
		    floorMaterial.setDiffuseMap(floorP);
		    floor.setMaterial(floorMaterial);
		    
		    doorMaterial.setDiffuseMap(doorPic);
		    door.setMaterial(doorMaterial);
		    
		    
		    //Light
//		    PointLight pointLight = new PointLight(Color.WHITE);
//		    pointLight.setTranslateX(centerX);
//		    pointLight.setTranslateY(centerY);
//		    pointLight.setTranslateZ(-200);
//		    
//		    PointLight pointLightback = new PointLight(Color.WHITE);
//		    pointLightback.setTranslateX(centerX);
//		    pointLightback.setTranslateY(centerY);
//		    pointLightback.setTranslateZ(400);
//		    
//		    PointLight pointLightLeft = new PointLight(Color.BLUE);
//		    pointLightLeft.setTranslateX(centerX-500);
//		    pointLightLeft.setTranslateY(centerY);
//		    //pointLightLeft.setTranslateZ(-200);
		    
		    AmbientLight ambLightRight = new AmbientLight(Color.WHITE);
		    //ambLightRight.setTranslateX(centerX + 500);
		    //ambLightRight.setTranslateY(centerY);
		    //pointLightLeft.setTranslateZ(-100);
		    
		    
		    
			//adding stuff to the group
			group.getChildren().addAll(box, box2, box3, box4, floor, door, camera, ambLightRight);
			
		
			
			// Showing the stage
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}