package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class Util {

public static final int TIMEOUT = 300;

/**
 *
 * @param Entidade
 * @return Var
 */
// Util
public static Var processCurriculum(Var Entidade) throws Exception {
 return new Callable<Var>() {

   private Var curriculumText = Var.VAR_NULL;

   public Var call() throws Exception {
    curriculumText = Var.valueOf(api.pdf.PdfManager.byteToText(cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("curriculum"))));
    cronapi.database.Operations.execute(Var.valueOf("app.entity.User"), Var.valueOf("update User set curriculumText = :curriculumText where id = :id"),Var.valueOf("curriculumText",curriculumText),Var.valueOf("id",cronapi.object.Operations.getObjectField(Entidade, Var.valueOf("id"))));
    System.out.println(curriculumText.getObjectAsString());
    return Var.VAR_NULL;
   }
 }.call();
}

/**
 */
// Descreva esta função...
public static void checkRedirectSimpleUser() throws Exception {
  new Callable<Var>() {

   private Var login = Var.VAR_NULL;
   private Var item = Var.VAR_NULL;
   private Var usuarios = Var.VAR_NULL;

   public Var call() throws Exception {
    login = cronapi.util.Operations.getCurrentUserName();
    item = cronapi.database.Operations.query(Var.valueOf("app.entity.Role"),Var.valueOf("select r from Role r where r.user.login = :userLogin"),Var.valueOf("userLogin",login));
    if (cronapi.list.Operations.isEmpty(item).getObjectAsBoolean()) {
        usuarios = cronapi.database.Operations.query(Var.valueOf("app.entity.User"),Var.valueOf("select u from User u where u.login = :login"),Var.valueOf("login",login));
        cronapi.util.Operations.callClientFunction(Var.valueOf("cronapi.screen.changeView"), Var.valueOf("#/home/logged/candidate-simple"), cronapi.list.Operations.newList(Var.valueOf("userId",cronapi.object.Operations.getObjectField(cronapi.list.Operations.get(usuarios, Var.valueOf(1)), Var.valueOf("id")))));
    }
   return Var.VAR_NULL;
   }
 }.call();
}

}

