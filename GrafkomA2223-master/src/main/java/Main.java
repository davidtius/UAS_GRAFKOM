import Engine.*;
import Engine.Object;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.joml.Matrix4f;

import java.util.*;

//import static org.lwjgl.util.glu.GLU.*;
import static org.lwjgl.glfw.GLFW.*;

import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {

    private Window window =
            new Window
                    (1840, 1020, "Among Us");
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
    float move = .025f;
    long lastPressed = 0;
    long lastPressed2 = 0;
    long lastPressed3 = 0;
    boolean dead = false;
    List<Vector3f> collisionPoint = Arrays.asList(
            // hall medbay
            new Vector3f(-3.55f, 0.25f, -1.5f),
            new Vector3f(-3.7f, 0.25f, -1.5f),
            new Vector3f(-3.85f, 0.25f, -1.5f),
            new Vector3f(-4f, 0.25f, -1.5f),
            new Vector3f(-4.15f, 0.25f, -1.5f),
            new Vector3f(-4.3f, 0.25f, -1.5f),
            new Vector3f(-4.45f, 0.25f, -1.5f),
            new Vector3f(-4.6f, 0.25f, -1.5f),
            new Vector3f(-4.8f, 0.25f, -1.5f),
            new Vector3f(-3.55f, 0.25f, -0.5f),
            new Vector3f(-3.7f, 0.25f, -0.5f),
            new Vector3f(-3.85f, 0.25f, -0.5f),
            new Vector3f(-4f, 0.25f, -0.5f),
            new Vector3f(-4.15f, 0.25f, -0.5f),
            new Vector3f(-4.3f, 0.25f, -0.5f),
            new Vector3f(-4.45f, 0.25f, -0.5f),
            new Vector3f(-4.6f, 0.25f, -0.5f),
            new Vector3f(-4.45f, 0.25f, -0.5f),
            new Vector3f(-5.1f, 0.25f, -1.4f),
            new Vector3f(-5.1f, 0.25f, -1.2f),
            new Vector3f(-5.1f, 0.25f, -1f),
            new Vector3f(-5.1f, 0.25f, -0.8f),
            new Vector3f(-5.1f, 0.25f, -0.6f),
            new Vector3f(-5.1f, 0.25f, -0.4f),

            // medbay
            new Vector3f(-5.3f, 0.25f, -0.05f),
            new Vector3f(-5.5f, 0.25f, -0.05f),
            new Vector3f(-5.7f, 0.25f, -0.05f),
            new Vector3f(-5.9f, 0.25f, -0.05f),

            new Vector3f(-5.9f, 0.25f, 0.05f),
            new Vector3f(-5.9f, 0.25f, 0.25f),
            new Vector3f(-5.9f, 0.25f, 0.5f),
            new Vector3f(-5.9f, 0.25f, 0.75f),
            new Vector3f(-5.9f, 0.25f, 1f),
            new Vector3f(-5.9f, 0.25f, 1.25f),
            new Vector3f(-5.9f, 0.25f, 1.5f),
            new Vector3f(-5.9f, 0.25f, 1.75f),
            new Vector3f(-5.9f, 0.25f, 2f),
            new Vector3f(-5.9f, 0.25f, 2.25f),
            new Vector3f(-5.9f, 0.25f, 2.5f),
            new Vector3f(-5.9f, 0.25f, 2.6f),

            new Vector3f(-5.8f, 0.25f, 2.8f),
            new Vector3f(-5.85f, 0.25f, 3f),
            new Vector3f(-5.7f, 0.25f, 3.2f),

            new Vector3f(-5.5f, 0.25f, 3.2f),
            new Vector3f(-5.3f, 0.25f, 3.2f),
            new Vector3f(-5.1f, 0.25f, 3.2f),
            new Vector3f(-4.9f, 0.25f, 3.2f),
            new Vector3f(-4.7f, 0.25f, 3.2f),
            new Vector3f(-4.5f, 0.25f, 3.2f),
            new Vector3f(-4.3f, 0.25f, 3.2f),
            new Vector3f(-4.1f, 0.25f, 3.2f),
            new Vector3f(-3.9f, 0.25f, 3.2f),
            new Vector3f(-3.7f, 0.25f, 3.2f),
            new Vector3f(-3.5f, 0.25f, 3.2f),
            new Vector3f(-3.3f, 0.25f, 3.2f),
            new Vector3f(-3.1f, 0.25f, 3.2f),
            new Vector3f(-2.9f, 0.25f, 3.2f),
            new Vector3f(-2.75f, 0.25f, 3.2f),
            new Vector3f(-2.6f, 0.25f, 3.2f),

            new Vector3f(-2.6f, 0.25f, 3f),
            new Vector3f(-2.6f, 0.25f, 2.8f),
            new Vector3f(-2.6f, 0.25f, 2.6f),

            new Vector3f(-2.8f, 0.25f, 2.4f),
            new Vector3f(-3f, 0.25f, 2.2f),
            new Vector3f(-3.2f, 0.25f, 2f),
            new Vector3f(-3.4f, 0.25f, 1.8f),
            new Vector3f(-3.6f, 0.25f, 1.6f),

            new Vector3f(-3.6f, 0.25f, 1.4f),
            new Vector3f(-3.6f, 0.25f, 1.2f),
            new Vector3f(-3.6f, 0.25f, 1f),
            new Vector3f(-3.6f, 0.25f, 0.8f),
            new Vector3f(-3.6f, 0.25f, 0.6f),
            new Vector3f(-3.6f, 0.25f, 0.4f),
            new Vector3f(-3.6f, 0.25f, 0.2f),
            new Vector3f(-3.6f, 0.25f, 0.1f),

            new Vector3f(-3.8f, 0.25f, 0.1f),
            new Vector3f(-4f, 0.25f, 0.1f),
            new Vector3f(-4.2f, 0.25f, 0.1f),
            new Vector3f(-4.4f, 0.25f, 0.1f),
            new Vector3f(-4.6f, 0.25f, 0.1f),

            // main hall
            new Vector3f(-3f, 0.25f, -1.8f),
            new Vector3f(-3f, 0.25f, -2f),
            new Vector3f(-3f, 0.25f, -2.15f),
            new Vector3f(-3f, 0.25f, -2.3f),
            new Vector3f(-3f, 0.25f, -2.45f),
            new Vector3f(-3f, 0.25f, -2.6f),
            new Vector3f(-3f, 0.25f, -2.75f),
            new Vector3f(-3f, 0.25f, -2.9f),
            new Vector3f(-3f, 0.25f, -3f),
            new Vector3f(-3f, 0.35f, -3.1f),

            new Vector3f(-3f, 0.25f, -3.1f),
            new Vector3f(-2.9f, 0.25f, -3.2f),
            new Vector3f(-2.7f, 0.25f, -3.3f),
            new Vector3f(-2.5f, 0.25f, -3.4f),
            new Vector3f(-2.6f, 0.25f, -3.6f),
            new Vector3f(-2.5f, 0.25f, -3.8f),
            new Vector3f(-2.4f, 0.25f, -3.8f),
            new Vector3f(-2.3f, 0.25f, -3.9f),
            new Vector3f(-2.2f, 0.25f, -3.95f),
            new Vector3f(-2.1f, 0.25f, -4.05f),
            new Vector3f(-2f, 0.25f, -4.15f),
            new Vector3f(-1.9f, 0.25f, -4.2f),

            new Vector3f(-1.8f, 0.25f, -4.2f),
            new Vector3f(-1.6f, 0.25f, -4.2f),
            new Vector3f(-1.4f, 0.25f, -4.2f),
            new Vector3f(-1.2f, 0.25f, -4.2f),
            new Vector3f(-1f, 0.25f, -4.2f),
            new Vector3f(-0.8f, 0.25f, -4.2f),
            new Vector3f(-0.6f, 0.25f, -4.2f),
            new Vector3f(-0.4f, 0.25f, -4.2f),
            new Vector3f(-0.2f, 0.25f, -4.2f),
            new Vector3f(-0f, 0.25f, -4.2f),
            new Vector3f(0.2f, 0.25f, -4.2f),
            new Vector3f(0.4f, 0.25f, -4.2f),
            new Vector3f(0.6f, 0.25f, -4.2f),
            new Vector3f(0.8f, 0.25f, -4.2f),
            new Vector3f(1f, 0.25f, -4.2f),
            new Vector3f(1.2f, 0.25f, -4.2f),
            new Vector3f(1.4f, 0.25f, -4.2f),
            new Vector3f(1.6f, 0.25f, -4.2f),
            new Vector3f(1.8f, 0.25f, -4.2f),

            new Vector3f(-3f, 0.25f, -0.3f),
            new Vector3f(-3f, 0.25f, -0.1f),
            new Vector3f(-3f, 0.25f, 0.1f),
            new Vector3f(-3f, 0.25f, 0.3f),
            new Vector3f(-3f, 0.25f, 0.5f),
            new Vector3f(-3f, 0.25f, 0.7f),
            new Vector3f(-3f, 0.25f, 0.9f),

            new Vector3f(-2.9f, 0.25f, 1f),
            new Vector3f(-2.8f, 0.25f, 1.05f),
            new Vector3f(-2.7f, 0.25f, 1.35f),
            new Vector3f(-2.6f, 0.25f, 1.5f),
            new Vector3f(-2.4f, 0.25f, 1.6f),
            new Vector3f(-2.2f, 0.25f, 1.8f),
            new Vector3f(-2f, 0.25f, 2f),
            new Vector3f(-1.8f, 0.25f, 2.25f),
            new Vector3f(-1.6f, 0.25f, 2.35f),

            new Vector3f(-1.4f, 0.25f, 2.35f),
            new Vector3f(-1.2f, 0.25f, 2.35f),
            new Vector3f(-1f, 0.25f, 2.35f),
            new Vector3f(-0.8f, 0.25f, 2.35f),
            new Vector3f(-0.6f, 0.25f, 2.35f),
            new Vector3f(-0.4f, 0.25f, 2.35f),
            new Vector3f(-0.2f, 0.25f, 2.35f),
            new Vector3f(0f, 0.25f, 2.35f),
            new Vector3f(0.2f, 0.25f, 2.35f),
            new Vector3f(0.4f, 0.25f, 2.35f),
            new Vector3f(0.6f, 0.25f, 2.35f),
            new Vector3f(0.8f, 0.25f, 2.35f),
            new Vector3f(1f, 0.25f, 2.35f),
            new Vector3f(1.2f, 0.25f, 2.35f),
            new Vector3f(1.4f, 0.25f, 2.35f),
            new Vector3f(1.6f, 0.25f, 2.35f),
            new Vector3f(1.8f, 0.25f, 2.35f),
            new Vector3f(2f, 0.25f, 2.35f),

            new Vector3f(2.2f, 0.25f, 2.1f),
            new Vector3f(2.4f, 0.25f, 1.95f),
            new Vector3f(2.6f, 0.25f, 1.75f),
            new Vector3f(2.8f, 0.25f, 1.55f),
            new Vector3f(3f, 0.25f, 1.35f),
            new Vector3f(3.15f, 0.25f, 1.15f),
            new Vector3f(3.3f, 0.25f, 1f),
            new Vector3f(3.5f, 0.25f, 0.8f),

            new Vector3f(3.3f, 0.25f, 0.8f),
            new Vector3f(3.3f, 0.25f, 0.6f),
            new Vector3f(3.3f, 0.25f, 0.4f),
            new Vector3f(3.3f, 0.25f, 0.2f),
            new Vector3f(3.3f, 0.25f, 0f),
            new Vector3f(3.3f, 0.25f, -0.3f),
            new Vector3f(3.3f, 0.25f, -0.6f),
            new Vector3f(3.3f, 0.25f, -0.9f),
            new Vector3f(3.3f, 0.25f, -1.2f),
            new Vector3f(3.3f, 0.25f, -1.5f),
            new Vector3f(3.3f, 0.25f, -1.8f),
            new Vector3f(3.3f, 0.25f, -2.1f),
            new Vector3f(3.3f, 0.25f, -2.3f),
            new Vector3f(3.3f, 0.25f, -2.5f),

            new Vector3f(3.1f, 0.25f, -2.7f),
            new Vector3f(2.9f, 0.25f, -2.9f),
            new Vector3f(2.7f, 0.25f, -3.1f),
            new Vector3f(2.5f, 0.25f, -3.3f),
            new Vector3f(2.3f, 0.25f, -3.5f),
            new Vector3f(2.1f, 0.25f, -3.7f),
            new Vector3f(1.9f, 0.25f, -3.9f),
            new Vector3f(1.75f, 0.25f, -4f)
    );
    // Vector3f ambientStrength = new Vector3f(0.5f, 0.5f, 0.5f);

    public void init() {

        window.init();
        GL.createCapabilities();
        mouseInput = window.getMouseInput();
//        camera.setRotation((float) Math.toRadians(0f), (float) Math.toRadians(30f));
        objects.add(new Sphere("map", Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/shaderBaru.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/shaderBaru.frag", GL_FRAGMENT_SHADER)
        ),
                new ArrayList<>(),
                new ArrayList<>(),
                Arrays.asList(0.0f, 0.0f, 0.0f),
                0.2f,
                0.05f,
                0.2f,
                360,
                180,
                180f
        ));

        this.brute = new ArrayList<>(objects.get(0).getAllVertices());

        objects.add(new Sphere("player", Arrays.asList(
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
        camera.setPosition(0, 0.2f, objects.get(1).getCenterPoint().get(2) + 0.5f);

        objects.add(new Sphere("knife", Arrays.asList(
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
        objects.get(2).scaleObject(0.05f, 0.05f, 0.05f);
        objects.get(2).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
        objects.get(2).rotateObject((float) Math.toRadians(45f), 1f, 0f, 0f);
        objects.get(2).translateObject(camera.getPosition().x + 0.15f, 0.15f, camera.getPosition().z - 0.2f);

        glfwSetInputMode(window.getWindow(), GLFW_CURSOR, GLFW_CURSOR_DISABLED);
        this.res = new Vector3f(objects.get(1).getCenterPoint().get(0), 0f, objects.get(1).getCenterPoint().get(2));
    }

    List<Vector3f> brute;
    boolean cekscan = false;
    boolean scannow = true;
    boolean scanning = false;

    boolean collision_detection(float posx, float posy, float posz) {
        if (window.isKeyPressed(GLFW_KEY_SPACE)) return false;

        if (v3R(posx, posy, posz, objects.get(1).getCenterPoint().get(0), objects.get(1).getCenterPoint().get(1),objects.get(1).getCenterPoint().get(2)) < 0.3f){
            return true;
        }
//        if (cameray,.getPosition().get(0) >= -4.7f && camera.getPosition().get(0) <= -4f && camera.getPosition().get(2) >= -0.45f && camera.getPosition().get(2) <= -0.4f){
//            return true;
//        }

        for (Vector3f e :
             collisionPoint)  {
            if (v3R(posx, posy, posz, e.get(0), e.get(1), e.get(2)) < 0.1f){
                return true;
            }
        }

       for(int i = 0; i < this.brute.size(); i++){
           if (this.brute.get(i).get(1) < 0f){
               continue;
           }

           if (v3R(this.brute.get(i).get(0), this.brute.get(i).get(1), this.brute.get(i).get(2), posx, posy, posz) < 0.25f) {
               return true;
           }
       }

        return false;
    }

    void kill(){
        objects.set(1, new Sphere("deadbody", Arrays.asList(
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
        objects.get(1).scaleObject(0.15f, 0.15f, 0.15f);
        objects.get(1).rotateObject((float) Math.toRadians(90f), 0f, 0f, 1f);
        objects.get(1).setCenterPoint(Arrays.asList(this.res.get(0), this.res.get(1), this.res.get(2)));
        objects.get(1).translateObject(this.res.get(0), this.res.get(1), this.res.get(2));

        dead = true;
    }

    void revive() {
        objects.set(1, new Sphere("player", Arrays.asList(
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

        dead = false;
    }

    public void input(float x, float y, float z) {
        Vector2f displayVec = window.getMouseInput().getDisplVec();
        float cx = camera.getPosition().x;
        float cy = camera.getPosition().y;
        float cz = camera.getPosition().z;
        float ox = objects.get(2).getCenterPoint().get(0);
        float oy = objects.get(2).getCenterPoint().get(1);
        float oz = objects.get(2).getCenterPoint().get(2);
        camera.addRotation((float) Math.toRadians(0f), (float) Math.toRadians(displayVec.y * 0.25f));


                cx = camera.getPosition().x;
                cy = camera.getPosition().y;
                cz = camera.getPosition().z;
                objects.get(2).translateObject(cx - ox, cy - oy, cz - oz);
                if (displayVec.y != 0 ) {
                objects.get(2).rotateObject((float) -Math.toRadians(displayVec.y * 0.25f), 0f, 1f, 0f);
                // System.out.println(camera.getPosition());

                }
                objects.get(2).translateObject(
                        (float) (Math.cos(Math.toRadians(getAngle())) + 0.3f) * 0.3f,
                        -0.05f,
                        (float) (Math.sin(Math.toRadians(getAngle())) - 0.3f) * -0.3f
                );


//            float ox = objects.get(1).getCenterPoint().get(0);
//            float oy = objects.get(1).getCenterPoint().get(1);
//            float oz = objects.get(1).getCenterPoint().get(2);

//
//            // rotate object
//            objects.get(1).translateObject(-ox, -oy, -oz);
//            objects.get(1).rotateObject(-1*(float)Math.toRadians(displayVec.y * 0.25f), 0f,1f, 0f );
//            objects.get(1).translateObject(ox, oy, oz);

//             ox = objects.get(1).getCenterPoint().get(0);
//             oy = objects.get(1).getCenterPoint().get(1);
//             oz = objects.get(1).getCenterPoint().get(2);

        //  System.out.println(objects.get(1).getCenterPoint());
        // rotate camera
        // camera.translateCddddddddddddddamera(-cx, -cy, -cz);
        // glLoadIdentity();
//            glPushMatrix();
//            camera.getViewMatrix().lookAt(new Vector3f(cx, 0.25f, cz), new Vector3f(ox, oy, oz), new Vector3f(0, 1, 0));
//            glPopMatrix();
        // Set up the view transformation
        // glLoadMatrixf(camera.getViewMatrix().get(new float[16]));

        //    glRotatef(-1*(float)Math.toRadians(displayVec.y * 0.25f), 0f,1f, 0f );
        //     glTranslatef(cx,cy,cz);
//            camera.move(-1*(float)Math.toRadians(displayVec.y * 0.25f), 0, 0);

//            camera.translateCamera(-ox, -oy, -oz);
//            camera.rotateCamera(-1*(float)Math.toRadians(displayVec.y * 0.25f), 0f,1f, 0f );
//            camera.translateCamera(ox,oy+0.4f,oz+0.5f);

//            camera.translateCamera(-0.01f, 0f, 0f);

        if (window.getMouseInput().getScroll().y != 0) {
            projection.setFOV(projection.getFOV() - (window.getMouseInput().getScroll().y * 0.01f));
            window.getMouseInput().setScroll(new Vector2f());
        }

        if (window.isKeyPressed(GLFW_KEY_E)) {
            if (lastPressed == 0 || lastPressed + 1000 <= System.currentTimeMillis()) {
                lastPressed = System.currentTimeMillis();
                blackout = !blackout;
            }
        }

        float posx = camera.getPosition().get(0);
        float posy = camera.getPosition().get(1);
        float posz = camera.getPosition().get(2);
        // float posx = camera.getViewMatrix().get(2, 0);
        // float posy = camera.getViewMatrix().get(2, 1);
        // float posz = camera.getViewMatrix().get(2, 2);

        if (window.isKeyPressed(GLFW_KEY_V)){
            if (lastPressed3 == 0 || lastPressed3 + 1000 <= System.currentTimeMillis()) {
                lastPressed3 = System.currentTimeMillis();
                if (v3R(posx,posy,posz, 3.0734284f, 0.2f, -0.21852417f) < 0.2f){
                    camera.setPosition(-5.746422f, 0.2f, 2.4672282f);
                }


                if (v3R(posx,posy,posz, -5.746422f, 0.2f, 2.4672282f) < 0.2f){
                    camera.setPosition(3.0734284f, 0.2f, -0.21852417f);
                }
            }

        }



        if (window.getMouseInput().isLeftButtonPressed()) {
            if (v3R(objects.get(2).getCenterPoint().get(0), objects.get(2).getCenterPoint().get(1), objects.get(2).getCenterPoint().get(2), objects.get(1).getCenterPoint().get(0), objects.get(1).getCenterPoint().get(1), objects.get(1).getCenterPoint().get(2)) < 0.3f){
                if (!dead) {
                    kill();
                    dead = true;
                }
            }
            System.out.println(v3R(objects.get(2).getCenterPoint().get(0), objects.get(2).getCenterPoint().get(1), objects.get(2).getCenterPoint().get(2), objects.get(1).getCenterPoint().get(0), objects.get(1).getCenterPoint().get(1), objects.get(1).getCenterPoint().get(2)));
        }

        if (window.getMouseInput().isRightButtonPressed()){
            System.out.println("\nx: " + camera.getPosition().get(0) + "\ny: " + camera.getPosition().get(1) + "\nz: " + camera.getPosition().get(2) + "\n");
        }

        if (window.isKeyPressed(GLFW_KEY_R) && dead) {
            revive();
        }

        if (window.isKeyPressed(GLFW_KEY_W) && !collision_detection(posx, posy, posz - 0.2f) ) {
//                objects.get(0).setAngle(180f);
//                objects.get(1).translateObject(0f, 0f, -1*move);
            camera.moveForward(move);
//                if (!collideTable(x, y, z - 0.1f)) move_forward();
        }


        if (window.isKeyPressed(GLFW_KEY_S) && !collision_detection(posx, posy, posz + 0.2f) ) {
//                objects.get(0).setAngle(0f);
//                camera.translateCamera(0f, 0f, move);
            camera.moveBackwards(move);
//                if (!collideTable(x, y, z + 0.1f)) move_backward();
//                objects.get(1).translateObject(0f, 0f, move);
        }


        if (window.isKeyPressed(GLFW_KEY_A) && !collision_detection(posx - 0.2f, posy, posz) ) {
//                objects.get(0).setAngle(270);
//                camera.translateCamera(-move, 0f, 0f);
            camera.moveLeft(move);
//                if (!collideTable(x - 0.1f, y, z)) move_left();
//                objects.get(1).translateObject(-1*move, 0f, 0f);
        }


        if (window.isKeyPressed(GLFW_KEY_D) && !collision_detection(posx + 0.2f, posy, posz) ) {
//                objects.get(0).setAngle(90);
//                camera.moveRight(move);
//                camera.translateCamera(move, 0f, 0f);
//                objects.get(1).translateObject(move, 0f, 0f);
            camera.moveRight(move);
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

            camera.setPosition(objects.get(0).getCenterPoint().get(0), objects.get(0).getCenterPoint().get(1), objects.get(0).getCenterPoint().get(2));
            camera.addRotation(0, (float) Math.toRadians(1.2f));
            camera.setPosition(tempx, tempy, tempz);
            camera.moveLeft(0.035f);
        }
    }

    public float getAngle() {
        float angle = (float) Math.toDegrees(Math.atan2(
                camera.getViewMatrix().get(2, 2),
                camera.getViewMatrix().get(2, 0)
        ));

        if (angle < 0) {
            angle += 360;
        }

        return angle;
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
            } else if (legDirection.equals("back")) {
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
            } else if (legDirection.equals("back")) {
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
        } else if (legDirection.equals("back")) {
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
        } else if (legDirection.equals("back")) {
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
    public void jump() {
        if (!jumped) {
            jumpup();
            if (objects.get(0).getCenterPoint().get(1) >= 0.2f) jumped = true;
        } else {
            jumpdown();
            if (objects.get(0).getCenterPoint().get(1) <= 0) {
                jumped = false;
                jumping = false;
            }
        }
    }

    public void jumpup() {
        objects.get(0).translateObject(0f, 0.01f, 0f);
        camera.moveUp(move - 0.01f);
    }

    public void jumpdown() {
        objects.get(0).translateObject(0f, -0.01f, 0f);
        camera.moveDown(move - 0.01f);
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

    public boolean isDie(){
        try {
            if (v3R(camera.getPosition().x, camera.getPosition().y, camera.getPosition().z, objects.get(1).getCenterPoint().get(0), objects.get(1).getCenterPoint().get(1), objects.get(1).getCenterPoint().get(2)) <= 2f){
                return true;
            }
        } catch (Exception i){
            return true;
        }
        return false;
    }

    public void loop() {
        while (window.isOpen()) {
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
            for (Object object : objects) {
                object.drawWithVerticesColor(camera, projection, blackout);
            }

            if (!dead) {
                if (lastPressed2 == 0 || lastPressed2 + 1000 <= System.currentTimeMillis()) {
                    lastPressed2 = System.currentTimeMillis();
                    if (blackout) random_position();
                }
            }

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

    public float v3R(float x1, float y1, float z1, float x2, float y2, float z2) {
        return (float) Math.sqrt(Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y2 - y1), 2) + Math.pow(Math.abs(z2 - z1), 2));
    }

    List<Vector3f> ranPos = Arrays.asList(
            new Vector3f(0.06321958f, 0.2f, 0.08278177f),

            new Vector3f(0.07632024f,
    0.2f,
    1.8017201f),

    new Vector3f(2.0449057f,
    0.2f,
    -0.8915209f),

    new Vector3f(0.018342175f,
    0.2f,
    -3.2758892f),

    new Vector3f(2.2465353f,
    0.2f,
    -0.93996906f)

            );

    public void random_position(){
        Random r = new Random();
        int c = ranPos.size();
        int i = r.nextInt(c);
        objects.get(1).rotateObject((float) Math.toRadians(r.nextFloat(360f)), 0f, 1f, 0f);
        objects.get(1).translateObject(ranPos.get(i).get(0)-objects.get(1).getCenterPoint().get(0), 0f, ranPos.get(i).get(2)-objects.get(1).getCenterPoint().get(2));

        this.res = new Vector3f(objects.get(1).getCenterPoint().get(0), 0f, objects.get(1).getCenterPoint().get(2));
    }

    Vector3f res;

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