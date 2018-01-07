package models;

public class TyppedDTO<T>extends BasicDTO{
    T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
