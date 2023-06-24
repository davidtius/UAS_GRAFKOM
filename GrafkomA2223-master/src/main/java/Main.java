import Engine.*;
import Engine.Object;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static org.lwjgl.util.glu.GLU.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window =
            new Window
                    (1840,1020,"Hello World");
    private ArrayList<Object> objects
            = new ArrayList<>();
    private ArrayList<Object> objectPeluru = new ArrayList<>();
    public String statusBody = "null";
    private MouseInput mouseInput;
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    Camera camera = new Camera();
    String legDirection = "front";
    int countDegree = 0;
    boolean blackout = false;
    float move = .02f;
    double lastPressed = 0;
    // Vector3f ambientStrength = new Vector3f(0.5f, 0.5f, 0.5f);

    public void init(){
        window.init();
        GL.createCapabilities();
        mouseInput = window.getMouseInput();
//        camera.setRotation((float) Math.toRadians(0f), (float) Math.toRadians(30f));
        objects.add(new Sphere("Blend",Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/shaderBaru.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/shaderBaru.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new ArrayList<>(),
                Arrays.asList(0.0f,0.0f,0.0f),
                0.2f,
                0.05f,
                0.2f,
                360,
                180,
                180f
        ));

        List<Vector3f> temp = new ArrayList<>(objects.get(0).getAllVertices());
         for (int i = 0; i < temp.size(); i++){
             if (temp.get(i).get(1) < 0f){
                 temp.remove(i);
             }
         }
         this.brute = new ArrayList<>(temp);

        objects.add(new Sphere("player",Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/shaderBaru.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/shaderBaru.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new ArrayList<>(),
                Arrays.asList(0f, 0f, 0f),
                0.2f,
                0.05f,
                0.2f,
                360,
                180,
                180f
        ));
        objects.get(1).translateObject(0f, -0.57f, 0f);
        objects.get(1).scaleObject(0.17f, 0.17f, 0.17f);
        camera.setPosition(0, objects.get(1).getCenterPoint().get(1)+0.4f, objects.get(1).getCenterPoint().get(2)+0.5f);
    }

    List<Vector3f> brute;
    boolean cekscan = false;
    boolean scannow = true;
    boolean scanning = false;

//    public void renderObject(Object object) {
//        Material material = object.getMaterial();
//
//        // Set material properties for rendering
//        // Example code using OpenGL and LWJGL:
//
//        // Set ambient color
//        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_AMBIENT, material.getAmbientColor());
//
//        // Set diffuse color
//        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, material.getDiffuseColor());
//
//        // Set specular color
//        GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, material.getSpecularColor());
//
//        // ... Set other material properties, textures, etc.
//
//        //
//    }

    boolean collision_detection(float posx, float posy, float posz){

//        for(int i = 0; i < this.brute.size(); i++){
//
//            if (v3R(this.brute.get(i).get(0), this.brute.get(i).get(1), this.brute.get(i).get(2), posx, posy, posz) < 0.08f) {
//                return true;
//            }
//        }
//
        return false;
    }
        public void input(float x, float y, float z) {
//        if (mouseInput.isLeftButtonPressed()) {
//            shoot();
//        }
            Vector2f displayVec = window.getMouseInput().getDisplVec();

            float ox = objects.get(1).getCenterPoint().get(0);
            float oy = objects.get(1).getCenterPoint().get(1);
            float oz = objects.get(1).getCenterPoint().get(2);
            float cx = camera.getPosition().x;
            float cy = camera.getPosition().y;
            float cz = camera.getPosition().z;

            // rotate object
            objects.get(1).translateObject(-ox, -oy, -oz);
            objects.get(1).rotateObject(-1*(float)Math.toRadians(displayVec.y * 0.25f), 0f,1f, 0f );
            objects.get(1).translateObject(ox, oy, oz);

             ox = objects.get(1).getCenterPoint().get(0);
             oy = objects.get(1).getCenterPoint().get(1);
             oz = objects.get(1).getCenterPoint().get(2);

            //  System.out.println(objects.get(1).getCenterPoint());
            // rotate camera
            // camera.translateCddddddddddddddamera(-cx, -cy, -cz);
            // glLoadIdentity();
            glPushMatrix();
            camera.getViewMatrix().lookAt(new Vector3f(cx, 0.25f, cz), new Vector3f(ox, oy, oz), new Vector3f(0, 1, 0));
            glPopMatrix();
            // Set up the view transformation
            // glLoadMatrixf(camera.getViewMatrix().get(new float[16]));

        //    glRotatef(-1*(float)Math.toRadians(displayVec.y * 0.25f), 0f,1f, 0f );
        //     glTranslatef(cx,cy,cz);
//            camera.move(-1*(float)Math.toRadians(displayVec.y * 0.25f), 0, 0);

//            camera.translateCamera(-ox, -oy, -oz);
//            camera.rotateCamera(-1*(float)Math.toRadians(displayVec.y * 0.25f), 0f,1f, 0f );
//            camera.translateCamera(ox,oy+0.4f,oz+0.5f);

//            camera.translateCamera(-0.01f, 0f, 0f);



        if(window.getMouseInput().getScroll().y != 0){
            projection.setFOV(projection.getFOV()- (window.getMouseInput().getScroll().y*0.01f));
            window.getMouseInput().setScroll(new Vector2f());
        }


        if (window.isKeyPressed(GLFW_KEY_E)) {
            if (lastPressed == 0 || lastPressed + 1000 <= System.currentTimeMillis() / 1000) {
                lastPressed = System.currentTimeMillis() / 1000;
                blackout = !blackout;
            }
            // System.out.println(System.currentTimeMillis() / 1000);
            // if (blackout)
            // if window.isKeyReleased()
            // blackout = !blackout;
        }

        if (window.isKeyPressed(GLFW_KEY_M)) {
            boolean boxOpen = false;
            boolean buttonPressed = false;
            if (!boxOpen) {

                while (!openVent(objects.get(2).getChildObject().get(9), new Vector3f(1.15f, -0.05f, -1.15f))) {

                    openVent(objects.get(2).getChildObject().get(9), new Vector3f(1.15f, -0.05f, -1.15f));

                }
                boxOpen = true;
            }

            if (boxOpen) {
                objects.get(2).getChildObject().get(8).translateObject(0f, -0.01f, 0f);
                buttonPressed = true;
            }

            if (buttonPressed) {
                while (!closeVent(objects.get(2).getChildObject().get(9), new Vector3f(1.15f, -0.05f, -1.15f),0.15f)) {

                    closeVent(objects.get(2).getChildObject().get(9), new Vector3f(1.15f, -0.05f, -1.15f),0.15f);
                }
                objects.get(2).getChildObject().get(8).translateObject(0f, 0.01f, 0f);
                buttonPressed = false;
            }




        }

//        if (window.isKeyPressed(GLFW_KEY_K)) {
//            //            if (objects.get(0).getCenterPoint().get(0) >= 0.9f && objects.get(0).getCenterPoint().get(0) <= 1.55f
////            && objects.get(0).getCenterPoint().get(1) >= 0.39f && objects.get(0).getCenterPoint().get(1) <= 0.42f
////            && objects.get(0).getCenterPoint().get(2) >= 0.68f && objects.get(0).getCenterPoint().get(2) <= 0.72f) {
////
////            }
//            if (currentColor.equals("red")) {
//                for (int i = 0; i < objects.get(0).getChildObject().size(); i++) {
//                    if (i == 2 || i == 4 || i == 5) {
//                        continue;
//                    }
//                    objects.get(0).getChildObject().get(i).changeColor(colors.get(1));
//                }
//                objects.get(0).changeColor(colors.get(1));
//                currentColor = "green";
//            }
//            else if(currentColor.equals("green")) {
//                for (int i = 0; i < objects.get(0).getChildObject().size(); i++) {
//                    if (i == 2 || i == 4 || i == 5) {
//                        continue;
//                    }
//                    objects.get(0).getChildObject().get(i).changeColor(colors.get(2));
//                }
//                objects.get(0).changeColor(colors.get(2));
//                currentColor = "blue";
//            }
//            else {
//                for (int i = 0; i < objects.get(0).getChildObject().size(); i++) {
//                    if (i == 2 || i == 4 || i == 5) {
//                        continue;
//                    }
//                    objects.get(0).getChildObject().get(i).changeColor(colors.get(0));
//                }
//                objects.get(0).changeColor(colors.get(0));
//                currentColor = "red";
//            }
//        }

        if (window.isKeyPressed(GLFW_KEY_E)) {
//                if (scannow && cekscan && !isVenting) {
//                    scanning = true;
//                    scannow = false;
//                } else if (!isVenting && !scanning && !cekscan) {
//                    float jarak1 = v3R(
//                            objects.get(0).getCenterPoint().get(0),
//                            objects.get(0).getCenterPoint().get(1),
//                            objects.get(0).getCenterPoint().get(2),
//                            objects.get(4).getCenterPoint().get(0),
//                            objects.get(4).getCenterPoint().get(1),
//                            objects.get(4).getCenterPoint().get(2)
//                    );
//
//                    float jarak2 = v3R(
//                            objects.get(0).getCenterPoint().get(0),
//                            objects.get(0).getCenterPoint().get(1),
//                            objects.get(0).getCenterPoint().get(2),
//                            objects.get(5).getCenterPoint().get(0),
//                            objects.get(5).getCenterPoint().get(1),
//                            objects.get(5).getCenterPoint().get(2)
//                    );
//
//                    if (jarak1 <= 0.35f) {
//                        ventSrc = objects.get(4);
//                        ventDest = objects.get(5);
//                        isVenting = true;
//                    } else if (jarak2 <= 0.35f) {
//                        ventSrc = objects.get(5);
//                        ventDest = objects.get(4);
//                        isVenting = true;
//                    }
//                }
            }

            float posx = camera.getPosition().get(0);
            float posy = camera.getPosition().get(1);
            float posz = camera.getPosition().get(2);

            if (window.isKeyPressed(GLFW_KEY_W) && !scanning && (z > -2.3f) && !collision_detection(posx, posy, posz-0.05f)) {
//                objects.get(0).setAngle(180f);
                camera.translateCamera(0f, 0f, -move);
                objects.get(1).translateObject(0f, 0f, -1*move);
//                if (!collideTable(x, y, z - 0.1f)) move_forward();
            }


            if (window.isKeyPressed(GLFW_KEY_S) && !scanning  && (z < 2.3f) && !collision_detection(posx, posy, posz+0.05f)) {
//                objects.get(0).setAngle(0f);
                camera.translateCamera(0f, 0f, move);
//                if (!collideTable(x, y, z + 0.1f)) move_backward();
                objects.get(1).translateObject(0f, 0f, move);
            }


            if (window.isKeyPressed(GLFW_KEY_A) && !scanning  && (x > -2.3f) && !collision_detection(posx-0.05f, posy, posz)) {
//                objects.get(0).setAngle(270);
                camera.translateCamera(-move, 0f, 0f);
//                if (!collideTable(x - 0.1f, y, z)) move_left();
                objects.get(1).translateObject(-1*move, 0f, 0f);
            }


            if (window.isKeyPressed(GLFW_KEY_D) && !scanning  && (x < 2.3f) && !collision_detection(posx + 0.05f, posy, posz)) {
//                objects.get(0).setAngle(90);
//                camera.moveRight(move);
                camera.translateCamera(move, 0f, 0f);
                objects.get(1).translateObject(move, 0f, 0f);
//                if (!collideTable(x + 0.1f, y, z)) move_right();
            }

//            if (window.isKeyPressed(GLFW_KEY_LEFT_SHIFT)) {
//                camera.moveUp(move);
//            }
            if (window.isKeyPressed(GLFW_KEY_RIGHT_SHIFT)) {
                camera.moveUp(move);
            }
            if (window.isKeyPressed(GLFW_KEY_G)) {
                float tempx = camera.getPosition().x;
                float tempy = camera.getPosition().y;
                float tempz = camera.getPosition().z;

                camera.setPosition(objects.get(0).getCenterPoint().get(0),objects.get(0).getCenterPoint().get(1),objects.get(0).getCenterPoint().get(2));
                camera.addRotation(0,(float) Math.toRadians(1.2f));
                camera.setPosition(tempx,tempy,tempz);
                camera.moveLeft(0.035f);
            }

            if (window.isKeyPressed(GLFW_KEY_SPACE) && !scanning ) {
                jumping = true;
            }
    }

    private boolean scanup=true;

    public void scanup(){
        objects.get(1).getChildObject().get(4).translateObject(0f,0.002f,0f);
    }

    public void scandown(){
        objects.get(1).getChildObject().get(4).translateObject(0f,-0.002f,0f);
    }

    public void move_forward() {
        objects.get(0).translateObject(.0f, .0f, -.02f);
        camera.moveForward(move);
        statusBody = "depan";

        if (!window.isKeyPressed(GLFW_KEY_A) && !window.isKeyPressed(GLFW_KEY_D)) {
            if (legDirection.equals("front")) {
                objects.get(0).getChildObject().get(0).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(0).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                objects.get(0).getChildObject().get(1).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(1).rotateObject((float) -Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(1).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                if (objects.get(0).getChildObject().get(0).getCenterPoint().get(2) <= objects.get(0).getCenterPoint().get(2) - 0.1f)
                    legDirection = "back";
            }
            else if (legDirection.equals("back")){
                objects.get(0).getChildObject().get(0).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(0).rotateObject((float) -Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(0).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                objects.get(0).getChildObject().get(1).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(1).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                if (objects.get(0).getChildObject().get(0).getCenterPoint().get(2) > objects.get(0).getCenterPoint().get(2) + 0.1f) {
                    legDirection = "front";
                }
            }
        }
    }

    public void move_backward() {
        objects.get(0).translateObject(.0f, .0f, .02f);
        camera.moveBackwards(move);
        statusBody = "belakang";

        if (!window.isKeyPressed(GLFW_KEY_A) && !window.isKeyPressed(GLFW_KEY_D)) {
            if (legDirection.equals("front")) {
                objects.get(0).getChildObject().get(0).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(0).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                objects.get(0).getChildObject().get(1).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(1).rotateObject((float) -Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(1).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );
                if (objects.get(0).getChildObject().get(0).getCenterPoint().get(2) <= objects.get(0).getCenterPoint().get(2) - 0.1f)
                    legDirection = "back";
            }
            else if (legDirection.equals("back")) {
                objects.get(0).getChildObject().get(0).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(0).rotateObject((float) -Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(0).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                objects.get(0).getChildObject().get(1).translateObject(
                        -objects.get(0).getCenterPoint().get(0),
                        -objects.get(0).getCenterPoint().get(1),
                        -objects.get(0).getCenterPoint().get(2)
                );
                objects.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(5f), 1.0f, 0.0f, 0.0f);
                objects.get(0).getChildObject().get(1).translateObject(
                        objects.get(0).getCenterPoint().get(0),
                        objects.get(0).getCenterPoint().get(1),
                        objects.get(0).getCenterPoint().get(2)
                );

                if (objects.get(0).getChildObject().get(0).getCenterPoint().get(2) > objects.get(0).getCenterPoint().get(2) + 0.1f) {
                    legDirection = "front";
                }
            }
        }
    }

    public void move_right() {
        objects.get(0).translateObject(0.02f, .0f, .0f);
        camera.moveRight(move);
        statusBody = "kanan";

        if (legDirection.equals("front")) {
            objects.get(0).getChildObject().get(0).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(0).rotateObject((float) -Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(0).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            objects.get(0).getChildObject().get(1).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(1).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            if (objects.get(0).getChildObject().get(0).getCenterPoint().get(0) <= objects.get(0).getCenterPoint().get(0) - 0.04f)
                legDirection = "back";
        }
        else if (legDirection.equals("back")){
            objects.get(0).getChildObject().get(0).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(0).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            objects.get(0).getChildObject().get(1).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(1).rotateObject((float) -Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(1).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            if (objects.get(0).getChildObject().get(0).getCenterPoint().get(0) > objects.get(0).getCenterPoint().get(0) + 0.04f) {
                legDirection = "front";
            }
        }
    }

    public void move_left() {
        objects.get(0).translateObject(-0.02f, .0f, .0f);
        camera.moveLeft(move);
        statusBody = "kiri";

        if (legDirection.equals("front")) {
            objects.get(0).getChildObject().get(0).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(0).rotateObject((float) -Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(0).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            objects.get(0).getChildObject().get(1).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(1).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            if (objects.get(0).getChildObject().get(0).getCenterPoint().get(0) <= objects.get(0).getCenterPoint().get(0) - 0.04f)
                legDirection = "back";
        }
        else if (legDirection.equals("back")){
            objects.get(0).getChildObject().get(0).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(0).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            objects.get(0).getChildObject().get(1).translateObject(
                    -objects.get(0).getCenterPoint().get(0),
                    -objects.get(0).getCenterPoint().get(1),
                    -objects.get(0).getCenterPoint().get(2)
            );
            objects.get(0).getChildObject().get(1).rotateObject((float) -Math.toRadians(5f), 0.0f, 0.0f, 1.0f);
            objects.get(0).getChildObject().get(1).translateObject(
                    objects.get(0).getCenterPoint().get(0),
                    objects.get(0).getCenterPoint().get(1),
                    objects.get(0).getCenterPoint().get(2)
            );

            if (objects.get(0).getChildObject().get(0).getCenterPoint().get(0) > objects.get(0).getCenterPoint().get(0) + 0.04f) {
                legDirection = "front";
            }
        }
    }

    boolean jumped = false;
    boolean jumping = false;
    public void scan(){
        if (scanup){
            scanup();
            if (objects.get(1).getChildObject().get(4).getCenterPoint().get(1) >=0.15f) scanup=false;
        } else {
            scandown();
            if (objects.get(1).getChildObject().get(4).getCenterPoint().get(1) <=-.199f) {
                scanup=true;
                scanning=false;
                scannow=true;
            }
        }
    }
    public void jump() {
        if (!jumped){
            jumpup();
            if (objects.get(0).getCenterPoint().get(1) >= 0.2f) jumped = true;
        } else {
            jumpdown();
            if (objects.get(0).getCenterPoint().get(1) <= 0) {
                jumped=false;
                jumping=false;
            }
        }
    }
    public void jumpup(){
        objects.get(0).translateObject(0f, 0.01f, 0f);
        camera.moveUp(move - 0.01f);
    }
    public void jumpdown(){
        objects.get(0).translateObject(0f, -0.01f, 0f);
        camera.moveDown(move - 0.01f);
    }

    public boolean closeVent(Object vent, Vector3f engsel, float offSetX) {
        if (vent.getCenterPoint().get(0) <= engsel.x + offSetX) {
            vent.translateObject(
                    -engsel.x,
                    -engsel.y,
                    -engsel.z
            );
            vent.rotateObject((float) -Math.toRadians(1.5f), 0.0f, 0.0f, 1.0f);
            vent.translateObject(
                    engsel.x,
                    engsel.y,
                    engsel.z
            );
        }

        return vent.getCenterPoint().get(0) > engsel.x + 0.15f;
    }

    public boolean openVent(Object vent, Vector3f engsel) {
        if (vent.getCenterPoint().get(0) > engsel.x) {
            vent.translateObject(
                    -engsel.x,
                    -engsel.y,
                    -engsel.z
            );
            vent.rotateObject((float) Math.toRadians(1.5f), 0.0f, 0.0f, 1.0f);
            vent.translateObject(
                    engsel.x,
                    engsel.y,
                    engsel.z
            );
        }

        return vent.getCenterPoint().get(0) <= engsel.x;
    }

//    public boolean collideTable(float x, float y, float z){
//if (v3R(x, y, z, objects.get(2).getChildObject().get(3).getCenterPoint().get(0), objects.get(2).getChildObject().get(3).getCenterPoint().get(1), objects.get(2).getChildObject().get(3).getCenterPoint().get(2)) <= 0.8f){
//    return true;
//}
//
//        if (v3R(x, y, z, objects.get(2).getChildObject().get(5).getCenterPoint().get(0), objects.get(2).getChildObject().get(5).getCenterPoint().get(1), objects.get(2).getChildObject().get(5).getCenterPoint().get(2)) <= 0.8f){
//            return true;
//        }
//
//        if (v3R(x, y, z, objects.get(3).getCenterPoint().get(0), objects.get(3).getCenterPoint().get(1), objects.get(3).getCenterPoint().get(2)) <= 0.2f){
//            return true;
//        }

//        if (v3R(x, y, z, objects.get(8).getCenterPoint().get(0), objects.get(8).getCenterPoint().get(1), objects.get(8).getCenterPoint().get(2)) <= 0.2f){
//            return true;
//        }

//return false;
//    }

//    public boolean isDie(){
//        try {
//            if (v3R(objectPeluru.get(0).getCenterPoint().get(0), objectPeluru.get(0).getCenterPoint().get(1), objectPeluru.get(0).getCenterPoint().get(2), objects.get(8).getCenterPoint().get(0), objects.get(8).getCenterPoint().get(1), objects.get(8).getCenterPoint().get(2)) <= 5f){
//                return true;
//            }
//        } catch (Exception i){
//            return true;
//        }
//        return false;
//    }

    public void loop(){

        while (window.isOpen()) {
            glMatrixMode(GL_MODELVIEW);
            glLoadIdentity();
            window.update();
            glClearColor(0.0f,
                    0.0f, 0.0f,
                    0.0f);
            GL.createCapabilities();
            float x1 = objects.get(0).getCenterPoint().get(0);
            float y1 = objects.get(0).getCenterPoint().get(1);
            float z1 = objects.get(0).getCenterPoint().get(2);

            input(x1, y1, z1);

            //code
            for(Object object: objects){
                object.drawWithVerticesColor(camera, projection, blackout);
            }


//            for(Object object: objectPeluru) {
//                object.draw(camera,projection);
//            }
////            for (Object oo : objects.get(0).getChildObject()) oo.draw();
//
//            if (isVenting) {
//                vent(ventSrc, ventDest);
//            }
//
//            if (scanning){
//                scan();
//            }
//
//            if (v3R(x1, y1, z1, objects.get(1).getCenterPoint().get(0), objects.get(1).getCenterPoint().get(1), objects.get(1).getCenterPoint().get(2)) <=0.22f){
//                cekscan = true;
//            } else {
//                cekscan = false;
//            }
//
//            if (jumping) {
//                jump();
//            }

//            if (isDie()){
//                System.out.println(1);
//            }

            // Restore state
            glDisableVertexAttribArray(0);

            // Poll for window events.
            // The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }

    public float v3R(float x1, float y1, float z1, float x2, float y2, float z2){
        return (float) Math.sqrt(Math.pow(Math.abs(x1-x2), 2) + Math.pow(Math.abs(y2-y1),2) + Math.pow(Math.abs(z2-z1), 2));
    }
    public void run() {

        init();
        loop();

        // Terminate GLFW and
        // free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
    public static void main(String[] args) {
        new Main().run();
    }
}