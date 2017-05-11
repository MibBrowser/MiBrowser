package Domain;

public class Package {

    private double inErrorPercent;
    private double outErrorPercent;
    private double inDiscardPercent;
    private double outDiscardPercent;

    public Package(Interface iface) {
        this.inErrorPercent = this.calculateInErrorPercent(iface);
        this.outErrorPercent = this.calculateOutErrorPercent(iface);
        this.inDiscardPercent = this.calculateInDiscardPercent(iface);
        this.outDiscardPercent = this.calculateOutDiscardPercent(iface);
    }

    private double calculateInErrorPercent(Interface iface) {
        if ((iface.getInCast() + iface.getInNcast()) == 0) {
            return 0;
        }
        //porcentagem de erro de entrada = ifInErrors / (ifInUcastPkts + ifInNUcastPkts)
        return iface.getInErrors() / (iface.getInCast() + iface.getInNcast());
    }

    private double calculateOutErrorPercent(Interface iface) {
        if ((iface.getOutCast() + iface.getOutNcast()) == 0) {
            return 0;
        }
        //porcentagem de erro de saída = ifOutErrors / (ifOutUcastPkts + ifOutNUcastPkts )
        return iface.getOutErrors() / (iface.getOutCast() + iface.getOutNcast());
    }

    private double calculateInDiscardPercent(Interface iface) {
        if ((iface.getInCast() + iface.getInNcast()) == 0) {
            return 0;
        }
        //porcentagem de descarte de entrada = ifInDiscards / (ifOutUcastPkts + ifOutNUcastPkts )
        return iface.getInDiscards() / (iface.getInCast() + iface.getInNcast());
    }

    private double calculateOutDiscardPercent(Interface iface) {
        if ((iface.getOutCast() + iface.getOutNcast()) == 0) {
            return 0;
        }
        //porcentagem de descarte de saída = ifOutDiscards / (ifOutUcastPkts + ifOutNUcastPkts )
        return iface.getOutDiscards() / (iface.getOutCast() + iface.getOutNcast());
    }

    public double getInErrorPercent() {
        return inErrorPercent;
    }

    public double getOutErrorPercent() {
        return outErrorPercent;
    }

    public double getInDiscardPercent() {
        return inDiscardPercent;
    }

    public double getOutDiscardPercent() {
        return outDiscardPercent;
    }
}
