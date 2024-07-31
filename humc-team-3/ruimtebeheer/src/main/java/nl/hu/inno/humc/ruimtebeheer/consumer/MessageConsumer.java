package nl.hu.inno.humc.ruimtebeheer.consumer;


public interface MessageConsumer<T> {

    void consume(T message);
}
