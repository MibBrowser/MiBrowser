package Presentation.Services;

import Domain.Interface;
import Domain.Package;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PackageInformationService {

    private Interface iface;

    public PackageInformationService(Interface _iface) {
        this.iface = _iface;
    }

    public Package readPackage() throws IOException, TimeoutException, InterruptedException {
        return new Package(this.iface);
    }
}
