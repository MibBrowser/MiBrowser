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

    public UsageRate(Octet lastRead, Octet compareRead, long speed) {
        long totalBytes = this.calcTotalBytes(lastRead, compareRead);
        long totalBytesPerSecond = this.calcTotalBytesPerSecond(totalBytes, lastRead, compareRead);
        long totalBitsPerSecond = this.calcTotalBitsPerSecond(totalBytesPerSecond);
        this.valor = (double) totalBitsPerSecond / speed;
    }
    
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    private long calcTotalBytes(Octet lastRead, Octet compareRead) {
        return (lastRead.getIn() - compareRead.getIn()) + (lastRead.getOut() - compareRead.getOut());
    }

    private long calcTotalBytesPerSecond(long totalBytes, Octet lastRead, Octet compareRead) {
        return totalBytes / (lastRead.getTime() - compareRead.getTime());
    }

    private long calcTotalBitsPerSecond(long totalBytesPerSecond) {
        return totalBytesPerSecond * 8;
    }
}
