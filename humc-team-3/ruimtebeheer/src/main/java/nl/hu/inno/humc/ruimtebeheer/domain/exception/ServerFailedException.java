package nl.hu.inno.humc.ruimtebeheer.domain.exception;

import java.rmi.ServerException;

public class ServerFailedException extends ServerException {
    public ServerFailedException(String s) {
        super(s);
    }
}
