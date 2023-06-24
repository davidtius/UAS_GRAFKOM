package Engine;

import org.joml.Vector3f;
import org.joml.Vector4f;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

public class Sphere extends Circle{
    float radiusZ;
    int stackCount;
    int sectorCount;
    List<Vector3f> normal;
    float angle;
    int nbo;
    int nboColor;
    Model m;
    List<Material> material;

    public Sphere(String type, List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, Vector4f color, List<Float> centerPoint, Float radiusX, Float radiusY, Float radiusZ,
                  int sectorCount,int stackCount, float angle) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);
        this.radiusZ = radiusZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        this.angle = angle;
//        createBox();
        switch (type){
            case "body": body();break;
            case "sphere" :createSphere(); break;
            default: bag(); break;
            case "leg" : leg(); break;
            case "glasses": glasses(); break;
            case "bag": bag(); break;
            case "medbay" : medbay();break;
            case "medleg":medleg();break;
            case "room" : room(); break;
            case "tablebase" : EllipticCone(); break;
            case "gun" : gun(); break;
            case "knife" : knife();break;
            case "button" : button();break;
            case "Blend" : loadMap();break;
        }
        setupVAOVBO();
    }

    public Sphere(String type, List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices, List<Vector3f> color, List<Float> centerPoint, Float radiusX, Float radiusY, Float radiusZ,
                  int sectorCount,int stackCount, float angle) {
        super(shaderModuleDataList, vertices, color, centerPoint, radiusX, radiusY);

        this.radiusZ = radiusZ;
        this.stackCount = stackCount;
        this.sectorCount = sectorCount;
        this.angle = angle;
//        createBox();
        switch (type){
            case "body": body();break;
            case "sphere" :createSphere(); break;
            default: bag(); break;
            case "leg" : leg(); break;
            case "glasses": glasses(); break;
            case "bag": bag(); break;
            case "medbay" : medbay();break;
            case "medleg":medleg();break;
            case "room" : room(); break;
            case "tablebase" : EllipticCone(); break;
            case "gun" : gun(); break;
            case "knife" : knife();break;
            case "button" : button();break;
            case "Blend" : loadMap();break;
            case "player" : loadPlayer(); break;
        }
        setupVAOVBOWithVerticesColor();
    }

    public void loadPlayer() {
        vertices.clear();

        try {
            m = ObjLoader.loadModel(new File("src/main/java/amongus.obj"), "src/main/java/amongus.mtl");
            // material = ObjLoader.loadMTLFile("src/main/java/amongus.mtl");
        } catch (IOException e) {
            e.printStackTrace();
        }

        m.getSize();
        normal = new ArrayList<>();
        for(Face face : m.faces){
            Vector3f n1 = m.normals.get((int) face.normal.x-1);
            normal.add(n1);
            Vector3f v1 = m.vertices.get((int) face.vertex.x-1);
            vertices.add(v1);
            verticesColor.add(face.color);

            Vector3f n2 = m.normals.get((int) face.normal.y-1);
            normal.add(n2);
            Vector3f v2 = m.vertices.get((int) face.vertex.y-1);
            vertices.add(v2);
            verticesColor.add(face.color);

            Vector3f n3 = m.normals.get((int) face.normal.z-1);
            normal.add(n3);
            Vector3f v3 = m.vertices.get((int) face.vertex.z-1);
            vertices.add(v3);
            verticesColor.add(face.color);
        }

        // System.out.println(verticesColor);
    }
    public void loadMap() {
        vertices.clear();
//        Vector3f temp = new Vector3f();
//        ArrayList<Vector3f> tempVertices = new ArrayList<>();

        try {
            m = ObjLoader.loadModel(new File("src/main/java/Engine/map.obj"), "src/main/java/Engine/map.mtl");
            // material = ObjLoader.loadMTLFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        m.getSize();
        normal = new ArrayList<>();
        for(Face face : m.faces){
            Vector3f n1 = m.normals.get((int) face.normal.x-1);
            normal.add(n1);
            Vector3f v1 = m.vertices.get((int) face.vertex.x-1);
            vertices.add(v1);
            verticesColor.add(face.color);

            Vector3f n2 = m.normals.get((int) face.normal.y-1);
            normal.add(n2);
            Vector3f v2 = m.vertices.get((int) face.vertex.y-1);
            vertices.add(v2);
            verticesColor.add(face.color);

            Vector3f n3 = m.normals.get((int) face.normal.z-1);
            normal.add(n3);
            Vector3f v3 = m.vertices.get((int) face.vertex.z-1);
            vertices.add(v3);
            verticesColor.add(face.color);
        }
    }

    public void setupVAOVBO() {
        super.setupVAOVBO();
        nbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(normal),
                GL_STATIC_DRAW);
    }

    public void setupVAOVBOWithVerticesColor(){
        super.setupVAOVBOWithVerticesColor();
        nbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(normal),
                GL_STATIC_DRAW);

//        textCoords = glGenBuffers();
//        glBindBuffer(GL_ARRAY_BUFFER, textCoords);
//        glBufferData(GL_ARRAY_BUFFER,
//                Utils.listoFloat(textures),
//                GL_STATIC_DRAW);

        //set nboColor
        nboColor = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, nboColor);
        glBufferData(GL_ARRAY_BUFFER,
                Utils.listoFloat(verticesColor),
                GL_STATIC_DRAW);
    }

    public void drawSetup(Camera camera, Projection projection, Vector3f ambientStrength){
        super.drawSetup(camera,projection, ambientStrength);
        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glVertexAttribPointer(1, 3,
                GL_FLOAT,
                false,
                0, 0);
        //directional Light
        uniformsMap.setUniform("lightColor",new Vector3f(1.0f,1.0f,0.0f));
        uniformsMap.setUniform("lightPos",new Vector3f(1.0f,1.0f,0.0f));
        uniformsMap.setUniform("viewPos",camera.getPosition());
    }

    public void drawSetupWithVerticesColor(Camera camera, Projection projection, boolean blackout){
        super.drawSetupWithVerticesColor(camera, projection, blackout);

        glEnableVertexAttribArray(1);
        glBindBuffer(GL_ARRAY_BUFFER, nboColor);
        glVertexAttribPointer(1, 3,
                GL_FLOAT,
                false,
                0, 0);

        glEnableVertexAttribArray(2);
        glBindBuffer(GL_ARRAY_BUFFER, nbo);
        glVertexAttribPointer(2, 3,
                GL_FLOAT,
                false,
                0, 0);

//        glEnableVertexAttribArray(3);
//        glBindBuffer(GL_ARRAY_BUFFER, textCoords);
//        glVertexAttribPointer(3, 3,
//                GL_FLOAT,
//                false,
//                0, 0);

        // uniformsMap.setUniform("lightColor",new Vector3f(1.0f,1.0f,1.0f));
        // uniformsMap.setUniform("lightPos",new Vector3f(1.0f,1.0f,0.0f));
        // uniformsMap.setUniform("viewPos",camera.getPosition());

        Vector3f[] hallLights = {
                    // hall atas
                    new Vector3f(-3.5f, 0.25f, -1.5f),
                    new Vector3f(-5.05f, 0.25f, -1.5f),
                    new Vector3f(-6.6f, 0.25f, -1.5f),
                    new Vector3f(-8f, 0.25f, -1.5f),
                    new Vector3f(-9.15f, 0.25f, -1.9f),
                    new Vector3f(-10.25f, 0.25f, -1.9f),

                    new Vector3f(-3.5f, 0.25f, -0.7f),
                    new Vector3f(-4.35f, 0.25f, -0.7f),
                    new Vector3f(-5.4f, 0.25f, -0.7f),
                    new Vector3f(-6.6f, 0.25f, -0.7f),
                    new Vector3f(-8f, 0.25f, -0.7f),
                    new Vector3f(-8.85f, 0.25f, 0.57f),
                    new Vector3f(-10.55f, 0.25f, 0.57f),

                    // hall kiri
                    // new Vector3f(-9.75f, 0.25f, 1.05f),
                    // new Vector3f(-9.75f, 0.25f, 2.15f),
                    // new Vector3f(-9.75f, 0.25f, 3.6f),
                    // new Vector3f(-9.75f, 0.25f, 4.75f),
                    // new Vector3f(-9.75f, 0.25f, 5.8f),
                    // new Vector3f(-9.75f, 0.25f, 6.75f),

                    // new Vector3f(-9.3f, 0.25f, 1.05f),
                    // new Vector3f(-9.3f, 0.25f, 2.15f),
                    // new Vector3f(-9.3f, 0.25f, 3.6f),
                    // new Vector3f(-9.3f, 0.25f, 4.75f),
                    // new Vector3f(-8.5f, 0.25f, 5.55f),

                    // hall bawah
                    // new Vector3f(-9.3f, 0.25f, 7f),
                    // new Vector3f(-8.5f, 0.25f, 7f),
                    // new Vector3f(-7.75f, 0.25f, 7f),
                    // new Vector3f(-7.45f, 0.25f, 6.25f),
                    // new Vector3f(-6.5f, 0.25f, 6.5f),
                    // new Vector3f(-6.5f, 0.25f, 7.5f),
                    // new Vector3f(-7.05f, 0.25f, 7.6f),
                    // new Vector3f(-7.05f, 0.25f, 8.65f),

                    // new Vector3f(-6.35f, 0.25f, 8.9f),
                    // new Vector3f(-5.1f, 0.25f, 8.9f),
                    // new Vector3f(-3.55f, 0.25f, 8.9f),
                    // new Vector3f(-6f, 0.25f, 7.9f),
                    // new Vector3f(-4.5f, 0.25f, 7.9f),
                    // new Vector3f(-2.85f, 0.25f, 7.9f),

                    // storage room
                    new Vector3f(-2.1f, 0.25f, 9.6f),
                    new Vector3f(-1.5f, 0.25f, 10f),
                    new Vector3f(-0.75f, 0.25f, 10.15f),
                    new Vector3f(0.25f, 0.25f, 10.15f),
                    new Vector3f(0.75f, 0.25f, 9.25f),
                    new Vector3f(0.75f, 0.25f, 8.25f),
                    new Vector3f(0.75f, 0.25f, 7.25f),
                    new Vector3f(0.75f, 0.25f, 6.25f),
                    new Vector3f(0.75f, 0.25f, 5.25f),
                    new Vector3f(-2.35f, 0.25f, 7.25f),
                    new Vector3f(-2.35f, 0.25f, 6.15f),
                    new Vector3f(-0.95f, 0.25f, 5f),

                    // mid connector
                    new Vector3f(-0.3f, 0.25f, 4.45f),
                    new Vector3f(-0.3f, 0.25f, 2.8f),
                    new Vector3f(0.3f, 0.25f, 4.45f),
                    new Vector3f(0.3f, 0.25f, 2.8f),
            };

            Vector3f[] monitorLights = {
                    // new Vector3f(-7.3f, 0.3f, 1.25f),
                    // new Vector3f(0.010217f, 0.041753f, 0.800000f),
                    new Vector3f(-3.15f, 0.25f, 2.15f),
                    new Vector3f(0.355600f, 0.800000f, 0.424038f),
            };

        if (!blackout) {
            //directional Light
            uniformsMap.setUniform("dirLight.direction", new Vector3f(0f, 3f, 0f));
            uniformsMap.setUniform("dirLight.ambient", new Vector3f(.5f, .5f, .5f));
            uniformsMap.setUniform("dirLight.diffuse", new Vector3f(0.8f, 0.8f, 0.8f));
            uniformsMap.setUniform("dirLight.specular", new Vector3f(1.0f, 1.0f, 1.0f));

            for (int i = 0; i < hallLights.length; i++) {
                uniformsMap.setUniform("pointLights[" + i + "].position", hallLights[i]);
                uniformsMap.setUniform("pointLights[" + i + "].ambient", new Vector3f(-0.35f, -0.35f, -0.35f));
                uniformsMap.setUniform("pointLights[" + i + "].diffuse", new Vector3f(1f, 1f, 1f));
                uniformsMap.setUniform("pointLights[" + i + "].specular", new Vector3f(0.5f, 0.5f, 0.5f));
                uniformsMap.setUniform("pointLights[" + i + "].constant", 1.0f);
                uniformsMap.setUniform("pointLights[" + i + "].linear", 0.7f);
                uniformsMap.setUniform("pointLights[" + i + "].quadratic", 1.8f);
            }

            for (int i = 0; i < monitorLights.length; i+=2) {
                uniformsMap.setUniform("pointLights[" + (i/2+29) + "].position", monitorLights[i]);
                uniformsMap.setUniform("pointLights[" + (i/2+29) + "].ambient", new Vector3f(-0.5f, -0.5f, -0.5f));
                uniformsMap.setUniform("pointLights[" + (i/2+29) + "].diffuse", monitorLights[i+1]);
                uniformsMap.setUniform("pointLights[" + (i/2+29) + "].specular", new Vector3f(1f, 1f, 1f));
                uniformsMap.setUniform("pointLights[" + (i/2+29) + "].constant", 1.0f);
                uniformsMap.setUniform("pointLights[" + (i/2+29) + "].linear", 0.7f);
                uniformsMap.setUniform("pointLights[" + (i/2+29) + "].quadratic", 1.8f);
            }
            // no spotlight
            uniformsMap.setUniform("spotLight.position", camera.getPosition());
            uniformsMap.setUniform("spotLight.direction", camera.getDirection());
            uniformsMap.setUniform("spotLight.ambient", new Vector3f(0.0f, 0.0f, 0.0f));
            uniformsMap.setUniform("spotLight.diffuse", new Vector3f(0.0f, 0.0f, 0.0f));
            uniformsMap.setUniform("spotLight.specular", new Vector3f(0.0f, 0.0f, 0.0f));
            uniformsMap.setUniform("spotLight.constant", 1.0f);
            uniformsMap.setUniform("spotLight.linear", 0.00f);
            uniformsMap.setUniform("spotLight.quadratic", 0.00f);
            uniformsMap.setUniform("spotLight.cutOff", (float) Math.cos(Math.toRadians(20f)));
            uniformsMap.setUniform("spotLight.outerCutOff", (float) Math.cos(Math.toRadians(21f)));

            uniformsMap.setUniform("viewPos",camera.getPosition());
            }

        else {
            //directional Light
            uniformsMap.setUniform("dirLight.direction", new Vector3f(0f, 3f, 0f));
            uniformsMap.setUniform("dirLight.ambient", new Vector3f(.1f, .1f, .1f));
            uniformsMap.setUniform("dirLight.diffuse", new Vector3f(0.8f, 0.8f, 0.8f));
            uniformsMap.setUniform("dirLight.specular", new Vector3f(1.0f, 1.0f, 1.0f));

            for (int i = 0; i < hallLights.length; i++) {
                uniformsMap.setUniform("pointLights[" + i + "].position", hallLights[i]);
                uniformsMap.setUniform("pointLights[" + i + "].ambient", new Vector3f(0f, 0f, 0f));
                uniformsMap.setUniform("pointLights[" + i + "].diffuse", new Vector3f(0f, 0f, 0f));
                uniformsMap.setUniform("pointLights[" + i + "].specular", new Vector3f(0f, 0f, 0f));
                uniformsMap.setUniform("pointLights[" + i + "].constant", 1.0f);
                uniformsMap.setUniform("pointLights[" + i + "].linear", 0.7f);
                uniformsMap.setUniform("pointLights[" + i + "].quadratic", 1.8f);
            }

            for (int i = 0; i < monitorLights.length; i+=2) {
                uniformsMap.setUniform("pointLights[" + (i/2+54) + "].position", monitorLights[i]);
                uniformsMap.setUniform("pointLights[" + (i/2+54) + "].ambient", new Vector3f(0f, 0f, 0f));
                uniformsMap.setUniform("pointLights[" + (i/2+54) + "].diffuse", new Vector3f(0f, 0f, 0f));
                uniformsMap.setUniform("pointLights[" + (i/2+54) + "].specular", new Vector3f(0f, 0f, 0f));
                uniformsMap.setUniform("pointLights[" + (i/2+54) + "].constant", 1.0f);
                uniformsMap.setUniform("pointLights[" + (i/2+54) + "].linear", 0.7f);
                uniformsMap.setUniform("pointLights[" + (i/2+54) + "].quadratic", 1.8f);
            }
            // spotlight on
            uniformsMap.setUniform("spotLight.position",camera.getPosition());
            uniformsMap.setUniform("spotLight.direction",camera.getDirection());
            uniformsMap.setUniform("spotLight.ambient",new Vector3f(.05f, .05f, .05f));
            uniformsMap.setUniform("spotLight.diffuse",new Vector3f(1.0f,1.0f,1.0f));
            uniformsMap.setUniform("spotLight.specular",new Vector3f(0.2f,0.2f,0.2f));
            uniformsMap.setUniform("spotLight.constant",1.0f);
            uniformsMap.setUniform("spotLight.linear",0.09f);
            uniformsMap.setUniform("spotLight.quadratic",0.032f);
            uniformsMap.setUniform("spotLight.cutOff",(float)Math.cos(Math.toRadians(20f)));
            uniformsMap.setUniform("spotLight.outerCutOff",(float)Math.cos(Math.toRadians(22f)));
        }

    }
    public void knife(){
        float x = centerPoint.get(0);
        float y = centerPoint.get(1);
        float z = centerPoint.get(2);

        Vector3f ver;

        ver = new Vector3f(x,y-radiusY,z);
        vertices.add(ver);

        ver = new Vector3f(x,y,z+radiusZ);
        vertices.add(ver);

        ver = new Vector3f(x,y,z);
        vertices.add(ver);
    }
    public void gun() {
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //TITIK 1
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f - 0.01f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 2
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f - 0.01f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 3
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f + 0.01f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 4
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f + 0.01f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 5
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f - 0.01f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 6
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f - 0.01f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 7
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f + 0.01f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 8
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f + 0.01f;
        tempVertices.add(temp);
        temp = new Vector3f();

        vertices.clear();
        //kotak yg sisi belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
        //kotak yg sisi kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi kanan
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));
        //kotak yg sisi atas
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(4));
        //kotak yg sisi bawah
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
    }
    public void button() {
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //TITIK 1
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 2
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 3
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 4
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 5
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 6
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 7
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 8
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();

        vertices.clear();
        //kotak yg sisi belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi kanan
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));
        //kotak yg sisi bawah
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
        //kotak yg sisi atas
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        //kotak yg sisi depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));

        normal = new ArrayList<>(Arrays.asList(
//               belakang
                new Vector3f(0,0,-1f),
                new Vector3f(0,0,-1f),
                new Vector3f(0,0,-1f),
                new Vector3f(0,0,-1f),
                new Vector3f(0,0,-1f),
                new Vector3f(0,0,-1f),
//                kiri
                new Vector3f(-1f,0,0),
                new Vector3f(-1f,0,0),
                new Vector3f(-1f,0,0),
                new Vector3f(-1f,0,0),
                new Vector3f(-1f,0,0),
                new Vector3f(-1f,0,0),
//                kanan
                new Vector3f(1f,0,0),
                new Vector3f(1f,0,0),
                new Vector3f(1f,0,0),
                new Vector3f(1f,0,0),
                new Vector3f(1f,0,0),
                new Vector3f(1f,0,0),
//                bawah
                new Vector3f(0,-1f,0),
                new Vector3f(0,-1f,0),
                new Vector3f(0,-1f,0),
                new Vector3f(0,-1f,0),
                new Vector3f(0,-1f,0),
                new Vector3f(0,-1f,0),
//                atas
                new Vector3f(0,1f,0),
                new Vector3f(0,1f,0),
                new Vector3f(0,1f,0),
                new Vector3f(0,1f,0),
                new Vector3f(0,1f,0),
                new Vector3f(0,1f,0),
//                depan
                new Vector3f(0,0,1f),
                new Vector3f(0,0,1f),
                new Vector3f(0,0,1f),
                new Vector3f(0,0,1f),
                new Vector3f(0,0,1f),
                new Vector3f(0,0,1f)
//                kiri




        ));
    }

    public void room(){
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //TITIK 1
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 2
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 3
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 4
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 5
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 6
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 7
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 8
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();

        vertices.clear();
        //kotak yg sisi belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi kanan
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));
        //kotak yg sisi bawah
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
    }

    public void EllipticCone() {
        ArrayList<Vector3f> temp = new ArrayList<>();

        for (float v = 0; v <= stackCount; v++) {
            float phi = (v / stackCount) * ((float) Math.PI * 2.0f);
            for (float u = 0; u <= sectorCount; u++) {
                float theta = (u / sectorCount) * ((float) Math.PI * 2.0f);
                float x = radiusX * (float) (phi * Math.cos(theta));
                float y = radiusY * (float) (phi * Math.sin(theta));
                float z = radiusZ * phi;
                temp.add(new Vector3f(x, z, y));
            }
        }
        vertices.addAll(temp);
    }

    public void bag(){
        Vector3f temp = new Vector3f();
        ArrayList<Vector3f> tempVertices = new ArrayList<>();
        //TITIK 1
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 2
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 3
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 4
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) - radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 5
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 6
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) + radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 7
        temp.x = centerPoint.get(0) + radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();
        //TITIK 8
        temp.x = centerPoint.get(0) - radiusX / 2.0f;
        temp.y = centerPoint.get(1) - radiusY / 2.0f;
        temp.z = centerPoint.get(2) + radiusZ / 2.0f;
        tempVertices.add(temp);
        temp = new Vector3f();

        vertices.clear();
        //kotak yg sisi belakang
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi depan
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(7));
        //kotak yg sisi kiri
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(4));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(3));
        //kotak yg sisi kanan
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(6));
        vertices.add(tempVertices.get(2));
        //kotak yg sisi atas
        vertices.add(tempVertices.get(0));
        vertices.add(tempVertices.get(1));
        vertices.add(tempVertices.get(5));
        vertices.add(tempVertices.get(4));
        //kotak yg sisi bawah
        vertices.add(tempVertices.get(3));
        vertices.add(tempVertices.get(2));
        vertices.add(tempVertices.get(7));
        vertices.add(tempVertices.get(6));
    }
//    public void draw(){
//        drawSetup();
//        glLineWidth(2); //ketebalan garis
//        glPointSize(2); //besar kecil vertex
//        glDrawArrays(GL_LINE_STRIP,
//                0,
//                vertices.size());
//    }

    public void octagon() {

    }

    public void leg(){
        float a=0,b=0,c = 0;

        for(float t = 0;t<360;t+=0.1){
            double rad = degToRad(t);
            a = (float)(centerPoint.get(0)+Math.cos(rad)*radiusX);
            b = (float) (centerPoint.get(1)+Math.sin(rad)*radiusY);
            c = 0;
            vertices.add(new Vector3f(a,b,c));

            a = (float)(centerPoint.get(0)+Math.cos(rad)*radiusX);
            b = (float) (centerPoint.get(1)+Math.sin(rad)*radiusY);
            c = -radiusZ;
            vertices.add(new Vector3f(a,b,c));
        }

    }

    public void medbay(){
        float a=0,b=0,c = 0;

        for(float t = 0;t<360;t+=0.1) {
            double rad = degToRad(t);
            a = (float) (centerPoint.get(0) + Math.cos(rad) * radiusX);
            b = (float) (centerPoint.get(1) + Math.sin(rad) * radiusY);
            c = 0;
            vertices.add(new Vector3f(a, b, c));

            float tempx = radiusX + 0.01f;
            float tempy = radiusY + 0.01f;

            a = (float) (centerPoint.get(0) + Math.cos(rad) * (tempx));
            b = (float) (centerPoint.get(1) + Math.sin(rad) * (tempy));
            c = -radiusZ;
            vertices.add(new Vector3f(a, b, c));
        }
    }

    public void medleg(){
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i)
        {

            StackAngle = pi / 2 - i * stackStep;
            x = radiusX * (float)Math.cos(StackAngle);
            y = radiusY * (float)Math.cos(StackAngle);
            z = radiusZ * (float)Math.sin(StackAngle);

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float)Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;
                vertices.add(temp_vector);
            }
            if (i >= stackCount/2){
                float a=0,b=0,c = 0;

                for(float t = 0;t<360;t+=0.01){
                    double rad = degToRad(t);
                    a = (float)(centerPoint.get(0)+Math.cos(rad)*radiusX);
                    b = (float) (centerPoint.get(1)+Math.sin(rad)*radiusY);
                    c = 0;
                    vertices.add(new Vector3f(a,b,c));

                    a = (float)(centerPoint.get(0)+Math.cos(rad)*radiusX);
                    b = (float) (centerPoint.get(1)+Math.sin(rad)*radiusY);
                    c = - radiusZ;
                    vertices.add(new Vector3f(a,b,c));
                }

                break;
            }
        }
    }

    public void body(){
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i)
        {

            StackAngle = pi / 2 - i * stackStep;
            x = radiusX * (float)Math.cos(StackAngle);
            y = radiusY * (float)Math.cos(StackAngle);
            z = radiusZ * (float)Math.sin(StackAngle);

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float)Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;
                vertices.add(temp_vector);
            }
            if (i >= stackCount/2){
                float a=0,b=0,c = 0;

                for(float t = 0;t<360;t+=0.01){
                    double rad = degToRad(t);
                    a = (float)(centerPoint.get(0)+Math.cos(rad)*radiusX);
                    b = (float) (centerPoint.get(1)+Math.sin(rad)*radiusY);
                    c = 0;
                    vertices.add(new Vector3f(a,b,c));

                    a = (float)(centerPoint.get(0)+Math.cos(rad)*radiusX);
                    b = (float) (centerPoint.get(1)+Math.sin(rad)*radiusY);
                    c = -.14f;
                    vertices.add(new Vector3f(a,b,c));
                }

                break;
            }
        }
    }

    public void glasses(){
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i)
        {

            StackAngle = pi / 2 - i * stackStep;
            x = radiusX * (float)Math.cos(StackAngle);
            y = radiusY * (float)Math.cos(StackAngle);
            z = radiusZ * (float)Math.sin(StackAngle);

            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float)Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;
                vertices.add(temp_vector);
            }
        }
    }

    public void createSphere(){
        float pi = (float)Math.PI;

        float sectorStep = 2 * (float)Math.PI / sectorCount;
        float stackStep = (float)Math.PI / stackCount;
        float sectorAngle, StackAngle, x, y, z;

        for (int i = 0; i <= stackCount; ++i)
        {

            StackAngle = pi / 2 - i * stackStep;
            x = radiusX * (float)Math.cos(StackAngle);
            y = radiusY * (float)Math.cos(StackAngle);
            z = radiusZ * (float)Math.sin(StackAngle);


            for (int j = 0; j <= sectorCount; ++j)
            {
                sectorAngle = j * sectorStep;
                Vector3f temp_vector = new Vector3f();
                temp_vector.x = centerPoint.get(0) + x * (float)Math.cos(sectorAngle);
                temp_vector.y = centerPoint.get(1) + y * (float)Math.sin(sectorAngle);
                temp_vector.z = centerPoint.get(2) + z;
                vertices.add(temp_vector);
            }
        }
    }
}