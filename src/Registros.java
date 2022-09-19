public interface Registros {
    void agregarRegistro(Object obj);
    void eliminarRegistro(Object obj);
    void editarRegistro(int index, Object obj);

    /* Hacer los métodos traerRegistro() y traerRegistros() abstractos aplicables para ambas clases. */
    //void traerRegistros();
    //void traerRegistro(int index);
}