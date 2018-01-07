package models;

public class BasicListDTO<C, T> extends BasicDTO{
    Class<C> resp;
    ListDTO<T> listDTO = new ListDTO<>();

    public ListDTO<T> getListDTO() {
        return listDTO;
    }

    public void setListDTO(ListDTO<T> listDTO) {
        this.listDTO = listDTO;
    }

    public Class<C> getResp() {
        return resp;
    }

    public void setResp(Class<C> resp) {
        this.resp = resp;
    }
}
