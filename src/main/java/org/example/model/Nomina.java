package org.example.model;

import java.math.BigDecimal;

public class Nomina {

    private String dni;
    private BigDecimal sueldo;

    public Nomina() {
    }

    public Nomina(String dni, BigDecimal sueldo) {
        this.dni = dni;
        this.sueldo = sueldo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }
}
