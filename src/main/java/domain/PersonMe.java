package domain;

import java.util.Date;

/**
 * Класс данных о клиентах
 */
public class PersonMe {
    // Суррогатный ключ
    private Long id;
    // Шифр клиента
    private String shifer;
    // ИНН клиента
    private String inn;
    // Тип клиента (физическое, юридическое лицо)
    private String type;
    // Дата регистрации клиента
    private Date data;

    public PersonMe() {
    }

    public PersonMe(String shifer, String inn, String type, Date data) {
        this.shifer = shifer;
        this.inn = inn;
        this.type = type;
        this.data = data;
    }

    public PersonMe(Long id, String shifer, String inn, String type, Date data) {
        this.id = id;
        this.shifer = shifer;
        this.inn = inn;
        this.type = type;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShifer() {
        return shifer;
    }

    public void setShifer(String shifer) {
        this.shifer = shifer;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Person {" + "Id = " + id + ", Shifer = '" + shifer + '\'' + ", Inn = '" + inn + '\'' + ", Type = '" + type + '\'' + ", Data = " + data + "}";
    }
}