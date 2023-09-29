package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import models.Usuario;

import java.io.File;
import java.io.IOException;

public class YamlUtils {

    private ObjectMapper mapper;

    public YamlUtils() {
        mapper = new ObjectMapper(new YAMLFactory().disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER));
    }

    public Usuario getUsuarioValido(String caracteristica){
        Usuario usuario;
        try {
            usuario = leerArchivoYML("src/test/resources/datos/usuarios.yml", caracteristica, Usuario.class);
        }
        catch (IOException e){
            e.printStackTrace();
            throw new IllegalArgumentException("Error al leer archivo yml. La estructura no corresponde a la clase Usuario.");
        }
        if(usuario == null)
            throw new IllegalArgumentException("No se encontro usuario " + caracteristica + " en el archivo yml.");
        return usuario;
    }
    public <T extends Object> T leerArchivoYML(String path, String key, Class<T> clase) throws IOException {
        return mapper.treeToValue(mapper.readTree(new File(path)).get(key), clase);
    }

    public void escribirArchivoYML(String path, Object value){
        try {
            mapper.writeValue(new File(path), value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}