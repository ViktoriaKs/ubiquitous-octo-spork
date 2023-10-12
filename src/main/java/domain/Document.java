package domain;

import java.util.Date;

/**
 * Класс данных о документах, удостоверяющих личность
 */
public class Document {
    // Суррогатный ключ
    private Long id;
    // Наименование документа, удостоверяющего личность
    private String name;
    // Серия документа, удостоверяющего личность
    private String seriy;
    // Орган, выдавший документ, удостоверяющий личность
    private String organ;
    // Дата выдачи документа, удостоверяющего личность
    private Date data;

    public Document() {
    }

    public Document(String name, String seriy, String organ, Date data) {
        this.name = name;
        this.seriy = seriy;
        this.organ = organ;
        this.data = data;
    }

    public Document(Long id, String name, String seriy, String organ, Date data) {
        this.id = id;
        this.name = name;
        this.seriy = seriy;
        this.organ = organ;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeriy() {
        return seriy;
    }

    public void setSeriy(String seriy) {
        this.seriy = seriy;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Document {" +
                "Id = " + id +
                ", Name = '" + name + '\'' +
                ", Seriy = '" + seriy + '\'' +
                ", Organ = '" + organ + '\'' +
                ", Data = " + data +
                "}";
    }
}