package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Line;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {

	double anchorY;
	double anchorX;
	double anchorAngle;

	Translate translate;
	Rotate rotateX, rotateY, rotateZ;
	Timeline timeline, timeline2, timeline3;

	PerspectiveCamera camera = new PerspectiveCamera(true);
	
	boolean isPressed, isPressedA;

	double centerX;
	double centerY;
	double centerZ;

	// Variables for the earth sphere
	double MAP_WIDTH = 8192 / 2d;
	double MAP_HEIGHT = 4092 / 2d;

	Image brick = new Image("/application/resources/Brick.jpg");
	Image floorP = new Image("/application/resources/Floor.jpg");
	Image doorPic = new Image("/application/resources/Door.jpg");
	Image wallPic = new Image("/application/resources/wallpaper.jpg");
	Image ceilingPic = new Image("/application/resources/ceiling.jpg");
	Image soccer = new Image("/application/resources/soccer1.jpg");
	Image soccerSpec = new Image("/application/resources/soccerSpecMap.jpg");
	Image cylinderPic = new Image("/application/resources/cylinder.jpg");
	
	Image hedgePic = new Image("/application/resources/hedge.jpg");
	Image tablePic = new Image("/application/resources/tablePic.jpg");
	Image couchPic = new Image("/application/resources/couchPic.jpg");
	Image cushionPic = new Image("/application/resources/cushionPic.jpg");
	Image pavementPic = new Image("/application/resources/pavementPic.jpg");
	Image chairPic = new Image("/application/resources/chairPic.jpg");
	Image rugPic = new Image("/application/resources/rugPic.jpg");
	Image mirrorPic = new Image("/application/resources/mirrorPic.jpg");
	Image mahoganyPic = new Image("/application/resources/mahoganyPic.jpg");
	Image marblePic = new Image("/application/resources/marblePic.jpg");
	Image grassPic = new Image("/application/resources/grassPic.jpg");
	
	String earthDiff = "/application/resources/EarthDiff.jpg";
	String earthSpec = "/application/resources/EarthSpec.jpg";
	String earthBump = "/application/resources/EarthNorm.jpg";

	@Override
	public void start(Stage primaryStage) {

		try {
			Group group = new Group();
			Scene scene = new Scene(group, 1000, 700, true);
			scene.setFill(Color.GRAY);
			primaryStage.setTitle("3D Environment");
			primaryStage.setScene(scene);
			primaryStage.initStyle(StageStyle.UNDECORATED);

			// Camera
			
			// camera.getTransforms().addAll(
			// rotateY = new Rotate(25, Rotate.Y_AXIS),
			// rotateX = new Rotate(-15, Rotate.X_AXIS),
			// rotateZ = new Rotate(10, Rotate.Z_AXIS),
			// translate = new Translate(centerX, centerY, -1000)
			// );

			camera.setNearClip(0.1);
			camera.setFarClip(2000.0);
			// Camera traslation for starting point
			camera.setTranslateZ(-350);
			camera.setTranslateX(100);
			
			scene.setCamera(camera);

			// BOX
			Box box = new Box(100, 100, 100);
			Box box2 = new Box(100, 100, 100);
			Box box3 = new Box(50, 100, 500);
			Box box4 = new Box(50, 100, 500);
			Box floor = new Box(230, 1, 500);
			Box door = new Box(100, 100, 1);
			Box floor2 = new Box(500, 1, 230);
			Box backwall = new Box(500, 100, 50);
			Box floor3 = new Box(230, 1, 500);
			Box leftwall = new Box(50, 100, 500);
			Box leftcornerwall = new Box(200, 100, 200);
			Box leftfrontwall = new Box(230, 100, 100);
			Box ceiling = new Box(1000, 1, 1000);
			Box floor4 = new Box(230, 1, 500);
			Box rightcornerwall = new Box(200, 100, 200);
			Box rightfrontwall = new Box(230, 100, 100);
			Box rightwall = new Box(50, 100, 500);
			Sphere soccerBall = new Sphere(10);
			Sphere earth = new Sphere(30);
			Cylinder cylinder = new Cylinder(20, 10);
			//Line intersecting the globe
			Line lineEarth = new Line(centerX - 210, centerY+50, centerX - 210, centerY-50);
			//Hannah
			Box couchBox1 = new Box(10, 30, 90);
			Box couchBox2 = new Box(40, 20, 90);
			Cylinder couchCylinder1 = new Cylinder(5, 30);
			Cylinder couchCylinder2 = new Cylinder(5, 30);
			Box cushion1 = new Box(3,20,20);
			Box cushion2 = new Box(3,20,20);
			Box mirror = new Box(1, 40, 30);
			Cylinder hallTableLeg1 = new Cylinder(1.5, 30);
			Cylinder hallTableLeg2 = new Cylinder(1.5, 30);
			Cylinder hallTableLeg3 = new Cylinder(1.5, 30);
			Cylinder hallTableLeg4 = new Cylinder(1.5, 30);
			Box hallTableTop1 = new Box(25, 5, 50);
			Box hallTableTop2 = new Box(29, 2, 54);
			Box outerRightFrontWall = new Box(230, 100, 0.1);
			Box outerLeftFrontWall = new Box(230, 100, 0.1);				
			Box hedge1 = new Box(100, 30, 30);
			Box hedge2 = new Box(100, 30, 30);
			Box hedge3 = new Box(100, 30, 30);
			Box hedge4 = new Box(160, 30, 30);
			Cylinder tableLeg1 = new Cylinder(2, 30);
			Cylinder tableLeg2 = new Cylinder(2, 30);
			Cylinder tableLeg3 = new Cylinder(2, 30);
			Cylinder tableLeg4 = new Cylinder(2, 30);
			Box tableTop = new Box(60, 3, 30);
			Cylinder chairLeg1 = new Cylinder(1.5, 20);
			Cylinder chairLeg2 = new Cylinder(1.5, 20);
			Cylinder chairLeg3 = new Cylinder(1.5, 20);
			Cylinder chairLeg4 = new Cylinder(1.5, 20);
			Box chairTop = new Box(20, 2, 20);
			Box chairTop4 = new Box(2,25,20);
			Cylinder chairLeg21 = new Cylinder(1.5, 20);
			Cylinder chairLeg22 = new Cylinder(1.5, 20);
			Cylinder chairLeg23 = new Cylinder(1.5, 20);
			Cylinder chairLeg24 = new Cylinder(1.5, 20);
			Box chairTop2 = new Box(20, 2, 20);
			Box chairTop24 = new Box(2,25,20);
			Box path = new Box(100,1,300);
			Box rug = new Box(100, 0.1, 200);
			Box grass1 = new Box(260,1,240);
			Box grass2 = new Box(260, 1, 260);
//	Hannah
			
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

			leftwall.setTranslateX(centerX - 300);
			leftwall.setTranslateZ(390);
			leftwall.setTranslateY(centerY);

			leftcornerwall.setTranslateZ(750);
			leftcornerwall.setTranslateX(centerX - 250);
			leftcornerwall.setTranslateY(centerY);

			leftfrontwall.setTranslateZ(180);
			leftfrontwall.setTranslateX(centerX - 170);

			ceiling.setTranslateY(centerY - 50);
			ceiling.setTranslateX(centerX);
			ceiling.setTranslateZ(500);

			floor4.setTranslateY(centerY + 49);
			floor4.setTranslateZ(390);
			floor4.setTranslateX(centerX + 340);

			rightcornerwall.setTranslateZ(750);
			rightcornerwall.setTranslateX(centerX + 425);
			rightcornerwall.setTranslateY(centerY);

			rightfrontwall.setTranslateZ(100);
			rightfrontwall.setTranslateX(centerX + 340);

			rightwall.setTranslateX(centerX + 480);
			rightwall.setTranslateZ(390);
			rightwall.setTranslateY(centerY);
			// SoccerBall in the left room
			soccerBall.setTranslateX(centerX - 100);
			soccerBall.setTranslateY(centerY + 28);
			soccerBall.setTranslateZ(280);
			soccerBall.getTransforms().add(new Rotate(-15, Rotate.Y_AXIS));
			//Globe in the left room
			earth.setTranslateX(centerX - 210);
			earth.setTranslateY(centerY);
			earth.setTranslateZ(280);
			//Line intersectiong the globe
			lineEarth.setTranslateZ(280);
			
			cylinder.setTranslateX(centerX-100);
			cylinder.setTranslateY(centerY+44);
			cylinder.setTranslateZ(280);
			
			//Hannah
			hedge1.setTranslateX(centerX);
			hedge1.setTranslateY(centerY+40);
			hedge1.setTranslateZ(30);
			
			hedge2.setTranslateX(centerX+300);	
			hedge2.setTranslateY(centerY+40);
			hedge2.setTranslateZ(30);		
			
			hedge3.setTranslateX(centerX+200);		
			hedge3.setTranslateY(centerY+40);
			hedge3.setTranslateZ(30);

			hedge4.setTranslateX(centerX-130);
			hedge4.setTranslateY(centerY+40);
			hedge4.setTranslateZ(110);
			
			tableLeg1.setTranslateY(centerY+40);
			tableLeg1.setTranslateZ(800);
			tableLeg2.setTranslateY(centerY+40);
			tableLeg2.setTranslateZ(780);
			tableLeg3.setTranslateY(centerY+40);
			tableLeg3.setTranslateX(centerX+40);
			tableLeg3.setTranslateZ(800);
			tableLeg4.setTranslateX(centerX+40);
			tableLeg4.setTranslateY(centerY+40);
			tableLeg4.setTranslateZ(780);

			chairLeg1.setTranslateX(centerX+55);
			chairLeg1.setTranslateY(centerY+45);
			chairLeg1.setTranslateZ(797);
			chairLeg2.setTranslateX(centerX+55);
			chairLeg2.setTranslateY(centerY+45);
			chairLeg2.setTranslateZ(783);
			chairLeg3.setTranslateX(centerX+70);
			chairLeg3.setTranslateY(centerY+45);
			chairLeg3.setTranslateZ(797);
			chairLeg4.setTranslateX(centerX+70);
			chairLeg4.setTranslateY(centerY+45);
			chairLeg4.setTranslateZ(783);
			
			chairTop.setTranslateX(centerX+62);
			chairTop.setTranslateY(centerY+35);
			chairTop.setTranslateZ(790);
			chairTop4.setTranslateX(centerX+72);
			chairTop4.setTranslateY(centerY+23);
			chairTop4.setTranslateZ(790);
			chairTop4.getTransforms().add(new Rotate(10,0,0));
			
			chairLeg21.setTranslateX(centerX-30);
			chairLeg21.setTranslateY(centerY+45);
			chairLeg21.setTranslateZ(797);
			chairLeg22.setTranslateX(centerX-30);
			chairLeg22.setTranslateY(centerY+45);
			chairLeg22.setTranslateZ(783);
			chairLeg23.setTranslateX(centerX-15);
			chairLeg23.setTranslateY(centerY+45);
			chairLeg23.setTranslateZ(797);
			chairLeg24.setTranslateX(centerX-15);
			chairLeg24.setTranslateY(centerY+45);
			chairLeg24.setTranslateZ(783);
			
			chairTop2.setTranslateX(centerX-23);
			chairTop2.setTranslateY(centerY+35);
			chairTop2.setTranslateZ(790);
			chairTop24.setTranslateX(centerX-33);
			chairTop24.setTranslateY(centerY+23);
			chairTop24.setTranslateZ(790);
			chairTop24.getTransforms().add(new Rotate(-10,0,0));
			
			tableTop.setTranslateX(centerX+20);
			tableTop.setTranslateY(centerY+24);
			tableTop.setTranslateZ(790);
			
			couchBox1.setTranslateX(centerX-266);
			couchBox1.setTranslateY(centerY+15);
			couchBox1.setTranslateZ(435);
			couchBox1.getTransforms().add(new Rotate(-10,0,0));
			couchBox2.setTranslateX(centerX-250);
			couchBox2.setTranslateY(centerY+40);
			couchBox2.setTranslateZ(435);
			
			couchCylinder1.setTranslateX(centerX-245);
			couchCylinder1.setTranslateY(centerY+25);
			couchCylinder1.setTranslateZ(395);
			couchCylinder1.getTransforms().add(new Rotate(90,0,0));
			couchCylinder2.setTranslateX(centerX-245);
			couchCylinder2.setTranslateY(centerY+25);
			couchCylinder2.setTranslateZ(475);
			couchCylinder2.getTransforms().add(new Rotate(90,0,0));
			
			cushion1.setTranslateX(centerX-257);
			cushion1.setTranslateY(centerY+22);
			cushion1.setTranslateZ(415);
			cushion1.getTransforms().add(new Rotate(-10,0,0));

			cushion2.setTranslateX(centerX-257);
			cushion2.setTranslateY(centerY+22);
			cushion2.setTranslateZ(455);
			cushion2.getTransforms().add(new Rotate(-10,0,0));
						
			path.setTranslateX(centerX + 100);
			path.setTranslateY(centerY+50);
			path.setTranslateZ(-5);
			grass1.setTranslateX(centerX+280);
			grass1.setTranslateY(centerY+50);
			grass1.setTranslateZ(-35);
			grass2.setTranslateX(centerX-80);
			grass2.setTranslateY(centerY+50);
			grass2.setTranslateZ(-20);
			
			rug.setTranslateX(centerX + 350);
			rug.setTranslateY(centerY + 45);
			rug.setTranslateZ(350);
			
			mirror.setTranslateY(centerY-5);
			mirror.setTranslateZ(335);
			mirror.getTransforms().add(new Rotate(-10,0,0));
			
			hallTableLeg1.setTranslateX(centerX+23);
			hallTableLeg1.setTranslateY(centerY+35);
			hallTableLeg1.setTranslateZ(312);
			hallTableLeg2.setTranslateX(centerX+2);
			hallTableLeg2.setTranslateY(centerY+35);
			hallTableLeg2.setTranslateZ(312);
			hallTableLeg3.setTranslateX(centerX+23);
			hallTableLeg3.setTranslateY(centerY+35);
			hallTableLeg3.setTranslateZ(358);
			hallTableLeg4.setTranslateX(centerX+2);
			hallTableLeg4.setTranslateY(centerY+35);
			hallTableLeg4.setTranslateZ(358);			
			hallTableTop1.setTranslateX(centerX+12);
			hallTableTop1.setTranslateY(centerY+20);
			hallTableTop1.setTranslateZ(335);
			hallTableTop2.setTranslateX(centerX+13);
			hallTableTop2.setTranslateY(centerY+16);
			hallTableTop2.setTranslateZ(335);
			
			outerLeftFrontWall.setTranslateZ(129);
			outerLeftFrontWall.setTranslateX(centerX-170);
			
			outerRightFrontWall.setTranslateX(centerX+340);
			outerRightFrontWall.setTranslateZ(49);
			//Hannah

			
			
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent ke) {
					KeyCode key = ke.getCode();
					switch (key) {
					// ZOOM
					case S:
						camera.getTransforms()
								.addAll(translate = new Translate(centerX,
										centerY, -10));
						break;

					case W:
						camera.getTransforms()
						.addAll(translate = new Translate(centerX,
								centerY, 10));
						break;
					// Rotation around Y Axis
					case LEFT:
						// door.getTransforms().add(new Rotate(5, centerX+50, 0,
						// 0, Rotate.Y_AXIS));
						break;

					case RIGHT:
						 soccerBall.getTransforms().add(new Rotate(-5,
						 Rotate.Y_AXIS));
						break;
					// Camera rotation around Z Axis
					case NUMPAD1:
						rotateZ = new Rotate(-1, Rotate.Z_AXIS);
						camera.getTransforms().add(rotateZ);
						break;

					case NUMPAD3:
						rotateZ = new Rotate(1, Rotate.Z_AXIS);
						camera.getTransforms().add(rotateZ);
						break;
					// Camera rotation Y Axis
					case A:
						rotateY = new Rotate(-1.4, Rotate.Y_AXIS);
						camera.getTransforms().add(rotateY);
						break;

					case D:
						rotateY = new Rotate(1.4, Rotate.Y_AXIS);
						camera.getTransforms().add(rotateY);
						break;
					// Rotate around X Axis
					case UP:
						box.getTransforms().add(new Rotate(-5, Rotate.X_AXIS));
						break;

					case DOWN:
						box.getTransforms().add(new Rotate(5, Rotate.X_AXIS));
						break;
						//opens the door
					case P:
					    timeline = new Timeline(new KeyFrame(Duration
								.millis(50), ae -> door.getTransforms()
								.add(new Rotate(1, centerX + 50, 0, 0,
										Rotate.Y_AXIS))));
						timeline.setCycleCount(88);
						timeline.play();
						break;
						//starts spinning the globe indefinitely
					case O:
						timeline = new Timeline(new KeyFrame(Duration
								.millis(20), ae -> earth.getTransforms()
								.addAll(new Rotate(1, Rotate.Y_AXIS))));
						timeline.setCycleCount(Animation.INDEFINITE);
						timeline.play();
						break;
						//shoots the ball once
					case I:
						timeline = new Timeline(new KeyFrame(Duration
								.millis(50), ae -> cylinder.setTranslateY(cylinder.getTranslateY()+1)),
								new KeyFrame(Duration
								.millis(50), ae ->soccerBall.setTranslateY(soccerBall.getTranslateY()+1)));
						timeline.setCycleCount(9);
						timeline.play();
						timeline2 = new Timeline(new KeyFrame(Duration
								.millis(20), ae -> cylinder.setTranslateY(cylinder.getTranslateY()-1)),
								new KeyFrame(Duration
								.millis(46), ae ->soccerBall.setTranslateY(soccerBall.getTranslateY()-5)));
						timeline2.setCycleCount(9);
						timeline2.setDelay(Duration.millis(400));
						timeline2.play();
						timeline3 = new Timeline(
								new KeyFrame(Duration
								.millis(50), ae ->soccerBall.setTranslateY(soccerBall.getTranslateY()+4)));
						timeline3.setCycleCount(9);
						timeline3.setDelay(Duration.millis(860));
						timeline3.play();
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

					// Exit the Application
					case ESCAPE:
						primaryStage.close();
					default:
						break;
					}
				}
			});
			
			
			
			// Color and phong material of the box
			PhongMaterial phong = new PhongMaterial();
			PhongMaterial floorMaterial = new PhongMaterial();
			PhongMaterial doorMaterial = new PhongMaterial();
			PhongMaterial wallMaterial = new PhongMaterial();
			PhongMaterial ceilingMaterial = new PhongMaterial();
			PhongMaterial soccerBallMaterial = new PhongMaterial();
			PhongMaterial earthMaterial = new PhongMaterial();
			PhongMaterial cylinderMaterial = new PhongMaterial();
			PhongMaterial hedgeMaterial = new PhongMaterial();
			PhongMaterial tableMaterial = new PhongMaterial();
			PhongMaterial couchMaterial = new PhongMaterial();
			PhongMaterial cushionMaterial = new PhongMaterial();
			PhongMaterial pathMaterial = new PhongMaterial();
			PhongMaterial chairMaterial = new PhongMaterial();
			PhongMaterial rugMaterial = new PhongMaterial();
			PhongMaterial mirrorMaterial = new PhongMaterial();
			PhongMaterial mahoganyMaterial = new PhongMaterial();
			PhongMaterial marbleMaterial = new PhongMaterial();
			PhongMaterial grassMaterial = new PhongMaterial();

			// phong.setDiffuseColor(Color.GREEN);
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

			soccerBallMaterial.setDiffuseMap(soccer);
			soccerBallMaterial.setSpecularMap(soccerSpec);
			soccerBall.setMaterial(soccerBallMaterial);
			
			earthMaterial.setDiffuseMap(new Image(earthDiff, MAP_WIDTH,
					MAP_HEIGHT, true, true));
			earthMaterial.setBumpMap(new Image(earthBump, MAP_WIDTH,
					MAP_HEIGHT, true, true));
			earthMaterial.setSpecularMap(new Image(earthSpec, MAP_WIDTH,
					MAP_HEIGHT, true, true));
			earth.setMaterial(earthMaterial);
			
			lineEarth.setSmooth(true);
			lineEarth.setStrokeLineJoin(StrokeLineJoin.ROUND);
			lineEarth.setStrokeLineCap(StrokeLineCap.ROUND);
			lineEarth.setStroke(Color.web("#3e260d"));
			lineEarth.setStrokeWidth(2.0f);
			
			cylinderMaterial.setDiffuseMap(cylinderPic);
			cylinder.setMaterial(cylinderMaterial);
			
			hedgeMaterial.setDiffuseMap(hedgePic);
		    hedge1.setMaterial(hedgeMaterial);
		    hedge2.setMaterial(hedgeMaterial);
		    hedge3.setMaterial(hedgeMaterial);
		    hedge4.setMaterial(hedgeMaterial);
		    
		    grassMaterial.setDiffuseMap(grassPic);
		    grass1.setMaterial(grassMaterial);
		    grass2.setMaterial(grassMaterial);
		    
		    tableMaterial.setDiffuseMap(tablePic);
		    tableLeg1.setMaterial(tableMaterial);
		    tableLeg2.setMaterial(tableMaterial);
		    tableLeg3.setMaterial(tableMaterial);
		    tableLeg4.setMaterial(tableMaterial);
		    tableTop.setMaterial(tableMaterial);
		    
		    chairMaterial.setDiffuseMap(chairPic);
		    chairLeg1.setMaterial(chairMaterial);
		    chairLeg2.setMaterial(chairMaterial);
		    chairLeg3.setMaterial(chairMaterial);
		    chairLeg4.setMaterial(chairMaterial);
		    chairTop.setMaterial(chairMaterial);
		    chairTop4.setMaterial(chairMaterial);
		    chairLeg21.setMaterial(chairMaterial);
		    chairLeg22.setMaterial(chairMaterial);
		    chairLeg23.setMaterial(chairMaterial);
		    chairLeg24.setMaterial(chairMaterial);
		    chairTop2.setMaterial(chairMaterial);
		    chairTop24.setMaterial(chairMaterial);
		    
		    mirrorMaterial.setDiffuseMap(mirrorPic);
			mirror.setMaterial(mirrorMaterial);
			
			couchMaterial.setDiffuseMap(couchPic);
			couchBox1.setMaterial(couchMaterial);
			couchBox2.setMaterial(couchMaterial);
			couchCylinder1.setMaterial(couchMaterial);
			couchCylinder2.setMaterial(couchMaterial);
			
			cushionMaterial.setDiffuseMap(cushionPic);
			cushion1.setMaterial(cushionMaterial);
			cushion2.setMaterial(cushionMaterial);
			
			pathMaterial.setDiffuseMap(pavementPic);
			path.setMaterial(pathMaterial);
		    
		    outerLeftFrontWall.setMaterial(phong);
		    outerRightFrontWall.setMaterial(phong);
			
		    mahoganyMaterial.setDiffuseMap(mahoganyPic);
		    hallTableLeg1.setMaterial(mahoganyMaterial);
		    hallTableLeg2.setMaterial(mahoganyMaterial);
		    hallTableLeg3.setMaterial(mahoganyMaterial);
		    hallTableLeg4.setMaterial(mahoganyMaterial);
		    hallTableTop1.setMaterial(mahoganyMaterial);
		    marbleMaterial.setDiffuseMap(marblePic);
		    hallTableTop2.setMaterial(marbleMaterial);
		    
		    rugMaterial.setDiffuseMap(rugPic);
		    rug.setMaterial(rugMaterial);

			// Light
			// PointLight pointLight = new PointLight(Color.WHITE);
			// pointLight.setTranslateX(centerX);
			// pointLight.setTranslateY(centerY);
			// pointLight.setTranslateZ(-200);
			//
			// PointLight pointLightback = new PointLight(Color.WHITE);
			// pointLightback.setTranslateX(centerX);
			// pointLightback.setTranslateY(centerY);
			// pointLightback.setTranslateZ(400);
			//
			// PointLight pointLightLeft = new PointLight(Color.BLUE);
			// pointLightLeft.setTranslateX(centerX-500);
			// pointLightLeft.setTranslateY(centerY);
			// //pointLightLeft.setTranslateZ(-200);

			AmbientLight ambLightRight = new AmbientLight(Color.WHITE);

			// adding stuff to the group
			group.getChildren().addAll(box, box2, box3, box4, backwall,
					leftwall, rightwall, leftcornerwall, rightcornerwall,
					leftfrontwall, rightfrontwall, ceiling, floor, floor2,
					floor3, floor4, door, soccerBall, earth, lineEarth, cylinder,hedge1,
					hedge2, hedge3, hedge4, tableLeg1, tableLeg2, tableLeg3, tableLeg4, tableTop,
					couchBox1, couchBox2, couchCylinder1, couchCylinder2, cushion1, cushion2,
					chairLeg1, chairLeg2, chairLeg3, chairLeg4, chairTop, path, chairTop4,
					chairLeg21, chairLeg22, chairLeg23, chairLeg24, chairTop2, chairTop24, rug,
					hallTableLeg1, hallTableLeg2, hallTableLeg3, hallTableLeg4,	hallTableTop1,
					hallTableTop2, mirror, outerLeftFrontWall, outerRightFrontWall, grass1, 
					grass2, camera, ambLightRight);

			// Showing the stage
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}