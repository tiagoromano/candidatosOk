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
    return Var.VAR_NULL;
   }
 }.call();
}

/**
 */
// Descreva esta função...
public static void checkRedirectSimpleUser() throws Exception {
  new Callable<Var>() {

   private Var usuarios = Var.VAR_NULL;
   private Var login = Var.VAR_NULL;
   private Var item = Var.VAR_NULL;

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

/**
 *
 * @param keys
 */
// Descreva esta função...
public static void downloadCurriculum(Var keys) throws Exception {
  new Callable<Var>() {

   private Var usuarios = Var.VAR_NULL;
   private Var contentBytes = Var.VAR_NULL;
   private Var filePath = Var.VAR_NULL;
   private Var newFile = Var.VAR_NULL;

   public Var call() throws Exception {
    usuarios = cronapi.database.Operations.query(Var.valueOf("app.entity.User"),Var.valueOf("select u from User u where u.id = :id"),Var.valueOf("id",cronapi.object.Operations.getObjectField(keys, Var.valueOf("id"))));
    if (cronapi.list.Operations.isEmpty(usuarios).getObjectAsBoolean()) {
        cronapi.util.Operations.callClientFunction( Var.valueOf("cronapi.screen.notify"), Var.valueOf("error"), Var.valueOf("Usuário não encontrado"));
    } else {
        contentBytes = cronapi.object.Operations.getObjectField(cronapi.list.Operations.get(usuarios, Var.valueOf(1)), Var.valueOf("curriculum"));
        System.out.println(contentBytes.getObjectAsString());
        if (cronapi.logic.Operations.isNullOrEmpty(contentBytes).getObjectAsBoolean()) {
            cronapi.util.Operations.callClientFunction( Var.valueOf("cronapi.screen.notify"), Var.valueOf("error"), Var.valueOf("Não existe currículo anexado"));
        } else {
            filePath = Var.valueOf(cronapi.io.Operations.fileAppReclycleDir().toString() + cronapi.io.Operations.fileSeparator().toString() + cronapi.object.Operations.getObjectField(cronapi.list.Operations.get(usuarios, Var.valueOf(1)), Var.valueOf("name")).toString() + cronapi.util.Operations.random(Var.valueOf(9999)).toString() + Var.valueOf(".pdf").toString());
            newFile = cronapi.io.Operations.fileOpenToWrite(filePath, Var.VAR_NULL);
            cronapi.io.Operations.fileAppend(newFile, contentBytes);
            cronapi.io.Operations.fileClose(newFile);
            cronapi.io.Operations.fileDownload(filePath);
        }
    }
   return Var.VAR_NULL;
   }
 }.call();
}

}

