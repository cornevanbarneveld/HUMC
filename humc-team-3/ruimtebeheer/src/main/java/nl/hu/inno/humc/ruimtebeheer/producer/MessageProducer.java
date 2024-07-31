package nl.hu.inno.humc.ruimtebeheer.producer;

public interface MessageProducer<T> {

    void sendMessage(T message);
}
