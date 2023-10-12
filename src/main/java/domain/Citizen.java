package domain;

/**
 * Класс данных о гражданах
 */
public class Citizen {
    // Суррогатный ключ
    private Long id;
    // Внешний ключ для связи с моделью Document
    private Long documentId;
    // Внешний ключ для связи с моделью Person
    private Long personId;
    // Имя клиента
    private String firstName;
    // Фамилия клиента
    private String secondName;
    // Отчество клиента
    private String lastName;
    // Номер документа, удостоверяющего личность
    private String number;

    public Citizen() {
    }

    public Citizen(Long documentId, Long personId, String firstName, String secondName, String lastName, String number) {
        this.documentId = documentId;
        this.personId = personId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.number = number;
    }

    public Citizen(Long id, Long documentId, Long personId, String firstName, String secondName, String lastName, String number) {
        this.id = id;
        this.documentId = documentId;
        this.personId = personId;
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Citizen {" +
                "Id = " + id +
                ", DocumentID = " + documentId +
                ", PersonID = " + personId +
                ", FirstName = '" + firstName + '\'' +
                ", SecondName = '" + secondName + '\'' +
                ", LastName = '" + lastName + '\'' +
                ", Number = '" + number + '\'' +
                "}";
    }
}