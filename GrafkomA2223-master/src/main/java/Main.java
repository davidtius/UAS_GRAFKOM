import Engine.*;
import Engine.Object;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL30.*;

public class Main {
    private Window window =
            new Window
                    (800,800,"Hello World");
    private ArrayList<Object> objects
            = new ArrayList<>();
    private ArrayList<Object> objectPeluru = new ArrayList<>();
    public String statusBody = "null";
    private MouseInput mouseInput;
    Projection projection = new Projection(window.getWidth(), window.getHeight());
    Camera camera = new Camera();
    String legDirection = "front";
    int countDegree = 0;
    boolean isVentOpened = false;
    boolean isVented = false;
    boolean isVenting = false;
    Object ventSrc, ventDest;
    float move = .02f;

    public void init(){
        window.init();
        GL.createCapabilities();
        mouseInput = window.getMouseInput();
        camera.setPosition(0, 0.25f, 1.7f);
//        camera.setRotation((float) Math.toRadians(0f), (float) Math.toRadians(30f));
        objects.add(new Sphere("Blend",Arrays.asList(
                new ShaderProgram.ShaderModuleData("resources/shaders/sceneWithVerticesColor.vert", GL_VERTEX_SHADER),
                new ShaderProgram.ShaderModuleData("resources/shaders/sceneWithVerticesColor.frag", GL_FRAGMENT_SHADER)
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
//        objects.get(0).rotateObject((float) Math.toRadians(180f), 0f, 1f, 0f);
//        objects.get(0).rotateObject((float) Math.toRadians(180f), 0.0f, 1f, 0f);

//BADAN
//        objects.add(new Sphere("body",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f,.0f,0.0f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.12f,
//                0.12f,
//                0.12f,
//                348,
//                348,
//                180f
//        ));
//
//        objects.get(0).rotateObject((float) Math.toRadians(270f),1.0f,.0f,.0f);
//
////KAKI
//        objects.get(0).getChildObject().add(new Sphere("leg",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f,0.0f,0.0f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.05f,
//                0.05f,
//                0.1f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(0).getChildObject().get(0).translateObject(0.065f,0.f,-0.14f);
//        objects.get(0).getChildObject().get(0).setCenterPoint(Arrays.asList(0.25f,0.0f,0.0f));
//        objects.get(0).getChildObject().get(0).rotateObject((float) Math.toRadians(270f),1.0f,.0f,.0f);
//
//        objects.get(0).getChildObject().add(new Sphere("leg",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f,0.0f,0.0f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.05f,
//                0.05f,
//                0.1f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(0).getChildObject().get(1).translateObject(-0.065f,0.f,-0.14f);
//        objects.get(0).getChildObject().get(1).setCenterPoint(Arrays.asList(0.25f,0.0f,0.0f));
//        objects.get(0).getChildObject().get(1).rotateObject((float) Math.toRadians(270f),1.0f,.0f,.0f);
//
//
//        //KACAMATA
//        objects.get(0).getChildObject().add(new Sphere("glasses",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.0f,1f,1f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.08f,
//                0.04f,
//                0.06f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(0).getChildObject().get(2).translateObject(.0f, .0f, -0.09f);
//
//
////TAS
//        objects.get(0).getChildObject().add(new Sphere("bag",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1.0f,0f,0f,1.0f),
//                Arrays.asList(0.0f,-0.04f,0.0f),
//                0.12f,
//                0.12f,
//                0.12f,
//                108,
//                72,
//                180f
//        ));
//        objects.get(0).getChildObject().get(3).translateObject(.0f, .0f, 0.12f);
//
//        // PISTOL
//
//        objects.get(0).getChildObject().add(new Sphere("gun",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(.0f,0f,0f,0.0f),
//                Arrays.asList(0.15f,-0.08f,0.0f),
//                0.03f,
//                0.08f,
//                0.05f,
//                108,
//                72,
//                180f
//        ));
//        objects.get(0).getChildObject().get(4).getChildObject().add(new Sphere("gun",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(.0f,0.0f,0.0f,0.0f),
//                Arrays.asList(0.15f,-0.05f,0.06f),
//                0.03f,
//                0.08f,
//                0.04f,
//                108,
//                72,
//                180f
//        ));
//        objects.get(0).getChildObject().get(4).getChildObject().add(new Sphere("bag",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.f,0.f,0.f,1.0f),
//                Arrays.asList(0.15f,-0.05f,-0.04f),
//                0.03f,
//                0.04f,
//                0.12f,
//                108,
//                72,
//                180f
//        ));
//        objects.get(0).getChildObject().get(4).getChildObject().get(0).rotateObject((float)Math.toRadians(90f),1.0f,0.0f,0.0f);
//
//        //PISAU
//        objects.get(0).getChildObject().add(new Sphere("bag",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(.0f,0f,0f,0.0f),
//                Arrays.asList(-0.15f,-0.08f,0.0f),
//                0.02f,
//                0.03f,
//                0.06f,
//                108,
//                72,
//                180f
//        ));
//
//        //    MEDBAY CENTER
//        objects.add(new Sphere("medbay", Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(.75f,.75f,.75f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.2f,
//                0.2f,
//                0.05f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(1).rotateObject((float) Math.toRadians(270f),1.0f,0.0f,0.0f);
//        objects.get(1).translateObject(2f,-.2f,-0f);
//
////        MEDBAY LEG
//        objects.get(1).getChildObject().add(new Sphere("medleg", Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(.7f,.7f,.7f,1.0f),
//                Arrays.asList(0.0f, 0.0f, 0.0f),
//                0.035f,
//                0.035f,
//                0.05f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(1).getChildObject().get(0).rotateObject((float) Math.toRadians(270f),1.0f,0.0f,0.0f);
//        objects.get(1).getChildObject().get(0).translateObject(2.2f,-.2f,-0.f);
//
//        objects.get(1).getChildObject().add(new Sphere("medleg", Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(.7f,.7f,.7f,1.0f),
//                Arrays.asList(0.0f, 0.0f, 0.0f),
//                0.035f,
//                0.02f,
//                0.05f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(1).getChildObject().get(1).rotateObject((float) Math.toRadians(270f),1.0f,0.0f,0.0f);
//        objects.get(1).getChildObject().get(1).translateObject(1.8f,-.2f,-0.f);
//
//        objects.get(1).getChildObject().add(new Sphere("medleg", Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(.7f,.7f,.7f,1.0f),
//                Arrays.asList(0.0f, 0.0f, 0.0f),
//                0.035f,
//                0.02f,
//                0.05f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(1).getChildObject().get(2).rotateObject((float) Math.toRadians(270f),1.0f,0.0f,0.0f);
//        objects.get(1).getChildObject().get(2).translateObject(2f,-.2f,-0.2f);
//
//        objects.get(1).getChildObject().add(new Sphere("medleg", Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(.7f,.7f,.7f,1.0f),
//                Arrays.asList(0.0f, 0.0f, 0.0f),
//                0.035f,
//                0.02f,
//                0.05f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(1).getChildObject().get(3).rotateObject((float) Math.toRadians(270f),1.0f,0.0f,0.0f);
//        objects.get(1).getChildObject().get(3).translateObject(2f,-0.2f,.2f);
//
//        //SCANNER
//        objects.get(1).getChildObject().add(new Circle( Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(0.0625f,0.785f,.0625f,1.0f),
//                Arrays.asList(0.0f, 0.0f, 0.0f),
//                0.16f,
//                0.16f
//        ));
//
//        objects.get(1).getChildObject().get(4).rotateObject((float) Math.toRadians(270f),1.0f,0.0f,0.0f);
//        objects.get(1).getChildObject().get(4).translateObject(2f,-.199f,-0f);
//
//        objects.get(1).getChildObject().add(new Circle( Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(0.7f,0.7f,.7f,1.0f),
//                Arrays.asList(0.0f, 0.0f, 0.0f),
//                0.16f,
//                0.16f
//        ));
//
//        objects.get(1).getChildObject().get(5).rotateObject((float) Math.toRadians(270f),1.0f,0.0f,0.0f);
//        objects.get(1).getChildObject().get(5).translateObject(2f,-.19999f,-0f);
//
//        //BEZIER CABLE
//        objects.get(1).getChildObject().add(new Bezier( Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(0f,0.f,1.0f,1.0f),
//                Arrays.asList(
//                        new float[]{0,0,0},
//                        new float[]{0.05f, -0.05f, 0},
//                        new float[]{0.07f, 0.08f, 0},
//                        new float[]{0.09f, -0.02f,0},
//                        new float[]{.1f, 0.01f,0},
//                        new float[]{.12f, -0.05f,0},
//                        new float[]{.14f, .01f, 0},
//                        new float[]{.145f, -0.03f, 0},
//                        new float[]{.155f, 0,0},
//                        new float[]{.16f, -.2f,0}
//                )
//        ));
//
//        objects.get(1).getChildObject().get(6).rotateObject((float) Math.toRadians(30f),.0f,1.0f,0.0f);
//        objects.get(1).getChildObject().get(6).translateObject(2.2f, -0.2f, 0f);
//
//        objects.get(1).getChildObject().add(new Bezier( Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(10f,10.f,.0f,1.0f),
//                Arrays.asList(
//                        new float[]{0,0,0},
//                        new float[]{0.03f, 0.05f, 0},
//                        new float[]{0.06f, -0.08f, 0},
//                        new float[]{0.09f, 0.02f,0},
//                        new float[]{.1f, 0.01f,0},
//                        new float[]{.12f, 0.05f,0},
//                        new float[]{.14f, .01f, 0},
//                        new float[]{.145f, 0.03f, 0},
//                        new float[]{.155f, 0,0},
//                        new float[]{.16f, -.2f,0}
//                )
//        ));
//
//        objects.get(1).getChildObject().get(7).rotateObject((float) Math.toRadians(-30f),.0f,1.0f,0.0f);
//        objects.get(1).getChildObject().get(7).translateObject(2.2f, -0.2f, 0f);
//
//        // LANTAI
//        objects.add(new Sphere("bag",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.88f,0.77f,0.6f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                5f,
//                0.02f,
//                5f,
//                108,
//                72,
//                180f
//        ));
//        objects.get(2).translateObject(.0f, -0.265f, 0f);
//
//
//        // DINDING
//        objects.get(2).getChildObject().add(new Sphere("bag",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.49f,0.62f,0.675f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                5f,
//                2f,
//                .005f,
//                108,
//                72,
//                180f
//        ));
//        objects.get(2).getChildObject().get(0).translateObject(.0f, 0.75f, -2.5f);
//
//        objects.get(2).getChildObject().add(new Sphere("bag",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.49f,0.62f,0.675f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                5f,
//                2f,
//                .005f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(2).getChildObject().get(1).rotateObject((float) Math.toRadians(270f), 0f, 1f, 0f);
//        objects.get(2).getChildObject().get(1).translateObject(-2.5f, 0.75f, 0.0f);
//
//
//        objects.get(2).getChildObject().add(new Sphere("bag",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.49f,0.62f,0.675f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                5f,
//                2f,
//                .005f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(2).getChildObject().get(2).rotateObject((float) Math.toRadians(90f), 0f, 1f, 0f);
//        objects.get(2).getChildObject().get(2).translateObject(2.5f, 0.75f, 0.0f);
//
//        // MEJA
//        objects.get(2).getChildObject().add(new Sphere("tablebase",Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(0.4f, 0.4f, 0.4f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.075f,
//                0.075f,
//                0.02f,
//                360,
//                180,
//                180f
//        ));
//
//        objects.get(2).getChildObject().add(new Sphere("leg",Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(0.6f, 0.6f, 0.6f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.75f,
//                0.75f,
//                0.05f,
//                360,
//                180,
//                180f
//        ));
//
//        objects.get(2).getChildObject().add(new Sphere("tablebase",Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(0.4f, 0.4f, 0.4f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.075f,
//                0.075f,
//                0.02f,
//                360,
//                180,
//                180f
//        ));
//
//        objects.get(2).getChildObject().add(new Sphere("leg",Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(0.6f, 0.6f, 0.6f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.75f,
//                0.75f,
//                0.05f,
//                360,
//                180,
//                180f
//        ));
//        objects.get(2).getChildObject().add(new Sphere("button",Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(0f, 0f, 0f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.2f,
//                0.05f,
//                0.2f,
//                360,
//                180,
//                180f
//        ));
//        objects.get(2).getChildObject().add(new Sphere("leg",Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(1f, 0f, 0f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.06f,
//                0.05f,
//                0.06f,
//                360,
//                180,
//                180f
//        ));
//        objects.get(2).getChildObject().add(new Sphere("bag",Arrays.asList(
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//        ),
//                new ArrayList<>(),
//                new Vector4f(1f, 1f, 1f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.2f,
//                0.1f,
//                0.2f,
//                360,
//                180,
//                180f
//        ));
//
//
//        objects.get(2).getChildObject().get(3).rotateObject((float) Math.toRadians(180f), 1f, 0f, 0f);
//        objects.get(2).getChildObject().get(3).translateObject(-1.25f, -0.1f, 1.25f);
//
//        objects.get(2).getChildObject().get(4).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
//        objects.get(2).getChildObject().get(4).translateObject(-1.25f, -0.1f, 1.25f);
//
//        objects.get(2).getChildObject().get(5).rotateObject((float) Math.toRadians(180f), 1f, 0f, 0f);
//        objects.get(2).getChildObject().get(5).translateObject(1.25f, -0.1f, -1.25f);
//
//        objects.get(2).getChildObject().get(6).rotateObject((float) Math.toRadians(90f), 1f, 0f, 0f);
//        objects.get(2).getChildObject().get(6).translateObject(1.25f, -0.1f, -1.25f);
//
//        objects.get(2).getChildObject().get(7).translateObject(1.25f,-0.05f,-1.25f);
//        objects.get(2).getChildObject().get(8).rotateObject((float) Math.toRadians(90f),1f,0f,0f);
//        objects.get(2).getChildObject().get(8).translateObject(1.25f,-0.045f,-1.28f);
//        objects.get(2).getChildObject().get(9).translateObject(1.25f,0.02f,-1.25f);
//
//        //MAYAT
//        objects.add(new Sphere("leg",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0f,1f,0.f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.12f,
//                0.12f,
//                0.12f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(3).rotateObject((float) Math.toRadians(270f),1.0f,.0f,.0f);
//        objects.get(3).translateObject(-2f, -.12f, -2f);
//
//        //badan dalam
//        objects.get(3).getChildObject().add(new Sphere("leg",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1f,0f,0.f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.10f,
//                0.10f,
//                0.10f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(3).getChildObject().get(0).rotateObject((float) Math.toRadians(270f),1.0f,.0f,.0f);
//        objects.get(3).getChildObject().get(0).translateObject(0f,0.00009f, 0f);
//        objects.get(3).getChildObject().get(0).translateObject(-2f,-.12f, -2f);
//
//        //tulang
//        objects.get(3).getChildObject().add(new Sphere("leg",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1f,1f,1f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.02f,
//                0.02f,
//                0.15f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(3).getChildObject().get(1).rotateObject((float) Math.toRadians(270f),1.0f,.0f,.0f);
//        objects.get(3).getChildObject().get(1).translateObject(0f,0.05f, 0f);
//        objects.get(3).getChildObject().get(1).translateObject(-2f,-.12f, -2f);
//
//        objects.get(3).getChildObject().add(new Sphere("sphere",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1f,1f,1f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.02f,
//                0.02f,
//                0.02f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(3).getChildObject().get(2).translateObject(0.02f,0.05f, 0f);
//        objects.get(3).getChildObject().get(2).rotateObject((float) Math.toRadians(45f), 0.0f,1.0f, 0f);
//        objects.get(3).getChildObject().get(2).translateObject(-2f,-.12f, -2f);
//
//        objects.get(3).getChildObject().add(new Sphere("sphere",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(1f,1f,1f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.02f,
//                0.02f,
//                0.02f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(3).getChildObject().get(3).translateObject(-0.02f,0.05f, 0f);
//        objects.get(3).getChildObject().get(3).rotateObject((float) Math.toRadians(45f), 0.0f,1.0f, 0f);
//        objects.get(3).getChildObject().get(3).translateObject(-2f,-.12f, -2f);
//
//        //kaki
//        objects.get(3).getChildObject().add(new Sphere("leg",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(.0f,1.0f,0.0f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.05f,
//                0.05f,
//                0.1f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(3).getChildObject().get(4).translateObject(0.065f,-0.09f,0.14f);
//        objects.get(3).getChildObject().get(4).rotateObject((float) Math.toRadians(45f), 0.0f,1f, 0f);
//        objects.get(3).getChildObject().get(4).translateObject(-2f,-.12f, -2f);
//
//        objects.get(3).getChildObject().add(new Sphere("leg",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(.0f,1.0f,0.0f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.05f,
//                0.05f,
//                0.1f,
//                108,
//                72,
//                180f
//        ));
//
//        objects.get(3).getChildObject().get(5).translateObject(-0.065f,-0.09f,0.14f);
//        objects.get(3).getChildObject().get(5).rotateObject((float) Math.toRadians(45f), 0.0f,1f, 0f);
//        objects.get(3).getChildObject().get(5).translateObject(-2f,-.12f, -2f);
//
//        //vent 1
//        objects.add(new Sphere("bag",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.4f,.4f,.4f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.3f,
//                0.02f,
//                0.3f,
//                108,
//                72,
//                180f)
//        );
//        objects.get(4).translateObject(-0.85f,-0.23f,.0f);
//
//        //vent 2
//        objects.add(new Sphere("bag",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.4f,.4f,.4f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.3f,
//                0.02f,
//                0.3f,
//                108,
//                72,
//                180f)
//        );
//        objects.get(5).translateObject(0.85f,-0.23f,.0f);
//
//        objects.add(new Sphere("bag",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.f,.0f,.0f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.3f,
//                0.0f,
//                0.3f,
//                108,
//                72,
//                180f)
//        );
//
//        objects.get(6).translateObject(-0.85f,-0.23f,.0f);
//
//
//        objects.add(new Sphere("bag",
//                Arrays.asList(
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
//                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
//                ),
//                new ArrayList<>(),
//                new Vector4f(0.f,.0f,.0f,1.0f),
//                Arrays.asList(0.0f,0.0f,0.0f),
//                0.3f,
//                0.0f,
//                0.3f,
//                108,
//                72,
//                180f)
//        );
//
//        objects.get(7).translateObject(0.85f,-0.23f,.0f);
//
////        colors.add(new Vector4f(1f, 0f, 0f, 1f));
////        colors.add(new Vector4f(0f, 1f, 0f, 1f));
////        colors.add(new Vector4f(0f, 0f, 1f, 1f));
//
////        //BADAN 2
////        objects.add(new Sphere("body",
////                Arrays.asList(
////                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
////                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
////                ),
////                new ArrayList<>(),
////                new Vector4f(1.0f,1.0f,0.0f,1.0f),
////                Arrays.asList(0.0f,0.0f,0.0f),
////                0.12f,
////                0.12f,
////                0.12f,
////                348,
////                348,
////                180f
////        ));
////
////        objects.get(8).rotateObject((float) Math.toRadians(270f),1.0f,.0f,.0f);
////        objects.get(8).translateObject(0f,0f,-2f);
////
//////KAKI 2
////        objects.get(8).getChildObject().add(new Sphere("leg",
////                Arrays.asList(
////                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
////                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
////                ),
////                new ArrayList<>(),
////                new Vector4f(1.0f,1.0f,0.0f,1.0f),
////                Arrays.asList(0.0f,0.0f,0.0f),
////                0.05f,
////                0.05f,
////                0.1f,
////                108,
////                72,
////                180f
////        ));
////
////        objects.get(8).getChildObject().get(0).translateObject(0.065f,0.f,-0.14f);
////        objects.get(8).getChildObject().get(0).setCenterPoint(Arrays.asList(0.25f,0.0f,0.0f));
////        objects.get(8).getChildObject().get(0).rotateObject((float) Math.toRadians(270f),1.0f,.0f,.0f);
////        objects.get(8).getChildObject().get(0).translateObject(0.0f,0.f,-2f);
////
////        objects.get(8).getChildObject().add(new Sphere("leg",
////                Arrays.asList(
////                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
////                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
////                ),
////                new ArrayList<>(),
////                new Vector4f(1.0f,1.0f,0.0f,1.0f),
////                Arrays.asList(0.0f,0.0f,0.0f),
////                0.05f,
////                0.05f,
////                0.1f,
////                108,
////                72,
////                180f
////        ));
////
////        objects.get(8).getChildObject().get(1).translateObject(-0.065f,0.f,-0.14f);
////        objects.get(8).getChildObject().get(1).setCenterPoint(Arrays.asList(0.25f,0.0f,0.0f));
////        objects.get(8).getChildObject().get(1).rotateObject((float) Math.toRadians(270f),1.0f,.0f,.0f);
////        objects.get(8).getChildObject().get(1).translateObject(0.0f,0.f,-2f);
////
////
////        //KACAMATA 2
////        objects.get(8).getChildObject().add(new Sphere("glasses",
////                Arrays.asList(
////                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
////                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
////                ),
////                new ArrayList<>(),
////                new Vector4f(0.0f,1f,1f,1.0f),
////                Arrays.asList(0.0f,0.0f,0.0f),
////                0.08f,
////                0.04f,
////                0.06f,
////                108,
////                72,
////                180f
////        ));
////
////        objects.get(8).getChildObject().get(2).translateObject(.0f, .0f, 0.09f);
////        objects.get(8).getChildObject().get(2).translateObject(0.0f,0.f,-2f);
////
////
////        //TAS
////        objects.get(8).getChildObject().add(new Sphere("bag",
////                Arrays.asList(
////                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.vert", GL_VERTEX_SHADER),
////                        new ShaderProgram.ShaderModuleData("resources/shaders/scene.frag", GL_FRAGMENT_SHADER)
////                ),
////                new ArrayList<>(),
////                new Vector4f(1.0f,1f,0f,1.0f),
////                Arrays.asList(0.0f,-0.04f,0.0f),
////                0.12f,
////                0.12f,
////                0.12f,
////                108,
////                72,
////                180f
////        ));
////        objects.get(8).getChildObject().get(3).translateObject(.0f, .0f, -2.12f);
    }


    boolean cekscan = false;
    boolean scannow = true;
    boolean scanning = false;
    public void shoot() {
        if (objectPeluru.size()== 0) {
            objectPeluru.add(new Sphere("sphere",
                    Arrays.asList(
                            new ShaderProgram.ShaderModuleData("resources/shaders/sceneWithVerticesColor.vert", GL_VERTEX_SHADER),
                            new ShaderProgram.ShaderModuleData("resources/shaders/sceneWithVerticesColor.frag", GL_FRAGMENT_SHADER)
                    ),
                    new ArrayList<>(),
                    new Vector4f(1.0f,.0f,0.0f,1.0f),
                    Arrays.asList(0.0f,0.0f,0.0f),
                    0.01f,
                    0.01f,
                    0.01f,
                    108,
                    72,
                    180f
            ));
            objectPeluru.get(0).translateObject (

                    objects.get(0).getChildObject().get(4).getChildObject().get(1).getCenterPoint().get(0),
                    objects.get(0).getChildObject().get(4).getChildObject().get(1).getCenterPoint().get(1),
                    objects.get(0).getChildObject().get(4).getChildObject().get(1).getCenterPoint().get(2));
        }

//        System.out.println(objects.get(0).getChildObject().get(4).getChildObject().get(1).getCenterPoint());
//        System.out.println(objectPeluru.get(0).getCenterPoint());
//
//        System.out.println(statusBody);
        switch (statusBody) {
            case "depan":
                objectPeluru.get(0).translateObject(0.15f,-0.065f,-0.1f);
                while(objectPeluru.get(0).getCenterPoint().get(2)>= -2.5f) {
//                    System.out.println("tes");
                    objectPeluru.get(0).draw(camera,projection);
                    objectPeluru.get(0).translateObject(0f,0f,-0.01f);
                }
                objectPeluru.remove(0);
                break;

            case "belakang":
                objectPeluru.get(0).translateObject(-0.15f,-0.06f,0.0f);
                while(objectPeluru.get(0).getCenterPoint().get(2)<= 2.5f) {
//                    System.out.println("tes");
                    objectPeluru.get(0).draw(camera,projection);
                    objectPeluru.get(0).translateObject(0f,0f,0.01f);
                }
                objectPeluru.remove(0);
                break;

            case "kiri":
                objectPeluru.get(0).translateObject(-0.15f,-0.06f,0.0f);
                while(objectPeluru.get(0).getCenterPoint().get(0)>= -2.5f) {
//                    System.out.println("tes");
                    objectPeluru.get(0).draw(camera,projection);
                    objectPeluru.get(0).translateObject(-0.01f,0f,0.0f);
                }
                objectPeluru.remove(0);
                break;

            case "kanan":
                objectPeluru.get(0).translateObject(0.15f,-0.06f,0.1f);
                while(objectPeluru.get(0).getCenterPoint().get(0)<= 2.5f) {
//                    System.out.println("tes");
                    objectPeluru.get(0).draw(camera,projection);
                    objectPeluru.get(0).translateObject(0.01f,0f,0f);
//                    System.out.println(objectPeluru.get(0).getCenterPoint());
                }
                objectPeluru.remove(0);
                break;
        }
    }
    
    String currentColor = "red";

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

        public void input(float x, float y, float z) {
//        if (mouseInput.isLeftButtonPressed()) {
//            shoot();
//        }
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
                if (scannow && cekscan && !isVenting) {
                    scanning = true;
                    scannow = false;
                } else if (!isVenting && !scanning && !cekscan) {
                    float jarak1 = v3R(
                            objects.get(0).getCenterPoint().get(0),
                            objects.get(0).getCenterPoint().get(1),
                            objects.get(0).getCenterPoint().get(2),
                            objects.get(4).getCenterPoint().get(0),
                            objects.get(4).getCenterPoint().get(1),
                            objects.get(4).getCenterPoint().get(2)
                    );

                    float jarak2 = v3R(
                            objects.get(0).getCenterPoint().get(0),
                            objects.get(0).getCenterPoint().get(1),
                            objects.get(0).getCenterPoint().get(2),
                            objects.get(5).getCenterPoint().get(0),
                            objects.get(5).getCenterPoint().get(1),
                            objects.get(5).getCenterPoint().get(2)
                    );

                    if (jarak1 <= 0.35f) {
                        ventSrc = objects.get(4);
                        ventDest = objects.get(5);
                        isVenting = true;
                    } else if (jarak2 <= 0.35f) {
                        ventSrc = objects.get(5);
                        ventDest = objects.get(4);
                        isVenting = true;
                    }
                }
            }

            if (window.isKeyPressed(GLFW_KEY_W) && !scanning && !isVenting && (z > -2.3f)) {
//                objects.get(0).setAngle(180f);
                camera.moveForward(move);
//                if (!collideTable(x, y, z - 0.1f)) move_forward();
            }


            if (window.isKeyPressed(GLFW_KEY_S) && !scanning && !isVenting && (z < 2.3f)) {
//                objects.get(0).setAngle(0f);
                camera.moveBackwards(move);
//                if (!collideTable(x, y, z + 0.1f)) move_backward();
            }


            if (window.isKeyPressed(GLFW_KEY_A) && !scanning && !isVenting && (x > -2.3f)) {
//                objects.get(0).setAngle(270);
                camera.moveLeft(move);
//                if (!collideTable(x - 0.1f, y, z)) move_left();
            }


            if (window.isKeyPressed(GLFW_KEY_D) && !scanning && !isVenting && (x < 2.3f)) {
//                objects.get(0).setAngle(90);
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

                camera.setPosition(objects.get(0).getCenterPoint().get(0),objects.get(0).getCenterPoint().get(1),objects.get(0).getCenterPoint().get(2));
                camera.addRotation(0,(float) Math.toRadians(1.2f));
                camera.setPosition(tempx,tempy,tempz);
                camera.moveLeft(0.035f);
            }

            if (window.isKeyPressed(GLFW_KEY_SPACE) && !scanning && !isVenting) {
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

    public void vent(Object src, Object dest) {
        if (objects.get(0).getCenterPoint().get(0) <= src.getCenterPoint().get(0) + 0.25f &&
                objects.get(0).getCenterPoint().get(0) >= src.getCenterPoint().get(0) - 0.25f &&
                objects.get(0).getCenterPoint().get(2) <= src.getCenterPoint().get(2) + 0.25f &&
                objects.get(0).getCenterPoint().get(2) >= src.getCenterPoint().get(2) - 0.25f
        )
        {
            // open vent
            if (!isVentOpened) {
                openVent(objects.get(4), new Vector3f(-1f, -0.25f, 0f));
                openVent(objects.get(5), new Vector3f(0.7f, -0.25f, 0f));
            }

            // vent opened
            if (openVent(objects.get(4), new Vector3f(-1f, -0.25f, 0f)) && openVent(objects.get(5), new Vector3f(0.7f, -0.25f, 0f))) {
                isVentOpened = true;

                // character animation
                objects.get(0).translateObject(0f,-.02f,0f); // animasi turun

                if (objects.get(0).getCenterPoint().get(1) <= -0.5f && !isVented) {
                    System.out.println("vented!");
                    // teleport character
                    objects.get(0).translateObject(
                            dest.getCenterPoint().get(0) - src.getCenterPoint().get(0),
                            dest.getCenterPoint().get(1) - src.getCenterPoint().get(1),
                            dest.getCenterPoint().get(2) - src.getCenterPoint().get(2)
                    );
                    // change camera position
                    Vector3f temp = camera.getPosition();
                    camera.setPosition(dest.getCenterPoint().get(0), temp.y, temp.z);
                    isVented = true;
                }
            }
        }

        if (objects.get(0).getCenterPoint().get(0) <= dest.getCenterPoint().get(0) + 0.25f &&
                objects.get(0).getCenterPoint().get(0) >= dest.getCenterPoint().get(0) - 0.25f &&
                objects.get(0).getCenterPoint().get(2) <= dest.getCenterPoint().get(2) + 0.25f &&
                objects.get(0).getCenterPoint().get(2) >= dest.getCenterPoint().get(2) - 0.25f
        )
        {
            // close vent
            if (isVentOpened && objects.get(0).getCenterPoint().get(1) >= 0f) {
                closeVent(objects.get(4), new Vector3f(-1f, -0.25f, 0f),0.15f);
                closeVent(objects.get(5), new Vector3f(0.7f, -0.25f, 0f),0.15f);

                // vent closed
                if  (closeVent(objects.get(4), new Vector3f(-1f, -0.25f, 0f),0.15f) && closeVent(objects.get(5), new Vector3f(0.7f, -0.25f, 0f),0.15f)) {
                    isVentOpened = false;
                    isVented = false;
                    isVenting = false;
                }
            }

            // character animation
            if (isVented && isVentOpened) {
                if (objects.get(0).getCenterPoint().get(1) <= 0f)
                    objects.get(0).translateObject(0f, .02f, 0f); // animasi naik
            }
        }
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
                object.drawWithVerticesColor(camera, projection);
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