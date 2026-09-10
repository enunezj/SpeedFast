package model;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(
            int codigo,
            String cliente,
            String direccion,
            double distanciaKm) {

        super(codigo, cliente, direccion, distanciaKm);
    }


    // Asignación automática para encomiendas.
    @Override
    public void asignarRepartidor() {

        if (puedeAsignarRepartidor()) {

            registrarAsignacion(
                    "Pedro - Camioneta",
                    "automáticamente"
            );
        }
    }


    // Asignación manual especializada para encomiendas.
    @Override
    public void asignarRepartidor(String nombre) {

        if (puedeAsignarRepartidor()) {

            registrarAsignacion(
                    nombre + " - Camioneta",
                    "manualmente"
            );
        }
    }


    @Override
    public int calcularTiempoEntrega() {

        return 30 + (int) (getDistanciaKm() * 4);
    }
}