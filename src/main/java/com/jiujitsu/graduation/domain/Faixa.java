package com.jiujitsu.graduation.domain;


import lombok.Getter;

@Getter
public enum Faixa {
 /*
    BRANCA(1.0),
    AZUL(2.0),
    ROXA(3.0),
    MARROM(4.0),
    PRETA(5.0);
*/

    BRANCA(1),
    AZUL(2),
    ROXA(3),
    MARROM(4),
    PRETA(5);

    private final int ref;

    Faixa(int ref) {
        this.ref = ref;
    }

    public int getRef() {
        return ref;
    }

    public static Faixa getColorByValue(int value) {
        for (Faixa faixa : Faixa.values()) {
            if (faixa.getRef() == value) {
                return faixa;
            }
        }
        throw new IllegalArgumentException("Código referência de faixa inválido: " + value
                + " \n utilize: \n BRANCA(1.0), AZUL(2.0), ROXA(3.0), MARROM(4.0), PRETA(5.0)");
    }

}
