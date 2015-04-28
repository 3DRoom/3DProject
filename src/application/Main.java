package application;

import java.awt.AWTException;
import java.awt.Robot;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
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

	double centerX;
	double centerY;

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
			PerspectiveCamera camera = new PerspectiveCamera(true);
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
						System.out.println("Key Pressed: " + ke.getCode()
								+ translate.toString());
						break;

					case W:
						camera.getTransforms()
								.addAll(translate = new Translate(centerX,
										centerY, 10));
						System.out.println("Key Pressed: " + ke.getCode()
								+ translate.toString());

						break;
					// Rotation around Y Axis
					case LEFT:
						// door.getTransforms().add(new Rotate(5, centerX+50, 0,
						// 0, Rotate.Y_AXIS));
						System.out.println("Key Pressed: " + ke.getCode());
						break;

					case RIGHT:
						 soccerBall.getTransforms().add(new Rotate(-5,
						 Rotate.Y_AXIS));
						System.out.println("Key Pressed: " + ke.getCode());
						break;
					// Camera rotation around Z Axis
					case NUMPAD1:
						rotateZ = new Rotate(-1, Rotate.Z_AXIS);
						camera.getTransforms().add(rotateZ);
						System.out.println("Key Pressed: " + ke.getCode()
								+ rotateZ);
						break;

					case NUMPAD3:
						rotateZ = new Rotate(1, Rotate.Z_AXIS);
						camera.getTransforms().add(rotateZ);
						System.out.println("Key Pressed: " + ke.getCode()
								+ rotateZ);
						break;
					// Camera rotation Y Axis
					case A:
						rotateY = new Rotate(-3, Rotate.Y_AXIS);
						camera.getTransforms().add(rotateY);
						System.out.println("Key Pressed: " + ke.getCode()
								+ rotateY);
						break;

					case D:
						rotateY = new Rotate(3, Rotate.Y_AXIS);
						camera.getTransforms().add(rotateY);
						System.out.println("Key Pressed: " + ke.getCode()
								+ rotateY);
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
						//opens the door
					case P:
					    timeline = new Timeline(new KeyFrame(Duration
								.millis(50), ae -> door.getTransforms()
								.add(new Rotate(1, centerX + 50, 0, 0,
										Rotate.Y_AXIS))));
						timeline.setCycleCount(88);
						timeline.play();
						System.out.println(timeline.getCycleCount());
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
						System.out.println(cylinder.getTranslateY());
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
					floor3, floor4, door, soccerBall, earth, lineEarth, cylinder, camera,
					ambLightRight);

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