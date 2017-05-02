/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author guitv
 */
public class UsageRate {

    private double valor;
    private Octet lastRead;

    public UsageRate(Octet lastRead, Octet compareRead, double speed) {
        double totalBytes = this.calcTotalBytes(lastRead, compareRead);
        double totalBytesPerSecond = this.calcTotalBytesPerSecond(totalBytes, lastRead, compareRead);
        double totalBitsPerSecond = this.calcTotalBitsPerSecond(totalBytesPerSecond);
        this.lastRead = lastRead;
        this.valor = totalBitsPerSecond / speed;
    }

    public UsageRate(Octet lastRead, double speed) {
        this(lastRead, new Octet(), speed);
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    private double calcTotalBytes(Octet lastRead, Octet compareRead) {
        return (lastRead.getIn() - compareRead.getIn()) + (lastRead.getOut() - compareRead.getOut());
    }

    private double calcTotalBytesPerSecond(double totalBytes, Octet lastRead, Octet compareRead) {
        return totalBytes / (lastRead.getTime() - compareRead.getTime());
    }

    private double calcTotalBitsPerSecond(double totalBytesPerSecond) {
        return totalBytesPerSecond * 8;
    }

    public Octet getLastRead() {
        return lastRead;
    }
}
