import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String documento;
    private String nombre;
    private String telefono;
    private String correo;
    private String direccion;
    private List<Pedido> listPedidos;

    public Cliente(String documento, String nombre, String telefono, String correo, String direccion) {
        this.documento = documento;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;

        listPedidos= new ArrayList<>();
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Pedido> getListPedidos() {
        return listPedidos;
    }

    public void setListPedidos(List<Pedido> listPedidos) {
        this.listPedidos = listPedidos;
    }

    @Override
    public String toString() {
        return "Cliente: " +
                "documento: " + documento +
                ", nombre: " + nombre +
                ", telefono: " + telefono +
                ", correo: " + correo +
                ", direccion: " + direccion +
                ", listPedidos: " + listPedidos;
    }

    //Registrar pedidos
    public void agregarPedido(Pedido pedido) {
        if (pedido != null) {
            listPedidos.add(pedido);
        }
    }
}
