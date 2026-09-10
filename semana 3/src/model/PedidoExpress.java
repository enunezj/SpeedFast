package model;

public class PedidoExpress extends Pedido {

    public PedidoExpress(
            int codigo,
            String cliente,
            String direccion,
            double distanciaKm) {

        super(codigo, cliente, direccion, distanciaKm);
    }


    // Asignación automática para pedidos express.
    @Override
    public void asignarRepartidor() {

        if (puedeAsignarRepartidor()) {

            registrarAsignacion(
                    "Sofia - Express",
                    "automáticamente"
            );
        }
    }


    // Asignación manual especializada para pedidos express.
    @Override
    public void asignarRepartidor(String nombre) {

        if (puedeAsignarRepartidor()) {

            registrarAsignacion(
                    nombre + " - Express",
                    "manualmente"
            );
        }
    }


    @Override
    public int calcularTiempoEntrega() {

        return 10 + (int) (getDistanciaKm() * 2);
    }
}