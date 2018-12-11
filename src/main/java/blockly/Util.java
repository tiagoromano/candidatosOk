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

}

