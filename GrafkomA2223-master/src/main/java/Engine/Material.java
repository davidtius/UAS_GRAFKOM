package Engine;

import org.joml.Vector3f;

public class Material {
    private String name;
    private Vector3f ambientColor;
    private Vector3f diffuseColor;
    private Vector3f specularColor;

//    public Material(String name) {
//        this.name = name;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmbientColor(Vector3f ambientColor) {
        this.ambientColor = ambientColor;
    }

    public void setDiffuseColor(Vector3f diffuseColor) {
        this.diffuseColor = diffuseColor;
    }

    public void setSpecularColor(Vector3f specularColor) {
        this.specularColor = specularColor;
    }

    public void print() {
        System.out.println(name);
        System.out.println(ambientColor);
        System.out.println(diffuseColor);
        System.out.println(specularColor);
    }
}
