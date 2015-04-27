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
	
	double anchorY;
	double anchorX;
	double anchorAngle;
	
	Translate translate;
	Rotate rotateX, rotateY, rotateZ;
	
	double centerX;
	double centerY;
        
	Image brick = new Image("/application/Brick.jpg");
	Image floorP = new Image("/application/Floor.jpg");
	Image doorPic = new Image("/application/Door.jpg");
	Image wallPic = new Image("/application/wallpaper.jpg");
	Image ceilingPic = new Image("/application/ceiling.jpg");
	
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
			camera.setTranslateZ(-350);
			camera.setTranslateX(100);
			//camera.setFieldOfView(35);
		    scene.setCamera(camera);
		    
		  //BOX
			Box box = new Box(100,100,100);
			Box box2 = new Box(100, 100, 100);
			Box box3 = new Box(50, 100, 500);
			Box box4 = new Box(50, 100, 500);
			Box floor = new Box(230, 1, 500);
			Box door = new Box (100, 100, 1);
			Box floor2 = new Box(500, 1, 230);
			Box backwall = new Box(500, 100, 50);
			Box floor3 = new Box(230, 1, 500);
			Box leftwall = new Box(50, 100, 500);
			Box leftcornerwall = new Box(200, 100, 200);
			Box leftfrontwall = new Box(230, 100, 100);
			Box ceiling = new Box(1000,1,1000);
			Box floor4 = new Box(230, 1, 500);
			Box rightcornerwall = new Box(200, 100, 200);
			Box rightfrontwall = new Box(230, 100, 100);
			Box rightwall = new Box(50, 100, 500);
			
			box.setTranslateX(centerX);
			box.setTranslateY(centerY);
			box.setTranslateZ(99);
			
			box2.setTranslateX(centerX + 200);
			box2.setTranslateY(centerY);
			box2.setTranslateZ(99);
			
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
			door.setTranslateZ(150);
			
			floor2.setTranslateX(centerX + 80);
			floor2.setTranslateY(centerY + 50);
			floor2.setTranslateZ(750);
			
			backwall.setTranslateX(centerX + 80);
			backwall.setTranslateY(centerY);
			backwall.setTranslateZ(880);
			
			floor3.setTranslateZ(390);
			floor3.setTranslateY(centerY + 49);
			floor3.setTranslateX(centerX - 170);
			
			leftwall.setTranslateX(centerX-300);
			leftwall.setTranslateZ(390);
			leftwall.setTranslateY(centerY);
			
			leftcornerwall.setTranslateZ(750);
			leftcornerwall.setTranslateX(centerX-250);
			leftcornerwall.setTranslateY(centerY);
			
			leftfrontwall.setTranslateZ(180);
			leftfrontwall.setTranslateX(centerX-170);
			
			ceiling.setTranslateY(centerY-50);
			ceiling.setTranslateX(centerX);
			ceiling.setTranslateZ(500);
			
			floor4.setTranslateY(centerY + 49);
			floor4.setTranslateZ(390);
			floor4.setTranslateX(centerX + 340);
			
			rightcornerwall.setTranslateZ(750);
			rightcornerwall.setTranslateX(centerX+425);
			rightcornerwall.setTranslateY(centerY);
			
			rightfrontwall.setTranslateZ(100);
			rightfrontwall.setTranslateX(centerX+340);
			
			rightwall.setTranslateX(centerX+480);
			rightwall.setTranslateZ(390);
			rightwall.setTranslateY(centerY);
			
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
	                	//box.getTransforms().add(new Rotate(-5, Rotate.Y_AXIS));
        				System.out.println("Key Pressed: " + ke.getCode());
	            	break;
	                // Camera rotation around Z Axis
	                case NUMPAD1:
	                	rotateZ = new Rotate(-1, Rotate.Z_AXIS);
	                	camera.getTransforms().add(rotateZ);
        				System.out.println("Key Pressed: " + ke.getCode() + rotateZ);
        			break;
        			
	                case NUMPAD3:
	                	rotateZ = new Rotate(1, Rotate.Z_AXIS);
	                	camera.getTransforms().add(rotateZ);
        				System.out.println("Key Pressed: " + ke.getCode() + rotateZ);
        			break;
        			// Camera rotation Y Axis
	                case A:
	                	rotateY = new Rotate(-3, Rotate.Y_AXIS);
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
			PhongMaterial wallMaterial = new PhongMaterial();
			PhongMaterial ceilingMaterial = new PhongMaterial();
			//phong.setDiffuseColor(Color.GREEN);
		    phong.setSpecularColor(Color.WHITE);
		    phong.setDiffuseMap(brick);
		    box.setMaterial(phong);
		    box2.setMaterial(phong);
		    
		    floorMaterial.setDiffuseMap(floorP);
		    floor.setMaterial(floorMaterial);
		    floor2.setMaterial(floorMaterial);
		    floor3.setMaterial(floorMaterial);
		    floor4.setMaterial(floorMaterial);
		    
		    doorMaterial.setDiffuseMap(doorPic);
		    door.setMaterial(doorMaterial);
		    
		    wallMaterial.setDiffuseMap(wallPic);
		    box3.setMaterial(wallMaterial);
		    box4.setMaterial(wallMaterial);
		    backwall.setMaterial(wallMaterial);
		    leftwall.setMaterial(wallMaterial);
		    leftcornerwall.setMaterial(wallMaterial);
		    leftfrontwall.setMaterial(wallMaterial);
		    rightcornerwall.setMaterial(wallMaterial);
		    rightfrontwall.setMaterial(wallMaterial);
		    rightwall.setMaterial(wallMaterial);
		    
		    ceilingMaterial.setDiffuseMap(ceilingPic);
		    ceiling.setMaterial(ceilingMaterial);
		   
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
			group.getChildren().addAll(box, box2, box3, box4,
					backwall, leftwall, rightwall, leftcornerwall, rightcornerwall,
					leftfrontwall, rightfrontwall, ceiling,
					floor, floor2, floor3, floor4, door, camera, ambLightRight);
			
		
			
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