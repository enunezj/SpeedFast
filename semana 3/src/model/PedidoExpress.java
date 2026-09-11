package model;

// Representa una compra o pedido express.
public class PedidoExpress extends Pedido {

    public PedidoExpress(
            int codigo,
            String cliente,
            String direccion,
            double distanciaKm) {

        super(codigo, cliente, direccion, distanciaKm);
    }


    // SOBRESCRITURA:
    // Los pedidos express utilizan un repartidor especializado.
    @Override
    public void asignarRepartidor() {

        String nombre = "Sofia - Express";

        setRepartidor(nombre);

        registrarEvento(
                "Repartidor automático asignado: " + nombre
        );

        System.out.println(
                "Repartidor automático asignado: " + nombre
        );
    }


    // El pedido express tiene una entrega más rápida.
    @Override
    public int calcularTiempoEntrega() {

        return 10 + (int) (getDistanciaKm() * 2);
    }
}