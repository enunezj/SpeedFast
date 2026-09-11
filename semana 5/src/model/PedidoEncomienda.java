package model;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(
            int id,
            String direccionEntrega) {

        super(
                id,
                direccionEntrega
        );
    }


    public PedidoEncomienda(
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
                    "Pedro - Camioneta",
                    "automáticamente"
            );
        }
    }


    @Override
    public void asignarRepartidor(
            String nombre) {

        if (puedeAsignarRepartidor()) {

            registrarAsignacion(
                    nombre + " - Camioneta",
                    "manualmente"
            );
        }
    }


    @Override
    public int calcularTiempoEntrega() {

        return 30
                + (int) (getDistanciaKm() * 4);
    }
}