public class User implements java.io.Serializable {

String firstname;
String lastname;
String phone;
String email;
String password;
String loginid;
String usertype;
User(String firstname,String lastname,String phone,String email,String password,String loginid,String usertype){
this.firstname = firstname;
this.lastname = lastname;
this.phone = phone;
this.email = email;
this.password = password;
this.loginid = loginid;
this.usertype = usertype;
}
public void setFirstame(String firstname) {
       this.firstname = firstname;
}

public String getfirstName() {
        return firstname;
}

public void setlastname(String lastname) {
       this.lastname = lastname;
}

public String getlastname() {
       return lastname;
}

public void setphone(String phone) {
       this.phone = phone;
}

public String getphone() {
    return phone;
}

public void setemail(String email) {
    this.email = email;
}
public String getemail() {
   return email;
}

public void setloginid(String login) {
      this.loginid = loginid;
}

public String getloginid() {
     return loginid;
}
public void setpassword( String password) {
   this.password = password;
}

public String getpassword() {
   return password;
}

public void setusertype( String usertype){
     this.usertype = usertype;
}

public String getusertype(){
    return usertype;
}
}


