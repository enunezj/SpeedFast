package model;

public class PedidoExpress extends Pedido {

    public PedidoExpress(
            int id,
            String direccionEntrega) {

        super(
                id,
                direccionEntrega
        );
    }


    public PedidoExpress(
            int id,
            String cliente,
            String direccionEntrega,
            double distanciaKm) {

        super(
                id,
                cliente,
                direccionEntrega,
                distanciaKm
        );
    }


    @Override
    public void asignarRepartidor() {

        if (puedeAsignarRepartidor()) {

            registrarAsignacion(
                    "Sofia - Express",
                    "automáticamente"
            );
        }
    }


    @Override
    public void asignarRepartidor(
            String nombre) {

        if (puedeAsignarRepartidor()) {

            registrarAsignacion(
                    nombre + " - Express",
                    "manualmente"
            );
        }
    }


    @Override
    public int calcularTiempoEntrega() {

        return 10
                + (int) (getDistanciaKm() * 2);
    }
}