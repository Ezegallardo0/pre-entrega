package model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contadorPedido = 1;
    private int idPedido;
    private List<LineaPedido> lineas;

    public Pedido() {
        this.idPedido = contadorPedido++;
        this.lineas = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) throws StockInsuficienteException {
        producto.reducirStock(cantidad);
        lineas.add(new LineaPedido(producto, cantidad));
    }

    public double calcularTotal() {
        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.getSubtotal();
        }
        return total;
    }

    public void mostrarResumen() {
        System.out.println("\n===RESUMEN DEL PEDIDO #" + idPedido + "===");
        for (LineaPedido linea : lineas) {
            System.out.println("- " + linea.getProducto().getNombre() + " x " + linea.getCantidad() + " = $" + linea.getSubtotal());
        }
        System.out.println("TOTAL A PAGAR: $" + calcularTotal());
    }
}
