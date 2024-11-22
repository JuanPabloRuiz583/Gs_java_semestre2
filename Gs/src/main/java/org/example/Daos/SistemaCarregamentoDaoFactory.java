package org.example.Daos;

public class SistemaCarregamentoDaoFactory {

        private SistemaCarregamentoDaoFactory() {
        }

        public static SistemaCarregamentoDao create() {
            return new SistemaCarregamentoDaoImpl();
        }

}
