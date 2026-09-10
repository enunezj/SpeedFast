package model;

public class PedidoComida extends Pedido {

    public PedidoComida(
            int codigo,
            String cliente,
            String direccion,
            double distanciaKm) {

        super(codigo, cliente, direccion, distanciaKm);
    }


    // Asignación automática para pedidos de comida.
    @Override
    public void asignarRepartidor() {

        if (puedeAsignarRepartidor()) {

            registrarAsignacion(
                    "Carlos - Moto",
                    "automáticamente"
            );
        }
    }


    // Asignación manual especializada para pedidos de comida.
    @Override
    public void asignarRepartidor(String nombre) {

        if (puedeAsignarRepartidor()) {

            registrarAsignacion(
                    nombre + " - Moto",
                    "manualmente"
            );
        }
    }


    @Override
    public int calcularTiempoEntrega() {

        return 20 + (int) (getDistanciaKm() * 3);
    }
}