package model;

public class PedidoComida extends Pedido {

    public PedidoComida(
            int id,
            String direccionEntrega) {

        super(
                id,
                direccionEntrega
        );
    }


    public PedidoComida(
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
                    "Carlos - Moto",
                    "automáticamente"
            );
        }
    }


    @Override
    public void asignarRepartidor(
            String nombre) {

        if (puedeAsignarRepartidor()) {

            registrarAsignacion(
                    nombre + " - Moto",
                    "manualmente"
            );
        }
    }


    @Override
    public int calcularTiempoEntrega() {

        return 20
                + (int) (getDistanciaKm() * 3);
    }
}