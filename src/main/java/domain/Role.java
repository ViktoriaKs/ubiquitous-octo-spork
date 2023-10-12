package domain;
/**
* Класс данных о должностях
*/
public class Role {
// Идентификатор должности
private Long id;
// Наименование должности
private String namerole;
public Role() {
}
public Role(String namerole) {
this.namerole = namerole;
}
public Role(Long id, String namerole) {
this.id = id;
this.namerole = namerole;
}
public Long getId() {
return id;
}
public void setId(Long id) {

this.id = id;
}
public String getNamerole() {
return namerole;
}
public void setNamerole(String namerole) {
this.namerole = namerole;
}
@Override
public String toString() {
return "Role {" + "Id = " + id + ", NameRole = " + namerole
+ "}";
}
public String getComputerStatus() {
	// TODO Auto-generated method stub
	return null;
}
}
