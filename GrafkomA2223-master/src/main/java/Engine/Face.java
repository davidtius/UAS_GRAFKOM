package Engine;

import org.joml.Vector3f;

import java.util.List;

public class Face {
    public Vector3f vertex; // three Indices
    public Vector3f normal;
    public Vector3f texture;
    public Vector3f color;
    public Face(Vector3f vertex, Vector3f normal, Vector3f texture, Vector3f color){
        this.vertex = vertex;
        this.normal = normal;
        this.texture = texture;
        this.color = color;
    }
}