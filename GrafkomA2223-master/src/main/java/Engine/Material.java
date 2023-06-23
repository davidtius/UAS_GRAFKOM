package Engine;

import org.joml.Vector3f;

public class Material {
    private String name;
    private Vector3f ambientColor;
    private Vector3f diffuseColor;
    private Vector3f specularColor;
//    private String mapKsPath; // Path to the specular color map
//    private String mapNsPath; // Path to the specular exponent map
//    private String mapReflPath; // Path to the reflection map
//    private String mapBumpPath;
//    private String diffuseMapPath; // Diffuse map file path
//    private String opacityMapPath; // Opacity map file path
//    private String emissiveMapPath; // Emissive map file path

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

    public String getName() {
        return name;
    }

    public Vector3f getAmbientColor() {
        return ambientColor;
    }

    public Vector3f getDiffuseColor() {
        return diffuseColor;
    }

    public Vector3f getSpecularColor() {
        return specularColor;
    }

//    public String getDiffuseMapPath() {
//        return diffuseMapPath;
//    }
//
//    public void setDiffuseMapPath(String diffuseMapPath) {
//        this.diffuseMapPath = diffuseMapPath;
//    }
//
//    public String getOpacityMapPath() {
//        return opacityMapPath;
//    }
//
//    public void setOpacityMapPath(String opacityMapPath) {
//        this.opacityMapPath = opacityMapPath;
//    }
//
//    public String getEmissiveMapPath() {
//        return emissiveMapPath;
//    }
//
//    public void setEmissiveMapPath(String emissiveMapPath) {
//        this.emissiveMapPath = emissiveMapPath;
//    }
//
//    public String getMapKsPath() {
//        return mapKsPath;
//    }
//
//    public void setMapKsPath(String mapKsPath) {
//        this.mapKsPath = mapKsPath;
//    }
//
//    public String getMapNsPath() {
//        return mapNsPath;
//    }
//
//    public void setMapNsPath(String mapNsPath) {
//        this.mapNsPath = mapNsPath;
//    }
//
//    public String getMapReflPath() {
//        return mapReflPath;
//    }
//
//    public void setMapReflPath(String mapReflPath) {
//        this.mapReflPath = mapReflPath;
//    }
//
//    public String getMapBumpPath() {
//        return mapBumpPath;
//    }
//
//    public void setMapBumpPath(String mapBumpPath) {
//        this.mapBumpPath = mapBumpPath;
//    }

    public void print() {
        System.out.println(name);
        System.out.println(ambientColor);
        System.out.println(diffuseColor);
        System.out.println(specularColor);
    }
}
